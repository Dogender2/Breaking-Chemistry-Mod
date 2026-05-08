package net.mcreator.brokenbad.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.google.common.collect.ImmutableMap;

public class GrowlightBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	private final ImmutableMap<BlockState, VoxelShape> shapes = this.makeShapes();

	public GrowlightBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 10f).lightLevel(blockstate -> 15).requiresCorrectToolForDrops().noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	private ImmutableMap<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(11, 8, 1.7, 15, 10, 14.3), box(1, 8, 1.7, 5, 10, 14.3), box(6, 8, 1.7, 10, 10, 14.3), box(14, 7.85, 0, 16, 9.85, 16), box(0, 7.85, 0, 2, 9.85, 16), box(2, 7.85, 14, 14, 9.85, 16), box(2, 7.85, 0, 14, 9.85, 2),
						box(0.5, 9.75, 0.5, 15.5, 10.75, 15.5), box(3, 10, 3, 5, 16, 5), box(3, 10, 11, 5, 16, 13), box(10, 10, 3, 12, 16, 5), box(10, 10, 11, 12, 16, 13));
				case NORTH -> Shapes.or(box(1, 8, 1.7, 5, 10, 14.3), box(11, 8, 1.7, 15, 10, 14.3), box(6, 8, 1.7, 10, 10, 14.3), box(0, 7.85, 0, 2, 9.85, 16), box(14, 7.85, 0, 16, 9.85, 16), box(2, 7.85, 0, 14, 9.85, 2),
						box(2, 7.85, 14, 14, 9.85, 16), box(0.5, 9.75, 0.5, 15.5, 10.75, 15.5), box(11, 10, 11, 13, 16, 13), box(11, 10, 3, 13, 16, 5), box(4, 10, 11, 6, 16, 13), box(4, 10, 3, 6, 16, 5));
				case EAST -> Shapes.or(box(1.7, 8, 1, 14.3, 10, 5), box(1.7, 8, 11, 14.3, 10, 15), box(1.7, 8, 6, 14.3, 10, 10), box(0, 7.85, 0, 16, 9.85, 2), box(0, 7.85, 14, 16, 9.85, 16), box(14, 7.85, 2, 16, 9.85, 14),
						box(0, 7.85, 2, 2, 9.85, 14), box(0.5, 9.75, 0.5, 15.5, 10.75, 15.5), box(3, 10, 11, 5, 16, 13), box(11, 10, 11, 13, 16, 13), box(3, 10, 4, 5, 16, 6), box(11, 10, 4, 13, 16, 6));
				case WEST -> Shapes.or(box(1.7, 8, 11, 14.3, 10, 15), box(1.7, 8, 1, 14.3, 10, 5), box(1.7, 8, 6, 14.3, 10, 10), box(0, 7.85, 14, 16, 9.85, 16), box(0, 7.85, 0, 16, 9.85, 2), box(0, 7.85, 2, 2, 9.85, 14),
						box(14, 7.85, 2, 16, 9.85, 14), box(0.5, 9.75, 0.5, 15.5, 10.75, 15.5), box(11, 10, 3, 13, 16, 5), box(3, 10, 3, 5, 16, 5), box(11, 10, 10, 13, 16, 12), box(3, 10, 10, 5, 16, 12));
			};
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.get(state);
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
}