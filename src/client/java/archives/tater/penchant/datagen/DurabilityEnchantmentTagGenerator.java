package archives.tater.penchant.datagen;

import archives.tater.penchant.registry.PenchantEnchantmentTags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.concurrent.CompletableFuture;

public class DurabilityEnchantmentTagGenerator extends EnchantmentTagsProvider {
    public DurabilityEnchantmentTagGenerator(PackOutput output, CompletableFuture<Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(Provider provider) {
         tag(PenchantEnchantmentTags.DISABLED).add(Enchantments.MENDING);
    }
}
