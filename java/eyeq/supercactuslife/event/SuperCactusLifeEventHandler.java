package eyeq.supercactuslife.event;

import eyeq.supercactuslife.SuperCactusLife;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class SuperCactusLifeEventHandler {
    @SubscribeEvent
    public void onBlockHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        World world = event.getWorld();
        if(world.isRemote) {
            return;
        }
        List<ItemStack> drops = event.getDrops();
        if(drops instanceof NonNullList) {
            return;
        }
        if(world.rand.nextFloat() < SuperCactusLife.prob) {
            drops.add(new ItemStack(Blocks.CACTUS));
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        World world = entity.getEntityWorld();
        if(world.isRemote) {
            return;
        }
        if(entity.getRNG().nextFloat() < SuperCactusLife.prob) {
            entity.dropItem(Item.getItemFromBlock(Blocks.CACTUS), 1);
        }
    }
}
