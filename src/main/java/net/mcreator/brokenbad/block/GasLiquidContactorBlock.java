package net.mcreator.brokenbad.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Containers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.brokenbad.world.inventory.GasLiquidContactorguiMenu;
import net.mcreator.brokenbad.block.entity.GasLiquidContactorBlockEntity;

import io.netty.buffer.Unpooled;

public class GasLiquidContactorBlock extends Block implements EntityBlock {
	private static final VoxelShape SHAPE = Shapes.or(box(1, 0, 1, 15, 2, 15), box(4, 2, 4, 12, 15, 12), box(3.7, 15, 3.7, 12.3, 16, 12.3), box(7, 2, 7, 9, 14, 9), box(11.5, 2, 3.5, 12.5, 15.9, 4.5), box(3.5, 2, 11.5, 4.5, 15.9, 12.5),
			box(11.5, 2, 11.5, 12.5, 15.9, 12.5), box(3.5, 2, 3.5, 4.5, 15.9, 4.5), box(9, 13, 5, 11, 13.2, 9), box(5, 3, 5, 9, 3.2, 7), box(7, 3, 9, 11, 3.2, 11), box(5, 3, 7, 7, 3.2, 11), box(9, 3, 5, 11, 3.2, 9), box(7, 5, 9, 11, 5.2, 11),
			box(5, 5, 7, 7, 5.2, 11), box(5, 5, 5, 9, 5.2, 7), box(9, 5, 5, 11, 5.2, 9), box(7, 7, 9, 11, 7.2, 11), box(5, 7, 7, 7, 7.2, 11), box(5, 7, 5, 9, 7.2, 7), box(9, 7, 5, 11, 7.2, 9), box(7, 9, 9, 11, 9.2, 11), box(5, 9, 7, 7, 9.2, 11),
			box(5, 9, 5, 9, 9.2, 7), box(9, 9, 5, 11, 9.2, 9), box(7, 11, 9, 11, 11.2, 11), box(5, 11, 7, 7, 11.2, 11), box(5, 11, 5, 9, 11.2, 7), box(9, 11, 5, 11, 11.2, 9), box(7, 13, 9, 11, 13.2, 11), box(5, 13, 7, 7, 13.2, 11),
			box(5, 13, 5, 9, 13.2, 7));

	public GasLiquidContactorBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
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
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Gas-Liquid Contactor");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new GasLiquidContactorguiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
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
		return new GasLiquidContactorBlockEntity(pos, state);
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
			if (blockEntity instanceof GasLiquidContactorBlockEntity be) {
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
		if (tileentity instanceof GasLiquidContactorBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}