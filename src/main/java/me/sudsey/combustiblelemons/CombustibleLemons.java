package me.sudsey.combustiblelemons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ObjectHolder;

@Mod(CombustibleLemons.MODID)
public class CombustibleLemons {

    public static final String MODID = "combustiblelemons";

    @ObjectHolder(CombustibleLemons.MODID)
    public static class Entities {
        public static final EntityType<EntityCombustibleLemon> combustible_lemon = null;
    }


    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(
                    new ItemLemon().setRegistryName(ItemLemon.NAME),
                    new ItemCombustibleLemon().setRegistryName(ItemCombustibleLemon.NAME)
            );
        }

        @SubscribeEvent
        public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
            EntityType<?> combustibleLemon = EntityType.Builder.<EntityCombustibleLemon>create(
                    EntityCombustibleLemon::new, EntityClassification.MISC
            )
                    .size(0.25F, 0.25F)
                    .build(EntityCombustibleLemon.NAME)
                    .setRegistryName(EntityCombustibleLemon.NAME);

            event.getRegistry().register(combustibleLemon);
        }

        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(Entities.combustible_lemon,
                    manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        }
    }

}
