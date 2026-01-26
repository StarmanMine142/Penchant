package archives.tater.penchant.datagen;

import archives.tater.penchant.loot.LootModification;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class LootModificationGenerator extends FabricDynamicRegistryProvider {
    public LootModificationGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static void addInject(Entries entries, ResourceKey<LootTable> target, ResourceKey<LootTable> inject) {
        entries.add(ResourceKey.create(LootModification.KEY, inject.identifier().withPath(path -> path.substring(path.indexOf('/') + 1))), new LootModification(
                List.of(target),
                List.of(LootPool.lootPool()
                        .add(NestedLootTable.lootTableReference(inject))
                        .build()),
                List.of(),
                Optional.empty()
        ));
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        addInject(entries, BuiltInLootTables.IGLOO_CHEST, LootLootTableGenerator.IGLOO);
        addInject(entries, BuiltInLootTables.NETHER_BRIDGE, LootLootTableGenerator.FORTRESS);
        addInject(entries, BuiltInLootTables.ABANDONED_MINESHAFT, LootLootTableGenerator.MINESHAFT);
        addInject(entries, BuiltInLootTables.SIMPLE_DUNGEON, LootLootTableGenerator.DUNGEON);
        addInject(entries, BuiltInLootTables.UNDERWATER_RUIN_SMALL, LootLootTableGenerator.RUINS);
        addInject(entries, BuiltInLootTables.BURIED_TREASURE, LootLootTableGenerator.BURIED_TREASURE);
    }

    @Override
    public String getName() {
        return "Loot Modification";
    }
}
