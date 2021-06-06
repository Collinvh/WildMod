package collinvht.old.wild.entity;

import collinvht.old.wild.WildMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WildMod.id, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntitySpawns {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void biomeLoad(BiomeLoadingEvent event) {
        if(event.getName() != null) {
            String location = event.getName().getPath();
            BiomeGenerationSettingsBuilder gen = event.getGeneration();

            switch (event.getCategory()) {
                case OCEAN:
                    if(location.contains("cold") || location.contains("frozen")) {
                        createSpawn(event.getSpawns(), EntityClassification.WATER_CREATURE, EntityHandler.blue_pinguin, 5, 1, 4);
                    }
                    if(location.contains("warm")) {
                        createSpawn(event.getSpawns(), EntityClassification.WATER_CREATURE, EntityHandler.nurse_shark, 5, 1, 4);
                        createSpawn(event.getSpawns(), EntityClassification.WATER_CREATURE, EntityHandler.whale_shark, 3, 1, 1);
                    }
                    break;
                case JUNGLE:
                    createSpawn(event.getSpawns(), EntityClassification.AMBIENT, EntityHandler.red_panda, 4, 6, 10);
                    createSpawn(event.getSpawns(), EntityClassification.AMBIENT, EntityHandler.beetleEntity, 22, 1, 20);
                    break;
                case FOREST:
                    if(location.contains("dark_forest")) {
                        createSpawn(event.getSpawns(), EntityClassification.CREATURE, EntityHandler.cleopard, 5, 3, 6);
                    }

                    createSpawn(event.getSpawns(), EntityClassification.AMBIENT, EntityHandler.ringneckedpheasant, 12, 1, 9);
                    break;
                case SAVANNA:
                    createSpawn(event.getSpawns(), EntityClassification.CREATURE, EntityHandler.eland, 6, 1, 4);
                    break;
                case SWAMP:
                    createSpawn(event.getSpawns(), EntityClassification.AMBIENT, EntityHandler.flyEntity, 13, 22, 80);
                    break;
                case PLAINS:
                    createSpawn(event.getSpawns(), EntityClassification.MISC, EntityHandler.beetleEntity, 12, 1, 5);
                    createSpawn(event.getSpawns(), EntityClassification.MISC, EntityHandler.flyEntity, 13, 1, 12);
                    createSpawn(event.getSpawns(), EntityClassification.AMBIENT, EntityHandler.ringneckedpheasant, 12, 1, 9);
                    break;
                default:
                    break;
            }
        }
    }

    public static void createSpawn(MobSpawnInfoBuilder builder, EntityClassification classification, EntityType<?> type, int weight, int count, int maxcount) {
        MobSpawnInfo.Spawners spawners = new MobSpawnInfo.Spawners(type, weight, count, maxcount);
        builder.withSpawner(classification, spawners);
    }
    @SubscribeEvent
    public static void onEntitySpawn(LivingSpawnEvent event) {
    }

}
