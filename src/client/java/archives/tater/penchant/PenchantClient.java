package archives.tater.penchant;

import net.fabricmc.api.ClientModInitializer;

public class PenchantClient implements ClientModInitializer {
//    private static KeyMapping keybind(ResourceLocation id, int key, KeyMapping.Category category) {
//        return KeyBindingHelper.registerKeyBinding(new KeyMapping(makeDescriptionId("key", id), Type.KEYSYM, key, category));
//    }
//
//    private static final KeyMapping.Category PENCHANT_CATEGORY = KeyMapping.Category.register(Penchant.id(Penchant.MOD_ID));
//    public static final KeyMapping SHOW_PROGRESS_KEYBIND = keybind(
//            Penchant.id("show_progress"),
//            InputConstants.KEY_LSHIFT,
//            PENCHANT_CATEGORY
//    );

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}