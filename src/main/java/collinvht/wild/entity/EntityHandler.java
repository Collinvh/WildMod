package collinvht.wild.entity;

import collinvht.wild.WildMod;
import collinvht.wild.client.renders.*;
import collinvht.wild.entity.entities.*;
import collinvht.wild.item.ItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityHandler {
    private static final DeferredRegister<EntityType<?>> entities = DeferredRegister.create(ForgeRegistries.ENTITIES, WildMod.id);

    public static final EntityType<RedPandaEntity> red_panda = EntityType.Builder.create(RedPandaEntity::new, EntityClassification.AMBIENT).func_233608_b_(10).size(0.6F, 0.7F).trackingRange(8).setUpdateInterval(1).func_233608_b_(10).build("red_panda");
    public static final EntityType<BluePinguinEntity> blue_pinguin = EntityType.Builder.create(BluePinguinEntity::new, EntityClassification.AMBIENT).func_233608_b_(10).size(0.4F, 1F).trackingRange(8).setUpdateInterval(1).func_233608_b_(10).build("blue_pinguin");
    public static final EntityType<NurseSharkEntity> nurse_shark = EntityType.Builder.create(NurseSharkEntity::new, EntityClassification.AMBIENT).func_233608_b_(10).size(1.2F, 0.8F).trackingRange(8).setUpdateInterval(1).func_233608_b_(10).build("nurse_shark");
    public static final EntityType<WhaleSharkEntity> whale_shark = EntityType.Builder.create(WhaleSharkEntity::new, EntityClassification.AMBIENT).func_233608_b_(10).size(5F, 2F).trackingRange(12).setUpdateInterval(1).func_233608_b_(10).build("whale_shark");
    public static final EntityType<ElandEntity> eland = EntityType.Builder.create(ElandEntity::new, EntityClassification.AMBIENT).func_233608_b_(10).size(0.6F, 0.7F).trackingRange(8).setUpdateInterval(1).func_233608_b_(10).build("eland");
    public static final EntityType<CloudedLeopardEntity> cleopard = EntityType.Builder.create(CloudedLeopardEntity::new, EntityClassification.CREATURE).func_233608_b_(10).size(0.6F, 0.7F).trackingRange(8).setUpdateInterval(1).func_233608_b_(10).build("c_leopard");
    public static final EntityType<RingNeckedPheasantEntity> ringneckedpheasant = EntityType.Builder.create(RingNeckedPheasantEntity::new, EntityClassification.AMBIENT).func_233608_b_(10).size(0.6F, 0.7F).trackingRange(8).setUpdateInterval(1).func_233608_b_(10).build("rn_pheasant");

    public static void init() {
        registerEntity("red_panda",  0, 0, new Item.Properties().group(ItemGroup.BREWING), red_panda);
        registerEntity("blue_pinguin",  0, 0, new Item.Properties().group(ItemGroup.BREWING), blue_pinguin);
        registerEntity("nurse_shark",  0, 0, new Item.Properties().group(ItemGroup.BREWING), nurse_shark);
        registerEntity("whale_shark", 0,0, new Item.Properties().group(ItemGroup.BREWING), whale_shark);
        registerEntity("eland", 0,0, new Item.Properties().group(ItemGroup.BREWING), eland);
        registerEntity("c_leopard", 0,0, new Item.Properties().group(ItemGroup.BREWING), cleopard);
        registerEntity("rn_pheasant", 0,0, new Item.Properties().group(ItemGroup.BREWING), ringneckedpheasant);
    }

    @OnlyIn(Dist.CLIENT)
    public static void clientInit() {
        registerEntityRenderingHandler(red_panda, RedpandaRender::new);
        registerEntityRenderingHandler(blue_pinguin, BluePinguinRender::new);
        registerEntityRenderingHandler(nurse_shark, NurseSharkRender::new);
        registerEntityRenderingHandler(whale_shark, WhaleSharkRender::new);
        registerEntityRenderingHandler(eland, ElandRender::new);
        registerEntityRenderingHandler(cleopard, CloudedLeopardRender::new);
        registerEntityRenderingHandler(ringneckedpheasant, RingNeckedPheasantRender::new);
    }

    public static <T extends Entity> void registerEntity(String name, int color1, int color2, Item.Properties settings, EntityType<T> type)  {
        entities.register(name, () -> type);
        ItemHandler.registerItem(new SpawnEggItem(type, color1, color2, settings), name + "_spawnegg");
    }



    @OnlyIn(Dist.CLIENT)
    public static <T extends Entity> void registerEntityRenderingHandler(EntityType<T> entityClass, IRenderFactory<? super T> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }

    static {
        entities.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
