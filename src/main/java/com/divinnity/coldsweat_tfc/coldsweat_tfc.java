package com.divinnity.coldsweat_tfc;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(coldsweat_tfc.MOD_ID)
public class coldsweat_tfc
{
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "coldsweat_tfc";

    public coldsweat_tfc()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
