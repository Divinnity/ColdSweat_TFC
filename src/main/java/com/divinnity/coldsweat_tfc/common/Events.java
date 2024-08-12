package com.divinnity.coldsweat_tfc.common;

import com.divinnity.coldsweat_tfc.compat.*;
import com.momosoftworks.coldsweat.api.event.core.BlockTempRegisterEvent;
import com.momosoftworks.coldsweat.api.event.core.TempModifierRegisterEvent;
import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;

import net.dries007.tfc.common.blockentities.CharcoalForgeBlockEntity;
import net.dries007.tfc.common.blockentities.FirepitBlockEntity;
import net.dries007.tfc.common.blockentities.GrillBlockEntity;
import net.dries007.tfc.common.blockentities.PotBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.momosoftworks.coldsweat.api.temperature.modifier.BiomeTempModifier;
import com.momosoftworks.coldsweat.api.temperature.modifier.UndergroundTempModifier;

@Mod.EventBusSubscriber
public class Events
{
    public final static TempModifier ColdSweatModifierCompatibility = new ColdSweatTempModifier().tickRate(5);

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        if (!player.level.isClientSide) {
            Temperature.removeModifiers(player, Temperature.Type.WORLD, mod -> mod instanceof BiomeTempModifier);
            Temperature.removeModifiers(player, Temperature.Type.WORLD, mod -> mod instanceof UndergroundTempModifier);
            Temperature.addModifier(player, ColdSweatModifierCompatibility, Temperature.Type.WORLD, false, com.momosoftworks.coldsweat.api.util.Temperature.Addition.BEFORE_FIRST);
        }
    }

    @SubscribeEvent
    public static void registerTempModifiers(TempModifierRegisterEvent event)
    {
        event.registerByClassName("com.divinnity.coldsweat_tfc.compat.ColdSweatTempModifier");
    }

    @SubscribeEvent
    public static void onBlockTempsRegister(BlockTempRegisterEvent event) {
        event.register(new ModBlockTemp("tfc", "charcoal_forge", 7, (level, entity, state, pos, distancce) -> {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity != null && blockEntity instanceof CharcoalForgeBlockEntity charcoalForge) {
                return charcoalForge.getTemperature() / 30;
            }
            return 0.0;
        }));
        event.register(new ModBlockTemp("tfc", "firepit", 7, (level, entity, state, pos, distancce) -> {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity != null && blockEntity instanceof FirepitBlockEntity firepit) {
                return firepit.getTemperature() / 30;
            }
            return 0.0;
        }));
        event.register(new ModBlockTemp("tfc", "grill", 7, (level, entity, state, pos, distancce) -> {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity != null && blockEntity instanceof GrillBlockEntity grill) {
                return grill.getTemperature() / 30;
            }
            return 0.0;
        }));
        event.register(new ModBlockTemp("tfc", "pot", 7, (level, entity, state, pos, distancce) -> {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity != null && blockEntity instanceof PotBlockEntity pot) {
                return pot.getTemperature() / 30;
            }
            return 0.0;
        }));
        event.register(new ModBlockTemp("tfc", "fluid/spring_water", 5f, 3));
        event.register(new ModBlockTemp("minecraft", "water", -0.1f, 4));
        event.register(new ModBlockTemp("tfc", "fluid/salt_water", -0.1f, 4));
    }
}
