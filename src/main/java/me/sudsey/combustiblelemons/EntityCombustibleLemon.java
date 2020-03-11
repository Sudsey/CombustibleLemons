package me.sudsey.combustiblelemons;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

public class EntityCombustibleLemon extends ProjectileItemEntity {

    public static final String NAME = "combustible_lemon";

    @ObjectHolder(CombustibleLemons.MODID)
    public static class Entities {
        public static final EntityType<EntityCombustibleLemon> combustible_lemon = null;
    }

    @ObjectHolder(CombustibleLemons.MODID)
    public static class Items {
        public static final Item combustible_lemon = null;
    }


    public EntityCombustibleLemon(EntityType<? extends EntityCombustibleLemon> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityCombustibleLemon(World worldIn, LivingEntity playerIn) {
        super(Entities.combustible_lemon, playerIn, worldIn);
    }


    @Override
    @Nonnull
    public Item func_213885_i() {
        return Items.combustible_lemon;
    }

    @Override
    public void onImpact(@Nonnull RayTraceResult result) {
        if (!this.world.isRemote) {
            this.world.createExplosion(this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 1.5F, true,
                    Explosion.Mode.BREAK);
            this.remove();
        }
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
