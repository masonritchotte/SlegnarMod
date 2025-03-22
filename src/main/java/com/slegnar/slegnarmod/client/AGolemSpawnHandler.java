package com.slegnar.slegnarmod.client;

import com.slegnar.slegnarmod.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AGolemSpawnHandler {

    @SubscribeEvent
    public static void onAmethystClusterPlaced(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getLevel() instanceof Level level)) return;
        BlockPos placedPos = event.getPos();
        BlockState placedBlock = event.getPlacedBlock();

        // ensure event is only triggered server-side and by a player
        if (!(level instanceof ServerLevel) || !(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        // trigger is if amethyst cluster is placed (as opposed to any block)
        if (placedBlock.is(Blocks.AMETHYST_CLUSTER)) {
            checkAndSpawnGolem((ServerLevel) level, placedPos, player);
        }
    }

    private static void checkAndSpawnGolem(Level level, BlockPos pos, Player player) {
        BlockPos amethystClusterPos = pos;
        BlockPos pumpkinPos = amethystClusterPos.below();
        BlockPos amethystBlockPos = pumpkinPos.below();

        if (level.getBlockState(amethystBlockPos).is(Blocks.AMETHYST_BLOCK) && level.getBlockState(pumpkinPos).is(Blocks.CARVED_PUMPKIN)) {
            level.setBlock(amethystClusterPos, Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(pumpkinPos, Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(amethystBlockPos, Blocks.AIR.defaultBlockState(), 3);

            AmethystGolem golem = new AmethystGolem(Registration.AMETHYST_GOLEM.get(), level);
            golem.moveTo(amethystBlockPos.getX() + 0.5, amethystBlockPos.getY(), amethystBlockPos.getZ() + 0.5, 0.0F, 0.0F);
            // golem.setOwner(player);

            level.addFreshEntity(golem);
        }
    }

}
