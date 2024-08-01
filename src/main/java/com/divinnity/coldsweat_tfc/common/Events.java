package com.divinnity.coldsweat_tfc.common;

import com.divinnity.coldsweat_tfc.compat.CharcoalForgeBlockTemp;
import com.divinnity.coldsweat_tfc.compat.ColdSweatTempModifier;
import com.momosoftworks.coldsweat.api.event.core.BlockTempRegisterEvent;
import com.momosoftworks.coldsweat.api.event.core.TempModifierRegisterEvent;
import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.momosoftworks.coldsweat.api.temperature.modifier.BiomeTempModifier;

@Mod.EventBusSubscriber
public class Events
{
    public final static TempModifier ColdSweatModifierCompatibility = new ColdSweatTempModifier().tickRate(5);

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        if (!player.level.isClientSide) {
            Temperature.removeModifiers(player, Temperature.Type.WORLD, mod -> mod instanceof BiomeTempModifier);
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
        event.register(new CharcoalForgeBlockTemp());
    }
}
