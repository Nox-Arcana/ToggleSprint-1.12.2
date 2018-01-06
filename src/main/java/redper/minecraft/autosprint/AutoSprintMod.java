package redper.minecraft.autosprint;

import net.minecraftforge.fml.common.*;
import java.io.*;
import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.common.*;
import redper.minecraft.autosprint.handler.*;
import redper.minecraft.autosprint.render.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = "redperautosprintmod", name = "AutoSprint Mod", version = "1.2.0", acceptedMinecraftVersions = "[1.12.2]", clientSideOnly = true, canBeDeactivated = false, guiFactory = "redper.minecraft.autosprint.gui.GuiFactory")
public class AutoSprintMod
{
    private Configuration config;
    @Mod.Instance("redperautosprintmod")
    private static AutoSprintMod instance;
    
    public Configuration getConfig() {
        return this.config;
    }
    
    public static AutoSprintMod getInstance() {
        return AutoSprintMod.instance;
    }
    
    public AutoSprintMod() {
        final File confFile = new File(Loader.instance().getConfigDir(), "autosprintmod.cfg");
        this.config = new Configuration(confFile);
        this.syncConfig(true);
    }
    
    private void syncConfig(final boolean load) {
        if (!this.config.isChild && load) {
            this.config.load();
        }
        Property prop = this.config.get("client", "isSprintToggled", false);
        prop.setComment("Determines if sprint is toggled.");
        prop.setLanguageKey("config.client.sprinttoggled");
        prop.setRequiresMcRestart(true);
        prop.setRequiresWorldRestart(false);
        prop.setShowInGui(false);
        prop = this.config.get("client", "doRememberSprint", true);
        prop.setComment("Do Remember Sprint State after Minecraft exit.");
        prop.setLanguageKey("config.client.remembersprint");
        prop.setRequiresMcRestart(false);
        prop.setRequiresWorldRestart(false);
        prop.setShowInGui(true);
        prop = this.config.get("client", "hexTextColor", "FFFFFF");
        prop.setComment("The Hex Color Of All The text displayed");
        prop.setLanguageKey("config.client.textcolor");
        prop.setRequiresMcRestart(false);
        prop.setRequiresWorldRestart(false);
        prop.setShowInGui(true);
        prop = this.config.get("client", "useTextBgr", true);
        prop.setComment("Determines if some background is shown under the text");
        prop.setLanguageKey("config.client.textbackground");
        prop.setRequiresMcRestart(false);
        prop.setRequiresWorldRestart(false);
        prop.setShowInGui(true);
        prop = this.config.get("client", "backgroundColor", "172331");
        prop.setComment("Color of the background under the text");
        prop.setLanguageKey("config.client.backroundcolor");
        prop.setRequiresMcRestart(false);
        prop.setRequiresWorldRestart(false);
        prop.setShowInGui(true);
        if (this.config.hasChanged()) {
            this.config.save();
        }
    }
    
    @SubscribeEvent
    public void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals("redperautosprintmod")) {
            this.syncConfig(false);
        }
    }
    
    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void preInit(final FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)KeyHandler.getInstance());
        MinecraftForge.EVENT_BUS.register((Object)SprintRenderer.getInstance());
    }
    
    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void init(final FMLInitializationEvent event) {
    }
    
    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void postInit(final FMLPostInitializationEvent event) {
    }
}
