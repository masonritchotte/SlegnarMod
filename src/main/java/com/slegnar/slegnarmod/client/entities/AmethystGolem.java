package com.slegnar.slegnarmod.client.entities;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AmethystGolem extends AbstractGolem {

    // define necessary variables first (animations)
    public final AnimationState walkState = new AnimationState();
    public final AnimationState attackState = new AnimationState();

    // constructor
    public AmethystGolem(EntityType<? extends AbstractGolem> entityType, Level world) {
        super(entityType, world);
    }

    // attributes
    public static AttributeSupplier.Builder createAttributes() {
        return AbstractGolem.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
        }
    
    // goals
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.9, 32.0F));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F, 0.02F, true));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Player.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, true)); //fix this later
    
    }


    // need to override dohurttarget to trigger attack
    public boolean doHurtTarget(@Nonnull Entity pentity) {
        this.level().broadcastEntityEvent(this, (byte) 4);
        return super.doHurtTarget(pentity);
    }

    // need to add additional entity handler for attack animation
    @Override
    public void handleEntityEvent(byte id) {
        if(id == 4) {
            this.walkState.stop();
            this.attackState.start(this.tickCount);
        } else {
            super.handleEntityEvent(id);
        }
    }

    // override tick for walk animation (?)
    @Override
    public void tick() {
        super.tick();
    
        if (this.getDeltaMovement().horizontalDistanceSqr() > 0.001) { 
            if (!this.walkState.isStarted()) {
                this.walkState.start(this.tickCount);
            }
        } else {
            this.walkState.stop();
            // could add idle in here mayhaps
        }
    }   
    

    // sound effects
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource source) {
        return SoundEvents.AMETHYST_BLOCK_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState blockIn) {
        this.playSound(SoundEvents.AMETHYST_BLOCK_STEP, 1.0F, 1.0F);
    }

    protected SoundEvent getAttackSound() {
        return SoundEvents.AMETHYST_BLOCK_PLACE;
    }
}
