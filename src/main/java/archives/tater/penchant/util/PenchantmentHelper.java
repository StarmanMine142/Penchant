package archives.tater.penchant.util;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class PenchantmentHelper {
    private PenchantmentHelper() {}

    public static Component getName(Holder<Enchantment> enchantment) {
        return ComponentUtils.mergeStyles(
                enchantment.value().description().copy(),
                Style.EMPTY.withColor(enchantment.is(EnchantmentTags.CURSE)
                        ? ChatFormatting.RED
                        : ChatFormatting.GRAY
                )
        );
    }

    public static int getBookRequirement(Holder<Enchantment> enchantment) {
        return 2 * enchantment.value().getMinCost(1) - 5;
    }

    public static int getXpLevelCost(Holder<Enchantment> enchantment) {
        return enchantment.value().getAnvilCost();
    }

    public static boolean canEnchantItem(ItemStack stack, Holder<Enchantment> enchantment) {
        return (stack.is(Items.BOOK) || stack.is(Items.ENCHANTED_BOOK) || stack.canBeEnchantedWith(enchantment, EnchantingContext.ACCEPTABLE));
    }

    public static ItemEnchantments getEnchantments(ItemStack stack) {
        return EnchantmentHelper.getEnchantmentsForCrafting(stack);
    }

    public static boolean hasEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return getEnchantments(stack).getLevel(enchantment) > 0;
    }

    public static boolean canEnchant(ItemStack stack, Holder<Enchantment> enchantment) {
        return canEnchantItem(stack, enchantment) && !hasEnchantment(stack, enchantment) && EnchantmentHelper.isEnchantmentCompatible(getEnchantments(stack).keySet(), enchantment);
    }
}
