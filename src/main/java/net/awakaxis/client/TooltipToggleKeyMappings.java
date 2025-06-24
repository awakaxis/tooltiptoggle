package net.awakaxis.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class TooltipToggleKeyMappings {
    public static final String MAIN_CATEGORY = "key.categories.tooltiptoggle";

    // shy chose the default
    public static final KeyMapping TOGGLE_TOOLTIPS = createKeyMap("toggle", GLFW.GLFW_KEY_PAGE_DOWN, MAIN_CATEGORY);

    public static void register() {
        KeyBindingHelper.registerKeyBinding(TOGGLE_TOOLTIPS);
        TooltipToggleClient.LOGGER.info("Registered Keybinds");
    }

    public static void handleKeybinds(Minecraft client) {
        assert client.player != null;
        if (TOGGLE_TOOLTIPS.consumeClick()) {
            TooltipToggleClient.toggleTooltips();
            client.player.displayClientMessage(Component.literal(String.format("Toggled Tooltips %s!", TooltipToggleClient.doRenderTooltips() ? "on" : "off")).withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD), false);
        }
    }

    private static KeyMapping createKeyMap(String id, int code, String category) {
        return new KeyMapping(String.format("key.%s.%s", TooltipToggleClient.MOD_ID, id), code, category);
    }
}
