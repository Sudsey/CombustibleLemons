package me.sudsey.combustiblelemons;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemCombustibleLemon extends Item {

    public static final String NAME = "combustible_lemon";


    public ItemCombustibleLemon() {
        super(new Item.Properties().group(ItemGroup.COMBAT));
    }


    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, @Nonnull Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);

        if (!worldIn.isRemote) {
            EntityCombustibleLemon entity = new EntityCombustibleLemon(worldIn, playerIn);
            entity.func_213884_b(itemStack);
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
            worldIn.addEntity(entity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemStack.shrink(1);
        }

        return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
    }

}
