package redper.minecraft.autosprint.gui;

import redper.minecraft.autosprint.gui.config.*;
import java.util.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.relauncher.*;

public class GuiFactory implements IModGuiFactory
{
    public void initialize(final Minecraft minecraftInstance) {
    }
    
    public boolean hasConfigGui() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public GuiScreen createConfigGui(final GuiScreen parentScreen) {
        return (GuiScreen)new ConfigGui(parentScreen);
    }
    
    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}
