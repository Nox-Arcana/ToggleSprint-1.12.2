package redper.minecraft.autosprint.handler;

import redper.minecraft.autosprint.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.*;
import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;

public class KeyHandler
{
    private static KeyHandler instance;
    private KeyBinding toggleSprintKey;
    private boolean toggled;
    private Minecraft mc;
    private int sprintKey;
    private Configuration config;
    private Property sprintProperty;
    
    private KeyHandler() {
        this.toggled = false;
        this.config = AutoSprintMod.getInstance().getConfig();
        final ConfigCategory cc = this.config.getCategory("client");
        if (cc.get("doRememberSprint").getBoolean()) {
            this.sprintProperty = cc.get("isSprintToggled");
            this.toggled = this.sprintProperty.getBoolean();
        }
        ClientRegistry.registerKeyBinding(this.toggleSprintKey = new KeyBinding("key.autosprint.toggle", 19, "key.categories.movement"));
        this.mc = Minecraft.getMinecraft();
        this.sprintKey = this.mc.gameSettings.keyBindSprint.getKeyCode();
    }
    
    public static KeyHandler getInstance() {
        return KeyHandler.instance;
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        this.sprintKey = this.mc.gameSettings.keyBindSprint.getKeyCode();
        if (this.toggleSprintKey.isPressed()) {
            this.toggled = !this.toggled;
            this.sprintProperty.set(this.toggled);
            this.config.save();
            if (!this.toggled) {
                KeyBinding.setKeyBindState(this.sprintKey, false);
            }
        }
        if (this.toggled) {
            KeyBinding.setKeyBindState(this.sprintKey, true);
        }
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    static {
        KeyHandler.instance = new KeyHandler();
    }
}
