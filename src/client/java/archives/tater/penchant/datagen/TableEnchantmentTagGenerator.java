package archives.tater.penchant.datagen;

import archives.tater.penchant.Penchant;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.concurrent.CompletableFuture;

public class TableEnchantmentTagGenerator extends FabricTagProvider<Enchantment> {
    public TableEnchantmentTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ENCHANTMENT, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        var normalTreasure = TagKey.create(Registries.ENCHANTMENT, Penchant.id("normal_treasure"));

        //noinspection unchecked
        builder(normalTreasure).add(
                Enchantments.FROST_WALKER,
                Enchantments.FIRE_ASPECT,
                Enchantments.FLAME,
                Enchantments.SILK_TOUCH,
                Enchantments.LUNGE,
                Enchantments.CHANNELING,
                Enchantments.RIPTIDE,
                Enchantments.FORTUNE,
                Enchantments.THORNS,
                Enchantments.INFINITY,
                Enchantments.RESPIRATION
        );
        builder(EnchantmentTags.IN_ENCHANTING_TABLE)
                .tagex_excludeTag(normalTreasure);
        builder(EnchantmentTags.TRADEABLE)
                .addTag(normalTreasure);
        builder(EnchantmentTags.ON_RANDOM_LOOT)
                .addTag(normalTreasure);
        builder(EnchantmentTags.ON_TRADED_EQUIPMENT)
                .addTag(normalTreasure);
        builder(EnchantmentTags.ON_MOB_SPAWN_EQUIPMENT)
                .addTag(normalTreasure);
    }
}
