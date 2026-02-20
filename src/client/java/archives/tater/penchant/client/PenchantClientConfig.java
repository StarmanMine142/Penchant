package archives.tater.penchant.client;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.IntegerRange;

public class PenchantClientConfig extends WrappedConfig {

    @Comment("Whether enchantment progress should always be shown regardless of keypress")
    public boolean alwaysShowTooltipProgress = false;

    @Comment("If the hint for showing enchantment progress with keybind should be shown")
    @Comment("Does nothing if Always Show Progress is enabled")
    public boolean showTooltipKeyHint = true;

    @Comment("Width of enchantment progress bar")
    @IntegerRange(min = 4, max = 128)
    public int barWidth = 32;
}
