package archives.tater.penchant.datagen;

import archives.tater.penchant.Penchant;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.SetEnchantmentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.minecraft.world.level.storage.loot.LootPool.lootPool;
import static net.minecraft.world.level.storage.loot.LootTable.lootTable;
import static net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem;
import static net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly;

public class LootLootTableGenerator extends SimpleFabricLootTableProvider {

    private final HolderLookup.Provider registries;

    private static ResourceKey<LootTable> inject(ResourceKey<LootTable> target) {
        return ResourceKey.create(Registries.LOOT_TABLE, Penchant.id("inject/" + target.identifier().getNamespace() + "/" + target.identifier().getPath()));
    }

    public static final ResourceKey<LootTable> IGLOO = inject(BuiltInLootTables.IGLOO_CHEST);
    public static final ResourceKey<LootTable> FORTRESS = inject(BuiltInLootTables.NETHER_BRIDGE);
    public static final ResourceKey<LootTable> MINESHAFT = inject(BuiltInLootTables.ABANDONED_MINESHAFT);
    public static final ResourceKey<LootTable> DUNGEON = inject(BuiltInLootTables.SIMPLE_DUNGEON);
    public static final ResourceKey<LootTable> RUINS = inject(BuiltInLootTables.UNDERWATER_RUIN_SMALL);
    public static final ResourceKey<LootTable> BURIED_TREASURE = inject(BuiltInLootTables.BURIED_TREASURE);

    public LootLootTableGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
        registries = registryLookup.join();
    }

    @SafeVarargs
    private LootTable.Builder createBooks(ResourceKey<Enchantment>... enchantments) {
        var enchantmentRegistry = registries.lookupOrThrow(Registries.ENCHANTMENT);

        var pool = lootPool();
        for (var enchantment : enchantments) {
            pool.add(lootTableItem(Items.BOOK)
                    .apply(new SetEnchantmentsFunction.Builder()
                            .withEnchantment(enchantmentRegistry.getOrThrow(enchantment), exactly(1))
                    )
            );
        }
        return lootTable()
                .withPool(pool);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(IGLOO, createBooks(Enchantments.FROST_WALKER));
        output.accept(FORTRESS, createBooks(Enchantments.FIRE_ASPECT, Enchantments.FLAME));
        output.accept(MINESHAFT, createBooks(Enchantments.SILK_TOUCH, Enchantments.FORTUNE));
        output.accept(DUNGEON, createBooks(Enchantments.SILK_TOUCH, Enchantments.FORTUNE, Enchantments.THORNS, Enchantments.INFINITY));
        output.accept(RUINS, createBooks(Enchantments.CHANNELING, Enchantments.RIPTIDE));
        output.accept(BURIED_TREASURE, createBooks(Enchantments.CHANNELING, Enchantments.RIPTIDE));
    }
}
