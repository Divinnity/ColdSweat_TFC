package com.divinnity.hardrock.common;

import com.divinnity.hardrock.Hardrock;
import com.divinnity.hardrock.compat.ColdSweatTempModifier;
import com.momosoftworks.coldsweat.api.event.core.TempModifierRegisterEvent;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Events
{
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        Player player = event.player;
        if (!player.level.isClientSide && event.phase == TickEvent.Phase.START) {
            if (player.tickCount % 5 == 0) {
                Temperature.addModifier(player, new ColdSweatTempModifier().tickRate(5), Temperature.Type.WORLD, false);
            }
        }
    }

    @SubscribeEvent
    public static void registerTempModifiers(TempModifierRegisterEvent event)
    {
        event.registerByClassName("com.divinnity.hardrock.compat.ColdSweatTempModifier");
    }
}
