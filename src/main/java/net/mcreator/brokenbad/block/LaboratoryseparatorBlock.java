package net.mcreator.brokenbad.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Containers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.brokenbad.world.inventory.LaboratorySeparatorguiMenu;
import net.mcreator.brokenbad.block.entity.LaboratoryseparatorBlockEntity;

import io.netty.buffer.Unpooled;

import com.google.common.collect.ImmutableMap;

public class LaboratoryseparatorBlock extends Block implements EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	private final ImmutableMap<BlockState, VoxelShape> shapes = this.makeShapes();

	public LaboratoryseparatorBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	private ImmutableMap<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(3.7, 0, 3.7, 12.3, 2, 12.3), box(4, 2, 7.5, 5, 12.9, 8.5), box(11, 2, 7.5, 12, 12.9, 8.5), box(5, 11.9, 5, 11, 12.9, 11), box(5.51, 1.66, 5.51, 10.49, 3.735, 10.49), box(6.34, 3.735, 6.34, 9.66, 4.15, 9.66),
						box(6.34, 6.225, 6.34, 9.66, 6.64, 9.66), box(5.51, 4.15, 5.51, 10.49, 6.225, 10.49), box(6.34, 11.205, 6.34, 9.66, 11.62, 9.66), box(5.51, 9.13, 5.51, 10.49, 11.205, 10.49), box(5.51, 11.63, 5.51, 10.49, 13.005, 10.49),
						box(6.34, 8.715, 6.34, 9.66, 9.13, 9.66), box(5.51, 6.64, 5.51, 10.49, 8.715, 10.49));
				case NORTH -> Shapes.or(box(3.7, 0, 3.7, 12.3, 2, 12.3), box(11, 2, 7.5, 12, 12.9, 8.5), box(4, 2, 7.5, 5, 12.9, 8.5), box(5, 11.9, 5, 11, 12.9, 11), box(5.51, 1.66, 5.51, 10.49, 3.735, 10.49),
						box(6.34, 3.735, 6.34, 9.66, 4.15, 9.66), box(6.34, 6.225, 6.34, 9.66, 6.64, 9.66), box(5.51, 4.15, 5.51, 10.49, 6.225, 10.49), box(6.34, 11.205, 6.34, 9.66, 11.62, 9.66), box(5.51, 9.13, 5.51, 10.49, 11.205, 10.49),
						box(5.51, 11.63, 5.51, 10.49, 13.005, 10.49), box(6.34, 8.715, 6.34, 9.66, 9.13, 9.66), box(5.51, 6.64, 5.51, 10.49, 8.715, 10.49));
				case EAST -> Shapes.or(box(3.7, 0, 3.7, 12.3, 2, 12.3), box(7.5, 2, 11, 8.5, 12.9, 12), box(7.5, 2, 4, 8.5, 12.9, 5), box(5, 11.9, 5, 11, 12.9, 11), box(5.51, 1.66, 5.51, 10.49, 3.735, 10.49), box(6.34, 3.735, 6.34, 9.66, 4.15, 9.66),
						box(6.34, 6.225, 6.34, 9.66, 6.64, 9.66), box(5.51, 4.15, 5.51, 10.49, 6.225, 10.49), box(6.34, 11.205, 6.34, 9.66, 11.62, 9.66), box(5.51, 9.13, 5.51, 10.49, 11.205, 10.49), box(5.51, 11.63, 5.51, 10.49, 13.005, 10.49),
						box(6.34, 8.715, 6.34, 9.66, 9.13, 9.66), box(5.51, 6.64, 5.51, 10.49, 8.715, 10.49));
				case WEST -> Shapes.or(box(3.7, 0, 3.7, 12.3, 2, 12.3), box(7.5, 2, 4, 8.5, 12.9, 5), box(7.5, 2, 11, 8.5, 12.9, 12), box(5, 11.9, 5, 11, 12.9, 11), box(5.51, 1.66, 5.51, 10.49, 3.735, 10.49), box(6.34, 3.735, 6.34, 9.66, 4.15, 9.66),
						box(6.34, 6.225, 6.34, 9.66, 6.64, 9.66), box(5.51, 4.15, 5.51, 10.49, 6.225, 10.49), box(6.34, 11.205, 6.34, 9.66, 11.62, 9.66), box(5.51, 9.13, 5.51, 10.49, 11.205, 10.49), box(5.51, 11.63, 5.51, 10.49, 13.005, 10.49),
						box(6.34, 8.715, 6.34, 9.66, 9.13, 9.66), box(5.51, 6.64, 5.51, 10.49, 8.715, 10.49));
			};
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.get(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
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

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Laboratory Separator");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new LaboratorySeparatorguiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new LaboratoryseparatorBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof LaboratoryseparatorBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof LaboratoryseparatorBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}