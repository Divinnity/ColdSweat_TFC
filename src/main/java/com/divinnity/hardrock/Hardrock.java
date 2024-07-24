package com.divinnity.hardrock;

import net.minecraftforge.fml.ModList;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Hardrock.MOD_ID)
public class Hardrock
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final String MOD_ID = "hardrock";

    public Hardrock()
    {
        MinecraftForge.EVENT_BUS.register(this);

        //IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        if (ModList.get().isLoaded("cold_sweat")) {
            LOGGER.info("ColdSweat detected and loaded.");
        }
    }
}
