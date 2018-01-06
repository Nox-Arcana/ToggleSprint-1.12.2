package redper.minecraft.autosprint.render;

import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import redper.minecraft.autosprint.*;
import java.awt.*;
import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraftforge.client.event.*;
import redper.minecraft.autosprint.handler.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class SprintRenderer extends Gui
{
    private static SprintRenderer instance;
    private Minecraft mc;
    private Configuration config;
    private ConfigCategory configCategory;
    
    public SprintRenderer() {
        this.mc = Minecraft.getMinecraft();
        this.config = AutoSprintMod.getInstance().getConfig();
        this.configCategory = this.config.getCategory("client");
    }
    
    public static SprintRenderer getInstance() {
        return SprintRenderer.instance;
    }
    
    @SideOnly(Side.CLIENT)
    public void render() {
        final Property colorProperty = this.configCategory.get("hexTextColor");
        final String hexColor = colorProperty.getString();
        int color;
        if (hexColor.length() != 6) {
            color = Color.decode("FFFFFF").getRGB();
        }
        else {
            try {
                color = Color.decode("0x" + hexColor).getRGB();
            }
            catch (NumberFormatException e) {
                color = Color.decode("FFFFFF").getRGB();
            }
        }
        final String s = "[Sprinting (Toggled)]";
        final Property backgroundProperty = this.configCategory.get("useTextBgr");
        final Property backgroundColorProperty = this.configCategory.get("backgroundColor");
        final int backgroundColorWA = Color.decode("0x" + backgroundColorProperty.getString()).getRGB();
        final int backgroundColor = (backgroundColorWA & 0xFFFFFF) | 0x32000000;
        RenderUtils.renderString(s, color, backgroundColor, 3, 479, backgroundProperty.getBoolean());
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRender(final RenderGameOverlayEvent event) {
        if (KeyHandler.getInstance().isToggled() && event.getType() == RenderGameOverlayEvent.ElementType.TEXT && !this.mc.gameSettings.showDebugInfo) {
            this.render();
        }
    }
    
    static {
        SprintRenderer.instance = new SprintRenderer();
    }
}
