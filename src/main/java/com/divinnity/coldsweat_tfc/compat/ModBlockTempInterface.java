package com.divinnity.coldsweat_tfc.compat;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface ModBlockTempInterface {
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance);
}
