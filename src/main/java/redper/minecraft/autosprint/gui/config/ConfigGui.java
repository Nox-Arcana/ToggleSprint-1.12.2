package redper.minecraft.autosprint.gui.config;

import redper.minecraft.autosprint.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.*;
import net.minecraftforge.fml.client.config.*;
import net.minecraftforge.common.config.*;

public class ConfigGui extends GuiConfig
{
    public ConfigGui(final GuiScreen parentScreen) {
        super(parentScreen, (List)getConfigElements(), "redperautosprintmod", false, false, I18n.format("config.configgui.title", new Object[0]));
    }
    
    private static List<IConfigElement> getConfigElements() {
        final List<IConfigElement> elements = new ArrayList<IConfigElement>();
        elements.addAll(new ConfigElement(AutoSprintMod.getInstance().getConfig().getCategory("client")).getChildElements());
        return elements;
    }
}
