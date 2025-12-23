package dev.apexstudios.fantasyfurniture.decorations.common.plushie;

import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Unit;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class PlushieBlockEntity extends BlockEntity {
    public static final String TAG_PROFILE = "profile";
    public static final String TAG_RENDER_NAME = "render_name";

    @Nullable private ResolvableProfile profile;
    private boolean renderName = false;

    public PlushieBlockEntity(BlockPos pos, BlockState blockState) {
        super(DecorationsFurnitureModule.PLUSHIE_BLOCK_ENTITY.value(), pos, blockState);
    }

    public @Nullable ResolvableProfile getProfile() {
        return profile;
    }

    public boolean setProfile(@Nullable ResolvableProfile profile) {
        if(!Objects.equals(this.profile, profile)) {
            this.profile = profile;
            return true;
        }

        return false;
    }

    public boolean setRenderName(boolean renderName) {
        if(this.renderName != renderName) {
            this.renderName = renderName;
            return true;
        }

        return false;
    }

    public boolean shouldRenderName() {
        return renderName;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.storeNullable(TAG_PROFILE, ResolvableProfile.CODEC, profile);
        output.putBoolean(TAG_RENDER_NAME, renderName);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        profile = input.read(TAG_PROFILE, ResolvableProfile.CODEC).orElse(null);
        renderName = input.getBooleanOr(TAG_RENDER_NAME, false);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveCustomOnly(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter components) {
        super.applyImplicitComponents(components);
        profile = components.get(DataComponents.PROFILE);
        renderName = components.get(DecorationsFurnitureModule.PLUSHIE_RENDER_NAME) != null;
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.PROFILE, profile);
        components.set(DecorationsFurnitureModule.PLUSHIE_RENDER_NAME, renderName ? Unit.INSTANCE : null);
    }

    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        super.removeComponentsFromTag(output);
        output.discard(TAG_PROFILE);
        output.discard(TAG_RENDER_NAME);
    }
}
