package collinvht.wild.entity.obj.builder;

import collinvht.old.wild.entity.EntityHandler;
import collinvht.old.wild.entity.entities.RedPandaEntity;
import collinvht.wild.WildMod;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;

public class WildEntity<T extends Entity> {
    private final EntityType.IFactory<T> entity;
    private final WildBuilder builder;
    private EntityType<T> entityType;
    private final String name;

    public WildEntity(String name, EntityType.IFactory<T> factoryIn, WildBuilder builder) {
        this.entity = factoryIn;
        this.builder = builder;
        this.name = name;
        build();
    }

    private void build() {
        Block[] blocks = builder.getField_233603_c_().toArray(new Block[0]);

        EntityType.Builder<T> entityBuilder = EntityType.Builder.create(entity, builder.getClassification()).func_233608_b_(builder.getField_233605_i_()).func_233607_a_(blocks).setTrackingRange(builder.getTrackingRange()).size(builder.getSize().width, builder.getSize().height);
        if(builder.isField_225436_f()) {
            entityBuilder.func_225435_d();
        }
        if(builder.isSerializable()) {
            entityBuilder.disableSerialization();
        }
        if(builder.isSummonable()) {
            entityBuilder.disableSummoning();
        }
        if(builder.isImmuneToFire()) {
            entityBuilder.immuneToFire();
        }

        entityType = entityBuilder.build(name);

        WildMod.register(ForgeRegistries.ENTITIES, name, entityType);
        WildMod.register(ForgeRegistries.ITEMS, name + "_spawnegg", new SpawnEggItem(entityType, 0, 0, new Item.Properties().group(ItemGroup.MISC)));
    }

    public EntityType<T> getEntityType() {
        return entityType;
    }

    public WildBuilder getBuilder() {
        return builder;
    }

    public String getName() {
        return name;
    }
}
