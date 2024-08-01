package com.divinnity.coldsweat_tfc.compat;

import com.divinnity.coldsweat_tfc.coldsweat_tfc;
import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.util.math.CSMath;

import net.dries007.tfc.common.blockentities.FirepitBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class FirePitBlockTemp extends BlockTemp {

    public static final Block FIREPIT = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tfc", "firepit"));

    public FirePitBlockTemp() {

        super(FIREPIT);
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance)
    {
        float temperature = 0f;
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity != null && blockEntity instanceof FirepitBlockEntity firepit) {
            temperature = firepit.getTemperature();
        }
        double converted_temperature = Temperature.convertUnits(temperature, Temperature.Units.C, Temperature.Units.MC, true);
        return CSMath.blend(converted_temperature / 30, 0, distance, 0.5, 7);
    }
    
}