package archives.tater.penchant.registry;

import archives.tater.penchant.Penchant;
import archives.tater.penchant.advancement.OpenTableTrigger;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class PenchantAdvancements {
    public static final OpenTableTrigger OPEN_TABLE = Registry.register(
            BuiltInRegistries.TRIGGER_TYPES,
            Penchant.id("open_table"),
            new OpenTableTrigger()
    );

    public static void init() {

    }
}
