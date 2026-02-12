package dev.apexstudios.fantasyfurniture.common.ctm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import dev.apexstudios.apexcore.api.data.ProviderType;
import dev.apexstudios.apexcore.api.data.pack.FeaturePackGenerator;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import net.neoforged.fml.ModList;
import org.jspecify.annotations.Nullable;

public interface CtmPack {
    String modId();

    default String packId() {
        return "ctm-" + modId();
    }

    default String displayName() {
        return packId();
    }

    default String description() {
        return "Fantasy's Furniture " + displayName() + " CTM support";
    }

    default boolean isEnabled() {
        return modId().equals(FantasyFurniture.ID) || ModList.get().isLoaded(modId());
    }

    default void provide(FeaturePackGenerator packGenerator) {

    }

    static Builder builder(String modId) {
        return new Builder(modId);
    }

    final class Builder {
        private final String modId;
        @Nullable private String packId = null;
        @Nullable private String displayName = null;
        @Nullable private String description = null;
        private final Multimap<ProviderType<?>, Supplier<? extends BiConsumer<?, BlockRegistrar>>> providers = MultimapBuilder.hashKeys().linkedHashSetValues().build();

        private Builder(String modId) {
            this.modId = modId;
        }

        public Builder packId(String packId) {
            this.packId = packId;
            return this;
        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public <TProvider> Builder providing(ProviderType<TProvider> providerType, Supplier<BiConsumer<TProvider, BlockRegistrar>> consumer) {
            providers.put(providerType, consumer);
            return this;
        }

        public <TProvider> Builder providing(ProviderType<TProvider> providerType, BiConsumer<TProvider, BlockRegistrar> consumer) {
            return providing(providerType, () -> consumer);
        }

        public CtmPack build() {
            return new CtmPack() {
                private final String modId = Builder.this.modId;
                @Nullable private final String packId = Builder.this.packId;
                @Nullable private final String displayName = Builder.this.displayName;
                @Nullable private final String description = Builder.this.description;
                private final Multimap<ProviderType<?>, Supplier<? extends BiConsumer<?, BlockRegistrar>>> providers = HashMultimap.create(Builder.this.providers);

                @Override
                public String modId() {
                    return modId;
                }

                @Override
                public String packId() {
                    return Objects.requireNonNullElseGet(packId, CtmPack.super::packId);
                }

                @Override
                public String displayName() {
                    return Objects.requireNonNullElseGet(displayName, CtmPack.super::displayName);
                }

                @Override
                public String description() {
                    return Objects.requireNonNullElseGet(description, CtmPack.super::description);
                }

                @Override
                public void provide(FeaturePackGenerator packGenerator) {
                    providers.keySet().forEach(providerType -> provide(packGenerator, providerType));
                }

                private <TProvider> void provide(FeaturePackGenerator generator, ProviderType<TProvider> providerType) {
                    generator.providing(providerType, (context, provider) -> CtmPacks.REFERENCES
                            .stream()
                            .map(Registree::blocks)
                            .forEach(blocks -> providers
                                    .get(providerType)
                                    .forEach(listener -> ((BiConsumer<TProvider, BlockRegistrar>) listener.get()).accept(provider, blocks))
                            )
                    );
                }
            };
        }
    }
}
