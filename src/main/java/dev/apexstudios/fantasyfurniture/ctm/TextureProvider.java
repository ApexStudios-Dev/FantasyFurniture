package dev.apexstudios.fantasyfurniture.ctm;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.mojang.blaze3d.platform.NativeImage;
import dev.apexstudios.apexcore.core.data.provider.BaseProvider;
import dev.apexstudios.apexcore.lib.data.ProviderType;
import dev.apexstudios.apexcore.lib.data.provider.context.ProviderOutputContext;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;

final class TextureProvider implements BaseProvider {
    public static final ProviderType<TextureProvider> PROVIDER_TYPE = ProviderType.register(FantasyFurniture.identifier("ctm/texture"), TextureProvider::new);

    private final List<Textures> textures = Lists.newArrayList();

    public void with(Block block, boolean dyeable) {
        var blockName = block.builtInRegistryHolder().key().location();

        textures.add(Textures.create(blockName));

        if(dyeable)
            textures.add(Textures.create(blockName.withSuffix("_tint")));
    }

    @Override
    public CompletableFuture<?> generate(CachedOutput cache, ProviderOutputContext context) {
        var resourceManager = context.getResourceManager(PackType.CLIENT_RESOURCES);
        var assetsDir = context.output().getOutputFolder(PackOutput.Target.RESOURCE_PACK);

        return CompletableFuture.allOf(textures.stream().map(texture -> generate(cache, resourceManager, texture, assetsDir)).toArray(CompletableFuture[]::new));
    }

    private CompletableFuture<?> generate(CachedOutput cache, ResourceManager resourceManager, Textures textures, Path assetsDir) {
        return CompletableFuture.runAsync(() -> {
            try(var baseIs = resourceManager.open(expand(textures.base));
                var centerIs = resourceManager.open(expand(textures.center));
                var emptyIs = resourceManager.open(expand(textures.empty));
                var horizontalIs = resourceManager.open(expand(textures.horizontal));
                var verticalIs = resourceManager.open(expand(textures.vertical));
            ) {
                var loaded = new LoadedTextures(
                        textures,
                        NativeImage.read(baseIs),
                        NativeImage.read(centerIs),
                        NativeImage.read(emptyIs),
                        NativeImage.read(horizontalIs),
                        NativeImage.read(verticalIs)
                );

                generate(cache, loaded, assetsDir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private ResourceLocation expand(ResourceLocation modelPath) {
        return modelPath.withPath(path -> "textures/block/" + path + ".png");
    }

    private void generate(CachedOutput cache, LoadedTextures textures, Path assetsDir) throws IOException {
        validateSameSize(textures.base, textures.center, textures.empty, textures.horizontal, textures.vertical);
        generate2x2(cache, textures, assetsDir);
        generateStrip(cache, textures, assetsDir);
    }

    private void validateSameSize(NativeImage... images) {
        for(var i = 0; i < images.length; i++) {
            for(var j = images.length - 1; j >= 0; j--) {
                if(i != j)
                    validateSameSize(images[i], images[j]);
            }
        }
    }

    private void validateSameSize(NativeImage a, NativeImage b) {
        if(a.getWidth() != b.getWidth())
            throw new IllegalStateException("Textures must have the same width");
        if(a.getHeight() != b.getHeight())
            throw new IllegalStateException("Textures must have the same height");
    }

    private void generate2x2(CachedOutput cache, LoadedTextures textures, Path assetsDir) throws IOException {
        var width = textures.base.getWidth();
        var height = textures.base.getHeight();

        var baseName = expand(textures.paths.base.withPath(path -> "ctm/" + path + "_simple"));
        var outputPath = assetsDir.resolve(baseName.getNamespace()).resolve(baseName.getPath());

        try (var output = new NativeImage(NativeImage.Format.RGBA, width * 2, height * 2, false)) {
            textures.empty.copyRect(output, 0, 0, 0, 0, width, height, false, false);
            textures.vertical.copyRect(output, 0, 0, width, 0, width, height, false, false);
            textures.horizontal.copyRect(output, 0, 0, 0, height, width, height, false, false);
            textures.center.copyRect(output, 0, 0, width, height, width, height, false, false);

            write(cache, output, outputPath);
        }
    }

    private void generateStrip(CachedOutput cache, LoadedTextures textures, Path assetsDir) throws IOException {
        var width = textures.base.getWidth();
        var height = textures.base.getHeight();

        var slices = new NativeImage[] {
                textures.base,
                textures.empty,
                textures.vertical,
                textures.horizontal,
                textures.center
        };

        var baseName = expand(textures.paths.base.withPath(path -> "ctm/" + path + "_simple_vertical"));
        var outputPath = assetsDir.resolve(baseName.getNamespace()).resolve(baseName.getPath());

        try (var output = new NativeImage(NativeImage.Format.RGBA, width * slices.length, height, false)) {
            for(var i = 0; i < slices.length; i++) {
                var slice = slices[i];
                slice.copyRect(output, 0, 0, i * width, 0, width, height, false, false);
            }

            write(cache, output, outputPath);
        }
    }

    private void write(CachedOutput cache, NativeImage image, Path path) throws IOException {
        var width = image.getWidth();
        var height = image.getHeight();

        var img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        img.setRGB(0, 0, width, height, image.getPixels(), 0, 0);

        // vanillas caching system does not work well when writing none text files
        // invalid files get written and if you dont write to the cache files get cleaned up
        // we work around this by writing a dummy text file
        // pass through the caching system without our file being cleaned up
        // downside to this, the generated file is not correctly cached and generated on every run
        writeDummy(cache, path);
        Files.deleteIfExists(path);
        image.writeToFile(path);
    }

    private void writeDummy(CachedOutput cache, Path path) throws IOException {
        var bao = new ByteArrayOutputStream();
        var ho = new HashingOutputStream(Hashing.sha1(), bao);

        try(var writer = new BufferedWriter(new OutputStreamWriter(ho, StandardCharsets.UTF_8))) {
            writer.write("dummy-value");
        }

        Files.deleteIfExists(path);
        cache.writeIfNeeded(path, bao.toByteArray(), ho.hash());
    }

    private record Textures(
            ResourceLocation base,
            ResourceLocation center,
            ResourceLocation empty,
            ResourceLocation horizontal,
            ResourceLocation vertical
    ) {
        public static Textures create(ResourceLocation blockName) {
            var ctmName = blockName.withPrefix("ctm/");

            return new Textures(
                    blockName,
                    ctmName.withSuffix("_center"),
                    ctmName.withSuffix("_empty"),
                    ctmName.withSuffix("_horizontal"),
                    ctmName.withSuffix("_vertical")
            );
        }
    }

    private record LoadedTextures(
            Textures paths,
            NativeImage base,
            NativeImage center,
            NativeImage empty,
            NativeImage horizontal,
            NativeImage vertical
    ) { }
}
