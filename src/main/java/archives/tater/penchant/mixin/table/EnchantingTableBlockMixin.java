package archives.tater.penchant.mixin.table;

import archives.tater.penchant.PenchantmentMenu;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantingTableBlock;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {
    @ModifyReturnValue(
            method = "method_17467",
            at = @At("RETURN")
    )
    private static AbstractContainerMenu replaceMenu(AbstractContainerMenu original, @Local(argsOnly = true) int syncId, @Local(argsOnly = true) Inventory inventory, @Local(argsOnly = true) Level level, @Local(argsOnly = true) BlockPos pos) {
        // TODO feature flag
        return new PenchantmentMenu(syncId, inventory, ContainerLevelAccess.create(level, pos));
    }
}
