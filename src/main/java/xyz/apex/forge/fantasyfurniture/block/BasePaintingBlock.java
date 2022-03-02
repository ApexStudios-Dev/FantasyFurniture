package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.BlockVoxelShape;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BasePaintingBlock extends SimpleFourWayBlock
{
	public BasePaintingBlock(Properties properties)
	{
		super(properties);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		World level = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();

		for(Direction facing : ctx.getNearestLookingDirections())
		{
			Direction opposite = facing.getOpposite();

			if(canSupportPainting(level, pos, facing))
				return defaultBlockState().setValue(FACING, opposite);
		}

		return null;
	}

	@Override
	public boolean canSurvive(BlockState blockState, IWorldReader level, BlockPos pos)
	{
		return canSupportPainting(level, pos);
	}

	public static boolean canSupportPainting(IBlockReader level, BlockPos pos)
	{
		for(Direction facing : Direction.Plane.HORIZONTAL)
		{
			if(canSupportPainting(level, pos, facing))
				return true;
		}

		return false;
	}

	public static boolean canSupportPainting(IBlockReader level, BlockPos pos, Direction facing)
	{
		if(!facing.getAxis().isHorizontal())
			return false;

		BlockPos facingPos = pos.relative(facing);
		return level.getBlockState(facingPos).isFaceSturdy(level, facingPos, facing.getOpposite(), BlockVoxelShape.RIGID);
	}
}
