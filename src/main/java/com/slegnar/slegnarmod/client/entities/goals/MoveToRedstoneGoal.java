package com.slegnar.slegnarmod.client.entities.goals;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;

public class MoveToRedstoneGoal extends MoveToBlockGoal{

    public MoveToRedstoneGoal(PathfinderMob pMob, double pSpeedModifier, int pSearchRange) {
        super(pMob, pSpeedModifier, pSearchRange);
    }

    @Override
    protected boolean isValidTarget(@Nonnull LevelReader pLevel, @Nonnull BlockPos pPos) {
        if (pLevel.getBlockState(pPos).getBlock().equals(Blocks.REDSTONE_WIRE)) {
            return true;
        } else {
            return false;
        }
    }
    
}
