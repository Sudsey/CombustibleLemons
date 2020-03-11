package me.sudsey.combustiblelemons;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ItemLemon extends Item {

    public static final String NAME = "lemon";

    public ItemLemon() {
        super(new Item.Properties()
                .group(ItemGroup.FOOD)
                .food(new Food.Builder().hunger(4).saturation(0.3f).build()));
    }


    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void loadLoot(LootTableLoadEvent event) {
            if (event.getName().toString().equals("minecraft:blocks/oak_leaves")) {
                LootPool pool = LootPool.builder().addEntry(
                        TableLootEntry.builder(new ResourceLocation("combustiblelemons:inject/oak_leaves"))
                ).build();

                event.getTable().addPool(pool);
            }
        }
    }

}
