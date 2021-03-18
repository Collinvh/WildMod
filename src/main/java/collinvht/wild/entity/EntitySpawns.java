package collinvht.wild.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.biome.Biome;

@Mod.EventBusSubscriber
public class EntitySpawns {
    @SubscribeEvent
    public static void biomeLoad(BiomeLoadingEvent event) {
        ResourceLocation biome = event.getName();
        MobSpawnInfoBuilder spawns = event.getSpawns();

        switch (event.getCategory()) {
            case SWAMP:
            case FOREST:
            case SAVANNA:
            case PLAINS:
            case JUNGLE:
                spawns.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityHandler.beetleEntity, 20, 4, 22));
        }
    }
}
