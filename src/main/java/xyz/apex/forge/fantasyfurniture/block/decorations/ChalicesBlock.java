package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedStackedBlock;

public final class ChalicesBlock extends SimpleFourWayWaterLoggedStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(6.5D, 0D, 6.5D, 9.5D, 8D, 9.5D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(9.5D, 0D, 5.5D, 12.5D, 8D, 8.5D),
			box(2D, 0D, 8D, 6D, 8D, 12D)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(10.5D, 0D, 6.5D, 13.5D, 8D, 9.5D),
			box(2D, 0D, 9D, 6D, 8D, 13D),
			box(5D, 0D, 2D, 9D, 8D, 6D)
	);

	public static final IntegerProperty CHALICES = IntegerProperty.create("chalices", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public ChalicesBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return CHALICES;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(CHALICES);
		return (count == 2 ? SHAPER_2 : count == 1 ? SHAPER_1 : SHAPER_0).get(facing);
	}
}