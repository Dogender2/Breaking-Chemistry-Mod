package net.mcreator.brokenbad.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.SectionPos;

import net.mcreator.brokenbad.procedures.OpenGUI1ContactorProcedure;
import net.mcreator.brokenbad.procedures.ContactorRecipe2Procedure;
import net.mcreator.brokenbad.BreakingChemistryMod;

@EventBusSubscriber
public record GasLiquidContactorgui2ButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<GasLiquidContactorgui2ButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(BreakingChemistryMod.MODID, "gas_liquid_contactorgui_2_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, GasLiquidContactorgui2ButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, GasLiquidContactorgui2ButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new GasLiquidContactorgui2ButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<GasLiquidContactorgui2ButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final GasLiquidContactorgui2ButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.getChunkSource().hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z)))
			return;
		if (buttonID == 0) {

			ContactorRecipe2Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			OpenGUI1ContactorProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		BreakingChemistryMod.addNetworkMessage(GasLiquidContactorgui2ButtonMessage.TYPE, GasLiquidContactorgui2ButtonMessage.STREAM_CODEC, GasLiquidContactorgui2ButtonMessage::handleData);
	}
}