package archives.tater.penchant.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

import static archives.tater.penchant.client.gui.PenchantGuiUtil.containsPoint;
import static net.minecraft.util.Mth.clamp;

public class ScrollbarComponent {
    private final Identifier texture;
    private final int width;
    private final int scrollerHeight;
    private final int trackHeight;
    private final int maxStep;
    private final int regionWidth;
    private final int regionHeight;
    private final Runnable onScrolled;

    private final int maxScrollerYOffset;

    private int x;
    private int y;
    private int regionX;
    private int regionY;


    private int position = 0;

    private boolean dragging = true;
    private double mouseYOffset = 0;

    public ScrollbarComponent(Identifier texture, int width, int height, int trackHeight, int stepCount, int regionWidth, int regionHeight, Runnable onScrolled) {
        this.texture = texture;
        this.width = width;
        this.scrollerHeight = height;
        this.trackHeight = trackHeight;
        this.maxStep = stepCount - 1;
        this.regionWidth = regionWidth;
        this.regionHeight = regionHeight;
        this.onScrolled = onScrolled;

        maxScrollerYOffset = trackHeight - scrollerHeight;
    }

    public void updatePos(int x, int y, int regionX, int regionY) {
        this.x = x;
        this.y = y;
        this.regionX = regionX;
        this.regionY = regionY;
    }

    private int getScrollerYOffset() {
        return clamp(maxScrollerYOffset * position / maxStep, 0, maxScrollerYOffset);
    }

    private void setPositionForOffset(int yOffset) {
        setPosition(maxStep * yOffset / maxScrollerYOffset);
    }

    private void setPositionForOffset(double yOffset) {
        setPositionForOffset((int) yOffset);
    }

    private int getScrollerY() {
        return y + getScrollerYOffset();
    }

    public void render(GuiGraphics guiGraphics) {
        guiGraphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                texture,
                width,
                scrollerHeight,
                0,
                0,
                x,
                getScrollerY(),
                width,
                scrollerHeight
        );
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double scrollAmount) {
        if (!containsPoint(x, y, width, trackHeight, mouseX, mouseY) && !containsPoint(regionX, regionY, regionWidth, regionHeight, mouseX, mouseY)) return false;
        if (scrollAmount >= 0 && position <= 0 || scrollAmount <= 0 && position >= maxStep) return false;
        addPosition((int) -scrollAmount);
        return true;
    }

    public boolean mouseClicked(MouseButtonEvent event) {
        if (!containsPoint(x, y, width, trackHeight, event.x(), event.y())) return false;

        if (containsPoint(x, getScrollerY(), width, scrollerHeight, event.x(), event.y())) {
            dragging = true;
            mouseYOffset = event.y() - getScrollerY();
        } else
            setPositionForOffset(event.y() - y);

        return true;
    }

    public boolean mouseDragged(MouseButtonEvent event) {
        if (!dragging) return false;
        setPositionForOffset(event.y() - y - mouseYOffset);
        return true;
    }

    public void mouseReleased() {
        dragging = false;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        position = clamp(position, 0, maxStep);
        if (position == this.position) return;
        this.position = position;
        onScrolled.run();
    }
    
    public void addPosition(int change) {
        setPosition(position + change);
    }
}
