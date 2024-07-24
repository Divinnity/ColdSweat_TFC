package com.divinnity.hardrock;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Hardrock.MOD_ID)
public class Hardrock
{
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "hardrock";

    public Hardrock()
    {
        MinecraftForge.EVENT_BUS.register(this);

        if (ModList.get().isLoaded("cold_sweat")) {
            LOGGER.info("ColdSweat detected and loaded.");
        }
    }


}
