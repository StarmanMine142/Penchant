package archives.tater.penchant.registry;

import archives.tater.penchant.Penchant;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

public class PenchantFlag {
    public static final ResourceKey<Registry<PenchantFlag>> REGISTRY_KEY = ResourceKey.createRegistryKey(Penchant.id("flag"));
    public static final Registry<PenchantFlag> REGISTRY = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();
    public static final TagKey<PenchantFlag> ENABLED = TagKey.create(REGISTRY_KEY, Penchant.id("enabled"));

    public static boolean isEnabled(Holder<PenchantFlag> flag) {
        return flag.is(ENABLED);
    }

    public static void init() {

    }

    private static Holder<PenchantFlag> register(Identifier id) {
        return Registry.registerForHolder(
                REGISTRY,
                ResourceKey.create(REGISTRY_KEY, id),
                new PenchantFlag(id)
        );
    }

    private static Holder<PenchantFlag> register(String path) {
        return register(Penchant.id(path));
    }

    public static final Holder<PenchantFlag> REWORK_ENCHANTING_TABLE = register("rework_enchanting_table");

    private final Identifier id;

    public PenchantFlag(Identifier id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
