package com.divinnity.hardrock.compat;

import java.lang.Double;
import com.momosoftworks.coldsweat.api.temperature.modifier.BiomeTempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.minecraft.world.entity.LivingEntity;
import net.dries007.tfc.util.climate.Climate;

import java.util.function.Function;

/**
 * Special TempModifier class for TerraFirmaCraft
 */
public class ColdSweatTempModifier extends BiomeTempModifier
{
    public ColdSweatTempModifier() {
        super();
    }

    public ColdSweatTempModifier(int samples) {
        super(samples);
    }

    @Override
    public Function<Double, Double> calculate(LivingEntity entity, Temperature.Type trait)
    {
        float actual_temperature = Climate.getTemperature(entity.level, entity.getOnPos());
        return temp -> Temperature.convertUnits(actual_temperature, Temperature.Units.C, Temperature.Units.MC, true);
    }
}