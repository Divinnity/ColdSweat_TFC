package com.divinnity.coldsweat_tfc.compat;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockTemp extends BlockTemp {

    public float TEMP;
    public double MAXDISTANCE;
    public double CONVERTEDTEMPERATURE;
    public ModBlockTempInterface CALLBACK = null;

    public ModBlockTemp(String modId, String blockId, float temp, double maxDistance) {
        super(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modId, blockId)));
        TEMP = temp;
        MAXDISTANCE = maxDistance;
        CONVERTEDTEMPERATURE = convertTemperatureFromCelsius(temp);
    }

    public ModBlockTemp(String modId, String blockId, double maxDistance, ModBlockTempInterface callback) {
        super(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modId, blockId)));
        MAXDISTANCE = maxDistance;
        CALLBACK = callback;
    }

    public static double convertTemperatureFromCelsius(double temp) {
        return Temperature.convertUnits(temp, Temperature.Units.C, Temperature.Units.MC, true);
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance) {
        if (CALLBACK != null) {
            return CSMath.blend(convertTemperatureFromCelsius(CALLBACK.getTemperature(level, entity, state, pos, distance)), 0, distance, 0.5, MAXDISTANCE);
            
        }
        return CSMath.blend(CONVERTEDTEMPERATURE, 0, distance, 0.5, MAXDISTANCE);
    }
    
}
