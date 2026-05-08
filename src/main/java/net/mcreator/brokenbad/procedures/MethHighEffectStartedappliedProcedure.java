package net.mcreator.brokenbad.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.brokenbad.network.BreakingChemistryModVariables;

public class MethHighEffectStartedappliedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			BreakingChemistryModVariables.PlayerVariables _vars = entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES);
			_vars.defaultmethplayercoords = new Vec3((entity.getX()), (entity.getY()), (entity.getZ()));
			_vars.showMethOverlay = true;
			_vars.markSyncDirty();
		}
		if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
			ResourceKey<Level> destinationType = ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("breaking_chemistry:methhighdimension"));
			if (_player.level().dimension() == destinationType)
				return;
			ServerLevel nextLevel = _player.server.getLevel(destinationType);
			if (nextLevel != null) {
				_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
				_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
				_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
				for (MobEffectInstance _effectinstance : _player.getActiveEffects())
					_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, false));
				_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
			}
		}
		if (entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES).visitedMethWorld == true) {
			{
				Entity _ent = entity;
				_ent.teleportTo((entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES).methWorldCoords.x()), (entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES).methWorldCoords.y()),
						(entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES).methWorldCoords.z()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES).methWorldCoords.x()), (entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES).methWorldCoords.y()),
							(entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES).methWorldCoords.z()), _ent.getYRot(), _ent.getXRot());
			}
		} else {
			{
				Entity _ent = entity;
				_ent.teleportTo(0, 200, 0);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(0, 200, 0, _ent.getYRot(), _ent.getXRot());
			}
			{
				BreakingChemistryModVariables.PlayerVariables _vars = entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES);
				_vars.visitedMethWorld = true;
				_vars.markSyncDirty();
			}
		}
	}
}