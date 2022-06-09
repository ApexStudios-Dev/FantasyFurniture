package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetSofaBlock;

public final class DunmerSofaBlock extends SetSofaBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(0, 4, 1, 16, 6, 15),
			box(2, 0, 12, 4, 4, 14),
			box(2, 0, 2, 4, 4, 4),
			box(12, 0, 2, 14, 4, 4),
			box(12, 0, 12, 14, 4, 14),
			box(3, 6, 13, 13, 14, 13),
			box(13, 6, 12, 15, 16, 14),
			box(3, 14, 12, 13, 16, 14),
			box(1, 6, 12, 3, 16, 14),
			box(13, 9, 2, 15, 11, 12),
			box(13, 6, 3, 15, 9, 5),
			box(1, 6, 3, 3, 9, 5),
			box(1, 9, 2, 3, 11, 12)
	);

	public static final VoxelShape SHAPE_LEFT = VoxelShaper.or(
			box(2, 0, 12, 4, 4, 14),
			box(2, 0, 2, 4, 4, 4),
			box(12, 0, 2, 14, 4, 4),
			box(12, 0, 12, 14, 4, 14),
			box(0, 4, 1, 15, 6, 15),
			box(0, 6, 13, 13, 14, 13),
			box(13, 6, 12, 15, 16, 14),
			box(0, 14, 12, 13, 16, 14),
			box(13, 9, 2, 15, 11, 12),
			box(13, 6, 3, 15, 9, 5)
	);

	public static final VoxelShape SHAPE_CENTER = VoxelShaper.or(
			box(2, 0, 2, 4, 4, 4),
			box(12, 0, 2, 14, 4, 4),
			box(12, 0, 12, 14, 4, 14),
			box(2, 0, 12, 4, 4, 14),
			box(0, 4, 1, 16, 6, 15),
			box(0, 6, 13, 16, 14, 13),
			box(0, 14, 12, 16, 16, 14)
	);

	public static final VoxelShape SHAPE_RIGHT = VoxelShaper.or(
			box(12, 0, 12, 14, 4, 14),
			box(12, 0, 2, 14, 4, 4),
			box(2, 0, 2, 4, 4, 4),
			box(2, 0, 12, 4, 4, 14),
			box(1, 4, 1, 16, 6, 15),
			box(3, 6, 13, 16, 14, 13),
			box(1, 6, 12, 3, 16, 14),
			box(3, 14, 12, 16, 16, 14),
			box(1, 9, 2, 3, 11, 12),
			box(1, 6, 3, 3, 9, 5)
	);

	public static final VoxelShape SHAPE_CORNER = VoxelShaper.or(
			box(2, 0, 12, 4, 4, 14),
			box(2, 0, 2, 4, 4, 4),
			box(12, 0, 2, 14, 4, 4),
			box(12, 0, 12, 14, 4, 14),
			box(12, 6, 12, 14, 16, 14),
			box(12, 14, 0, 14, 16, 12),
			box(0, 14, 12, 12, 16, 14),
			box(13, 6, 0, 13, 14, 12),
			box(0, 6, 13, 12, 14, 13),
			box(1, 4, 0, 15, 6, 15),
			box(0, 4, 1, 1, 6, 15)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final VoxelShaper SHAPER_LEFT = VoxelShaper.forHorizontal(SHAPE_LEFT, Direction.NORTH);
	public static final VoxelShaper SHAPER_CENTER = VoxelShaper.forHorizontal(SHAPE_CENTER, Direction.NORTH);
	public static final VoxelShaper SHAPER_RIGHT = VoxelShaper.forHorizontal(SHAPE_RIGHT, Direction.NORTH);
	public static final VoxelShaper SHAPER_CORNER = VoxelShaper.forHorizontal(SHAPE_CORNER, Direction.NORTH);

	public DunmerSofaBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		var connectionType = blockState.getValue(CONNECTION_TYPE);

		return switch(connectionType)
				{
					case NONE -> SHAPER.get(facing);
					case LEFT -> SHAPER_LEFT.get(facing);
					case CENTER -> SHAPER_CENTER.get(facing);
					case RIGHT -> SHAPER_RIGHT.get(facing);
					case CORNER -> SHAPER_CORNER.get(facing);
				};
	}
}
