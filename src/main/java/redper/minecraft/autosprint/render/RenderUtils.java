package redper.minecraft.autosprint.render;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.*;
import java.awt.*;
import net.minecraft.client.gui.*;

@SideOnly(Side.CLIENT)
public class RenderUtils
{
    private static Minecraft mc;
    
    public static void renderString(final String text, final int color, final int backgroundColor, final int x, final int y, final boolean background) {
        final int h = RenderUtils.mc.fontRenderer.FONT_HEIGHT;
        final int sw = RenderUtils.mc.fontRenderer.getStringWidth(text);
        if (background) {
            Gui.drawRect(x - 1, y - 2, sw + 4, h + 479, backgroundColor);
        }
        RenderUtils.mc.fontRenderer.drawString(text, x, y, color);
    }
    
    public static void renderString(final String text, final Color color, final Color backgroundColor, final int x, final int y, final boolean background) {
        final int h = RenderUtils.mc.fontRenderer.FONT_HEIGHT;
        final int sw = RenderUtils.mc.fontRenderer.getStringWidth(text);
        if (background) {
            Gui.drawRect(x - 1, y - 1, sw + 4, h + 479, backgroundColor.getRGB());
        }
        RenderUtils.mc.fontRenderer.drawString(text, x, y, color.getRGB());
    }
    
    public static ScaledResolution getScaledResolution() {
        return new ScaledResolution(RenderUtils.mc);
    }
    
    static {
        RenderUtils.mc = Minecraft.getMinecraft();
    }
}
