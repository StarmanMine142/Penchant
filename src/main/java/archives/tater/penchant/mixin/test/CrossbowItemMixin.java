package archives.tater.penchant.mixin.test;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import org.jspecify.annotations.NullMarked;

@NullMarked
@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin extends ProjectileWeaponItem {
    public CrossbowItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, Holder<Enchantment> enchantment, EnchantingContext context) {
        return super.canBeEnchantedWith(stack, enchantment, context) && (stack.getEnchantments().keySet().stream().noneMatch(it -> it.is(Enchantments.UNBREAKING)) || !enchantment.is(Enchantments.PIERCING));
    }
}
