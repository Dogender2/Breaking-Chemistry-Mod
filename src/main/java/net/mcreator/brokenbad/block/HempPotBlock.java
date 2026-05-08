package net.mcreator.brokenbad.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.brokenbad.procedures.HempPotOnTickUpdateProcedure;
import net.mcreator.brokenbad.procedures.HempPotOnBlockRightclickedProcedure;
import net.mcreator.brokenbad.block.entity.HempPotBlockEntity;

import com.google.common.collect.ImmutableMap;

public class HempPotBlock extends Block implements EntityBlock {
	public static final IntegerProperty POT_STAGE = IntegerProperty.create("pot_stage", 0, 2);
	public static final IntegerProperty GROWTH = IntegerProperty.create("growth", 0, 3);
	private final ImmutableMap<BlockState, VoxelShape> shapes = this.makeShapes();

	public HempPotBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1f, 0f).requiresCorrectToolForDrops().noOcclusion().pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false)
				.instrument(NoteBlockInstrument.BASEDRUM));
		this.registerDefaultState(this.stateDefinition.any().setValue(POT_STAGE, 0).setValue(GROWTH, 0));
	}

	private ImmutableMap<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(POT_STAGE) == 1 && state.getValue(GROWTH) == 0) {
				return Shapes.or(box(2, 0, 2, 14, 8, 14), box(2, 1, 1, 14, 12, 2), box(2, 1, 14, 14, 12, 15), box(1, 1, 2, 2, 12, 14), box(14, 1, 2, 15, 12, 14), box(2, 10.5, 2, 14, 11.5, 14));
			} else if (state.getValue(POT_STAGE) == 2 && state.getValue(GROWTH) == 0) {
				return Shapes.or(box(2, 0, 2, 14, 8, 14), box(2, 1, 1, 14, 12, 2), box(2, 1, 14, 14, 12, 15), box(1, 1, 2, 2, 12, 14), box(14, 1, 2, 15, 12, 14), box(2, 10.5, 2, 14, 11.5, 14), box(6.25, 10, 7, 7.25, 12, 9),
						box(7, 9.75, 8.75, 8, 11.75, 9.75), box(7.5, 9.75, 6.25, 9.25, 11.75, 7.25), box(8.75, 10, 7, 9.75, 12, 9), box(7.5, 10, 7.5, 8.5, 12, 8.5));
			} else if (state.getValue(POT_STAGE) == 2 && state.getValue(GROWTH) == 1) {
				return Shapes.or(box(2, 0, 2, 14, 8, 14), box(2, 1, 1, 14, 12, 2), box(2, 1, 14, 14, 12, 15), box(1, 1, 2, 2, 12, 14), box(14, 1, 2, 15, 12, 14), box(2, 10.5, 2, 14, 11.5, 14), box(6.25, 10, 7, 7.25, 12, 9),
						box(7, 9.75, 8.75, 8, 11.75, 9.75), box(7.5, 9.75, 6.25, 9.25, 11.75, 7.25), box(8.75, 10, 7, 9.75, 12, 9), box(7.5, 10, 7.5, 8.5, 12, 8.5));
			} else if (state.getValue(POT_STAGE) == 2 && state.getValue(GROWTH) == 2) {
				return Shapes.or(box(1, 0, 1, 15, 16, 15), box(2, 12, 2, 14, 24, 14));
			} else if (state.getValue(POT_STAGE) == 2 && state.getValue(GROWTH) == 3) {
				return Shapes.or(box(1, 0, 1, 15, 12, 15), box(2, 12, 2, 14, 31, 14));
			}
			return Shapes.or(box(2, 0, 2, 14, 8, 14), box(2, 1, 1, 14, 12, 2), box(2, 1, 14, 14, 12, 15), box(1, 1, 2, 2, 12, 14), box(14, 1, 2, 15, 12, 14));
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
		builder.add(POT_STAGE, GROWTH);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(POT_STAGE, 0).setValue(GROWTH, 0);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 30);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		HempPotOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 30);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		HempPotOnBlockRightclickedProcedure.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HempPotBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}
}