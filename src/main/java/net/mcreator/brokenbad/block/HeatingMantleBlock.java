package net.mcreator.brokenbad.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.brokenbad.world.inventory.HeatingMantleGUIMenu;
import net.mcreator.brokenbad.procedures.HeatingMantleOnTickUpdateProcedure;
import net.mcreator.brokenbad.block.entity.HeatingMantleBlockEntity;

import io.netty.buffer.Unpooled;

import com.google.common.collect.ImmutableMap;

public class HeatingMantleBlock extends Block implements EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty MANTLE_BOTTLE = BooleanProperty.create("mantle_bottle");
	public static final BooleanProperty MANTLE_CONDENSER = BooleanProperty.create("mantle_condenser");
	private final ImmutableMap<BlockState, VoxelShape> shapes = this.makeShapes();

	public HeatingMantleBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops().noOcclusion().pushReaction(PushReaction.BLOCK).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(MANTLE_BOTTLE, false).setValue(MANTLE_CONDENSER, false));
	}

	private ImmutableMap<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(MANTLE_BOTTLE) == true && state.getValue(MANTLE_CONDENSER) == false) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(6, 4, 6, 10, 5, 10), box(6, 9, 6, 10, 10, 10), box(7.05, 10, 7.05, 8.95, 14, 8.95), box(6.95, 14, 6.95, 9.05, 15, 9.05), box(6, 5, 10, 10, 9, 11), box(6, 5, 5, 10, 9, 6), box(5, 5, 6, 6, 9, 10),
							box(10, 5, 6, 11, 9, 10), box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(4, 3, 4, 5, 8, 12), box(11, 3, 4, 12, 8, 12), box(5, 3, 11, 11, 8, 12), box(5, 3, 4, 11, 8, 5), box(5, 0, 10, 6, 1, 11),
							box(5, 0, 5, 6, 1, 6), box(10, 0, 10, 11, 1, 11), box(10, 0, 5, 11, 1, 6), box(6, 4, 12, 7, 5, 12.75), box(8, 4, 12, 9, 5, 12.75));
					case NORTH -> Shapes.or(box(6, 4, 6, 10, 5, 10), box(6, 9, 6, 10, 10, 10), box(7.05, 10, 7.05, 8.95, 14, 8.95), box(6.95, 14, 6.95, 9.05, 15, 9.05), box(6, 5, 5, 10, 9, 6), box(6, 5, 10, 10, 9, 11), box(10, 5, 6, 11, 9, 10),
							box(5, 5, 6, 6, 9, 10), box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(11, 3, 4, 12, 8, 12), box(4, 3, 4, 5, 8, 12), box(5, 3, 4, 11, 8, 5), box(5, 3, 11, 11, 8, 12), box(10, 0, 5, 11, 1, 6),
							box(10, 0, 10, 11, 1, 11), box(5, 0, 5, 6, 1, 6), box(5, 0, 10, 6, 1, 11), box(9, 4, 3.25, 10, 5, 4), box(7, 4, 3.25, 8, 5, 4));
					case EAST -> Shapes.or(box(6, 4, 6, 10, 5, 10), box(6, 9, 6, 10, 10, 10), box(7.05, 10, 7.05, 8.95, 14, 8.95), box(6.95, 14, 6.95, 9.05, 15, 9.05), box(10, 5, 6, 11, 9, 10), box(5, 5, 6, 6, 9, 10), box(6, 5, 10, 10, 9, 11),
							box(6, 5, 5, 10, 9, 6), box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(4, 3, 11, 12, 8, 12), box(4, 3, 4, 12, 8, 5), box(11, 3, 5, 12, 8, 11), box(4, 3, 5, 5, 8, 11), box(10, 0, 10, 11, 1, 11),
							box(5, 0, 10, 6, 1, 11), box(10, 0, 5, 11, 1, 6), box(5, 0, 5, 6, 1, 6), box(12, 4, 9, 12.75, 5, 10), box(12, 4, 7, 12.75, 5, 8));
					case WEST -> Shapes.or(box(6, 4, 6, 10, 5, 10), box(6, 9, 6, 10, 10, 10), box(7.05, 10, 7.05, 8.95, 14, 8.95), box(6.95, 14, 6.95, 9.05, 15, 9.05), box(5, 5, 6, 6, 9, 10), box(10, 5, 6, 11, 9, 10), box(6, 5, 5, 10, 9, 6),
							box(6, 5, 10, 10, 9, 11), box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(4, 3, 4, 12, 8, 5), box(4, 3, 11, 12, 8, 12), box(4, 3, 5, 5, 8, 11), box(11, 3, 5, 12, 8, 11), box(5, 0, 5, 6, 1, 6),
							box(10, 0, 5, 11, 1, 6), box(5, 0, 10, 6, 1, 11), box(10, 0, 10, 11, 1, 11), box(3.25, 4, 6, 4, 5, 7), box(3.25, 4, 8, 4, 5, 9));
				};
			} else if (state.getValue(MANTLE_BOTTLE) == true && state.getValue(MANTLE_CONDENSER) == true) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(6, 4, 6, 10, 5, 10), box(6, 9, 6, 10, 10, 10), box(7.05, 10, 7.05, 8.95, 14, 8.95), box(6.95, 14, 6.95, 9.05, 15, 9.05), box(6, 5, 10, 10, 9, 11), box(6, 5, 5, 10, 9, 6), box(5, 5, 6, 6, 9, 10),
							box(10, 5, 6, 11, 9, 10), box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(4, 3, 4, 5, 8, 12), box(11, 3, 4, 12, 8, 12), box(5, 3, 11, 11, 8, 12), box(5, 3, 4, 11, 8, 5), box(5, 0, 10, 6, 1, 11),
							box(5, 0, 5, 6, 1, 6), box(10, 0, 10, 11, 1, 11), box(10, 0, 5, 11, 1, 6), box(6, 4, 12, 7, 5, 12.75), box(8, 4, 12, 9, 5, 12.75), box(7, 15.5, 7, 9, 22, 9), box(7.5, 14, 7.5, 8.5, 15.5, 8.5),
							box(7.5, 22, 7.5, 8.5, 23.5, 8.5), box(7.4, 23.5, 7.4, 8.6, 23.6, 8.6), box(9, 20.3, 7.75, 9.6, 20.8, 8.25), box(9.6, 20.2, 7.65, 9.7, 20.9, 8.35), box(9.6, 16.2, 7.65, 9.7, 16.9, 8.35),
							box(9, 16.3, 7.75, 9.6, 16.8, 8.25), box(8.6, 14.7, 7.95, 8.9, 16, 8.25), box(8.3, 15.6, 7.25, 8.6, 15.9, 8.15), box(7.4, 16.3, 7.35, 8.6, 16.6, 7.65), box(7.4, 16, 7.55, 7.7, 16.3, 8.75),
							box(7.4, 16.7, 8.35, 8.6, 17, 8.65), box(8.3, 16.4, 7.25, 8.6, 16.7, 8.45), box(7.4, 17.1, 7.35, 8.6, 17.4, 7.65), box(7.4, 16.8, 7.55, 7.7, 17.1, 8.75), box(7.4, 17.5, 8.35, 8.6, 17.8, 8.65),
							box(8.3, 17.2, 7.25, 8.6, 17.5, 8.45), box(7.4, 17.9, 7.35, 8.6, 18.2, 7.65), box(7.4, 17.6, 7.55, 7.7, 17.9, 8.75), box(7.4, 18.3, 8.35, 8.6, 18.6, 8.65), box(8.3, 18, 7.25, 8.6, 18.3, 8.45),
							box(7.4, 18.7, 7.35, 8.6, 19, 7.65), box(7.4, 18.4, 7.55, 7.7, 18.7, 8.75), box(7.4, 19.1, 8.35, 8.6, 19.4, 8.65), box(8.3, 18.8, 7.25, 8.6, 19.1, 8.45), box(7.4, 19.5, 7.35, 8.6, 19.8, 7.65),
							box(7.4, 19.2, 7.55, 7.7, 19.5, 8.75), box(7.4, 19.9, 8.35, 8.6, 20.2, 8.65), box(8.3, 19.6, 7.25, 8.6, 19.9, 8.45), box(7.4, 20.3, 7.35, 8.6, 20.6, 7.65), box(7.4, 20, 7.55, 7.7, 20.3, 8.75),
							box(7.4, 20.7, 8.35, 8.6, 21, 8.65), box(8.3, 20.4, 7.25, 8.6, 20.7, 8.45), box(7.4, 21.1, 7.35, 8.6, 21.4, 7.65), box(7.4, 21.4, 7.85, 7.7, 22.6, 8.15), box(7.4, 20.8, 7.55, 7.7, 21.1, 8.25),
							box(7.75, 23.4, 7.75, 8.25, 24.4, 8.25), box(11.05, 4.9, 7.75, 11.55, 23.9, 8.25), box(8.25, 23.9, 7.75, 11.55, 24.4, 8.25));
					case NORTH -> Shapes.or(box(6, 4, 6, 10, 5, 10), box(6, 9, 6, 10, 10, 10), box(7.05, 10, 7.05, 8.95, 14, 8.95), box(6.95, 14, 6.95, 9.05, 15, 9.05), box(6, 5, 5, 10, 9, 6), box(6, 5, 10, 10, 9, 11), box(10, 5, 6, 11, 9, 10),
							box(5, 5, 6, 6, 9, 10), box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(11, 3, 4, 12, 8, 12), box(4, 3, 4, 5, 8, 12), box(5, 3, 4, 11, 8, 5), box(5, 3, 11, 11, 8, 12), box(10, 0, 5, 11, 1, 6),
							box(10, 0, 10, 11, 1, 11), box(5, 0, 5, 6, 1, 6), box(5, 0, 10, 6, 1, 11), box(9, 4, 3.25, 10, 5, 4), box(7, 4, 3.25, 8, 5, 4), box(7, 15.5, 7, 9, 22, 9), box(7.5, 14, 7.5, 8.5, 15.5, 8.5),
							box(7.5, 22, 7.5, 8.5, 23.5, 8.5), box(7.4, 23.5, 7.4, 8.6, 23.6, 8.6), box(6.4, 20.3, 7.75, 7, 20.8, 8.25), box(6.3, 20.2, 7.65, 6.4, 20.9, 8.35), box(6.3, 16.2, 7.65, 6.4, 16.9, 8.35),
							box(6.4, 16.3, 7.75, 7, 16.8, 8.25), box(7.1, 14.7, 7.75, 7.4, 16, 8.05), box(7.4, 15.6, 7.85, 7.7, 15.9, 8.75), box(7.4, 16.3, 8.35, 8.6, 16.6, 8.65), box(8.3, 16, 7.25, 8.6, 16.3, 8.45),
							box(7.4, 16.7, 7.35, 8.6, 17, 7.65), box(7.4, 16.4, 7.55, 7.7, 16.7, 8.75), box(7.4, 17.1, 8.35, 8.6, 17.4, 8.65), box(8.3, 16.8, 7.25, 8.6, 17.1, 8.45), box(7.4, 17.5, 7.35, 8.6, 17.8, 7.65),
							box(7.4, 17.2, 7.55, 7.7, 17.5, 8.75), box(7.4, 17.9, 8.35, 8.6, 18.2, 8.65), box(8.3, 17.6, 7.25, 8.6, 17.9, 8.45), box(7.4, 18.3, 7.35, 8.6, 18.6, 7.65), box(7.4, 18, 7.55, 7.7, 18.3, 8.75),
							box(7.4, 18.7, 8.35, 8.6, 19, 8.65), box(8.3, 18.4, 7.25, 8.6, 18.7, 8.45), box(7.4, 19.1, 7.35, 8.6, 19.4, 7.65), box(7.4, 18.8, 7.55, 7.7, 19.1, 8.75), box(7.4, 19.5, 8.35, 8.6, 19.8, 8.65),
							box(8.3, 19.2, 7.25, 8.6, 19.5, 8.45), box(7.4, 19.9, 7.35, 8.6, 20.2, 7.65), box(7.4, 19.6, 7.55, 7.7, 19.9, 8.75), box(7.4, 20.3, 8.35, 8.6, 20.6, 8.65), box(8.3, 20, 7.25, 8.6, 20.3, 8.45),
							box(7.4, 20.7, 7.35, 8.6, 21, 7.65), box(7.4, 20.4, 7.55, 7.7, 20.7, 8.75), box(7.4, 21.1, 8.35, 8.6, 21.4, 8.65), box(8.3, 21.4, 7.85, 8.6, 22.6, 8.15), box(8.3, 20.8, 7.75, 8.6, 21.1, 8.45),
							box(7.75, 23.4, 7.75, 8.25, 24.4, 8.25), box(4.45, 4.9, 7.75, 4.95, 23.9, 8.25), box(4.45, 23.9, 7.75, 7.75, 24.4, 8.25));
					case EAST -> Shapes.or(box(6, 4, 6, 10, 5, 10), box(6, 9, 6, 10, 10, 10), box(7.05, 10, 7.05, 8.95, 14, 8.95), box(6.95, 14, 6.95, 9.05, 15, 9.05), box(10, 5, 6, 11, 9, 10), box(5, 5, 6, 6, 9, 10), box(6, 5, 10, 10, 9, 11),
							box(6, 5, 5, 10, 9, 6), box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(4, 3, 11, 12, 8, 12), box(4, 3, 4, 12, 8, 5), box(11, 3, 5, 12, 8, 11), box(4, 3, 5, 5, 8, 11), box(10, 0, 10, 11, 1, 11),
							box(5, 0, 10, 6, 1, 11), box(10, 0, 5, 11, 1, 6), box(5, 0, 5, 6, 1, 6), box(12, 4, 9, 12.75, 5, 10), box(12, 4, 7, 12.75, 5, 8), box(7, 15.5, 7, 9, 22, 9), box(7.5, 14, 7.5, 8.5, 15.5, 8.5),
							box(7.5, 22, 7.5, 8.5, 23.5, 8.5), box(7.4, 23.5, 7.4, 8.6, 23.6, 8.6), box(7.75, 20.3, 6.4, 8.25, 20.8, 7), box(7.65, 20.2, 6.3, 8.35, 20.9, 6.4), box(7.65, 16.2, 6.3, 8.35, 16.9, 6.4),
							box(7.75, 16.3, 6.4, 8.25, 16.8, 7), box(7.95, 14.7, 7.1, 8.25, 16, 7.4), box(7.25, 15.6, 7.4, 8.15, 15.9, 7.7), box(7.35, 16.3, 7.4, 7.65, 16.6, 8.6), box(7.55, 16, 8.3, 8.75, 16.3, 8.6),
							box(8.35, 16.7, 7.4, 8.65, 17, 8.6), box(7.25, 16.4, 7.4, 8.45, 16.7, 7.7), box(7.35, 17.1, 7.4, 7.65, 17.4, 8.6), box(7.55, 16.8, 8.3, 8.75, 17.1, 8.6), box(8.35, 17.5, 7.4, 8.65, 17.8, 8.6),
							box(7.25, 17.2, 7.4, 8.45, 17.5, 7.7), box(7.35, 17.9, 7.4, 7.65, 18.2, 8.6), box(7.55, 17.6, 8.3, 8.75, 17.9, 8.6), box(8.35, 18.3, 7.4, 8.65, 18.6, 8.6), box(7.25, 18, 7.4, 8.45, 18.3, 7.7),
							box(7.35, 18.7, 7.4, 7.65, 19, 8.6), box(7.55, 18.4, 8.3, 8.75, 18.7, 8.6), box(8.35, 19.1, 7.4, 8.65, 19.4, 8.6), box(7.25, 18.8, 7.4, 8.45, 19.1, 7.7), box(7.35, 19.5, 7.4, 7.65, 19.8, 8.6),
							box(7.55, 19.2, 8.3, 8.75, 19.5, 8.6), box(8.35, 19.9, 7.4, 8.65, 20.2, 8.6), box(7.25, 19.6, 7.4, 8.45, 19.9, 7.7), box(7.35, 20.3, 7.4, 7.65, 20.6, 8.6), box(7.55, 20, 8.3, 8.75, 20.3, 8.6),
							box(8.35, 20.7, 7.4, 8.65, 21, 8.6), box(7.25, 20.4, 7.4, 8.45, 20.7, 7.7), box(7.35, 21.1, 7.4, 7.65, 21.4, 8.6), box(7.85, 21.4, 8.3, 8.15, 22.6, 8.6), box(7.55, 20.8, 8.3, 8.25, 21.1, 8.6),
							box(7.75, 23.4, 7.75, 8.25, 24.4, 8.25), box(7.75, 4.9, 4.45, 8.25, 23.9, 4.95), box(7.75, 23.9, 4.45, 8.25, 24.4, 7.75));
					case WEST -> Shapes.or(box(6, 4, 6, 10, 5, 10), box(6, 9, 6, 10, 10, 10), box(7.05, 10, 7.05, 8.95, 14, 8.95), box(6.95, 14, 6.95, 9.05, 15, 9.05), box(5, 5, 6, 6, 9, 10), box(10, 5, 6, 11, 9, 10), box(6, 5, 5, 10, 9, 6),
							box(6, 5, 10, 10, 9, 11), box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(4, 3, 4, 12, 8, 5), box(4, 3, 11, 12, 8, 12), box(4, 3, 5, 5, 8, 11), box(11, 3, 5, 12, 8, 11), box(5, 0, 5, 6, 1, 6),
							box(10, 0, 5, 11, 1, 6), box(5, 0, 10, 6, 1, 11), box(10, 0, 10, 11, 1, 11), box(3.25, 4, 6, 4, 5, 7), box(3.25, 4, 8, 4, 5, 9), box(7, 15.5, 7, 9, 22, 9), box(7.5, 14, 7.5, 8.5, 15.5, 8.5),
							box(7.5, 22, 7.5, 8.5, 23.5, 8.5), box(7.4, 23.5, 7.4, 8.6, 23.6, 8.6), box(7.75, 20.3, 9, 8.25, 20.8, 9.6), box(7.65, 20.2, 9.6, 8.35, 20.9, 9.7), box(7.65, 16.2, 9.6, 8.35, 16.9, 9.7),
							box(7.75, 16.3, 9, 8.25, 16.8, 9.6), box(7.75, 14.7, 8.6, 8.05, 16, 8.9), box(7.85, 15.6, 8.3, 8.75, 15.9, 8.6), box(8.35, 16.3, 7.4, 8.65, 16.6, 8.6), box(7.25, 16, 7.4, 8.45, 16.3, 7.7),
							box(7.35, 16.7, 7.4, 7.65, 17, 8.6), box(7.55, 16.4, 8.3, 8.75, 16.7, 8.6), box(8.35, 17.1, 7.4, 8.65, 17.4, 8.6), box(7.25, 16.8, 7.4, 8.45, 17.1, 7.7), box(7.35, 17.5, 7.4, 7.65, 17.8, 8.6),
							box(7.55, 17.2, 8.3, 8.75, 17.5, 8.6), box(8.35, 17.9, 7.4, 8.65, 18.2, 8.6), box(7.25, 17.6, 7.4, 8.45, 17.9, 7.7), box(7.35, 18.3, 7.4, 7.65, 18.6, 8.6), box(7.55, 18, 8.3, 8.75, 18.3, 8.6),
							box(8.35, 18.7, 7.4, 8.65, 19, 8.6), box(7.25, 18.4, 7.4, 8.45, 18.7, 7.7), box(7.35, 19.1, 7.4, 7.65, 19.4, 8.6), box(7.55, 18.8, 8.3, 8.75, 19.1, 8.6), box(8.35, 19.5, 7.4, 8.65, 19.8, 8.6),
							box(7.25, 19.2, 7.4, 8.45, 19.5, 7.7), box(7.35, 19.9, 7.4, 7.65, 20.2, 8.6), box(7.55, 19.6, 8.3, 8.75, 19.9, 8.6), box(8.35, 20.3, 7.4, 8.65, 20.6, 8.6), box(7.25, 20, 7.4, 8.45, 20.3, 7.7),
							box(7.35, 20.7, 7.4, 7.65, 21, 8.6), box(7.55, 20.4, 8.3, 8.75, 20.7, 8.6), box(8.35, 21.1, 7.4, 8.65, 21.4, 8.6), box(7.85, 21.4, 7.4, 8.15, 22.6, 7.7), box(7.75, 20.8, 7.4, 8.45, 21.1, 7.7),
							box(7.75, 23.4, 7.75, 8.25, 24.4, 8.25), box(7.75, 4.9, 11.05, 8.25, 23.9, 11.55), box(7.75, 23.9, 8.25, 8.25, 24.4, 11.55));
				};
			}
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(4, 3, 4, 5, 8, 12), box(11, 3, 4, 12, 8, 12), box(5, 3, 11, 11, 8, 12), box(5, 3, 4, 11, 8, 5), box(5, 0, 10, 6, 1, 11), box(5, 0, 5, 6, 1, 6),
						box(10, 0, 10, 11, 1, 11), box(10, 0, 5, 11, 1, 6), box(6, 4, 12, 7, 5, 12.75), box(8, 4, 12, 9, 5, 12.75));
				case NORTH -> Shapes.or(box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(11, 3, 4, 12, 8, 12), box(4, 3, 4, 5, 8, 12), box(5, 3, 4, 11, 8, 5), box(5, 3, 11, 11, 8, 12), box(10, 0, 5, 11, 1, 6), box(10, 0, 10, 11, 1, 11),
						box(5, 0, 5, 6, 1, 6), box(5, 0, 10, 6, 1, 11), box(9, 4, 3.25, 10, 5, 4), box(7, 4, 3.25, 8, 5, 4));
				case EAST -> Shapes.or(box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(4, 3, 11, 12, 8, 12), box(4, 3, 4, 12, 8, 5), box(11, 3, 5, 12, 8, 11), box(4, 3, 5, 5, 8, 11), box(10, 0, 10, 11, 1, 11), box(5, 0, 10, 6, 1, 11),
						box(10, 0, 5, 11, 1, 6), box(5, 0, 5, 6, 1, 6), box(12, 4, 9, 12.75, 5, 10), box(12, 4, 7, 12.75, 5, 8));
				case WEST -> Shapes.or(box(4, 2, 4, 12, 3, 12), box(4.5, 1, 4.5, 11.5, 2, 11.5), box(4, 3, 4, 12, 8, 5), box(4, 3, 11, 12, 8, 12), box(4, 3, 5, 5, 8, 11), box(11, 3, 5, 12, 8, 11), box(5, 0, 5, 6, 1, 6), box(10, 0, 5, 11, 1, 6),
						box(5, 0, 10, 6, 1, 11), box(10, 0, 10, 11, 1, 11), box(3.25, 4, 6, 4, 5, 7), box(3.25, 4, 8, 4, 5, 9));
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
		builder.add(FACING, MANTLE_BOTTLE, MANTLE_CONDENSER);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(MANTLE_BOTTLE, false).setValue(MANTLE_CONDENSER, false);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 20);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		HeatingMantleOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 20);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Heating Mantle");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new HeatingMantleGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
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
		return new HeatingMantleBlockEntity(pos, state);
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
			if (blockEntity instanceof HeatingMantleBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}
}