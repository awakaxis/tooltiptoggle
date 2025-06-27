package net.awakaxis.client;

import net.awakaxis.client.mixin.AbstractContainerScreenAccessor;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;

import org.lwjgl.glfw.GLFW;

public class TooltipToggleKeyMappings {
    public static final String MAIN_CATEGORY = "key.categories.tooltiptoggle";

    // shy chose the default (not my fault)
    public static final KeyMapping TOGGLE_TOOLTIPS = createKeyMap("toggle_tooltips", GLFW.GLFW_KEY_PAGE_DOWN,
            MAIN_CATEGORY);
    public static final KeyMapping TOGGLE_DIFFS = createKeyMap("toggle_diffs", GLFW.GLFW_KEY_PAGE_UP, MAIN_CATEGORY);
    public static final KeyMapping MARK_DIFF = createKeyMap("mark_diff", GLFW.GLFW_KEY_O,
            MAIN_CATEGORY);

    public static void register() {
        KeyBindingHelper.registerKeyBinding(TOGGLE_TOOLTIPS);
        KeyBindingHelper.registerKeyBinding(TOGGLE_DIFFS);
        KeyBindingHelper.registerKeyBinding(MARK_DIFF);
        TooltipToggleClient.LOGGER.info("Registered Keybinds");
    }

    public static void handleKeybinds(Minecraft client) {
        assert client.player != null;
        if (TOGGLE_TOOLTIPS.consumeClick()) {
            TooltipToggleClient.toggleTooltips();
            client.player.displayClientMessage(Component
                    .literal(String.format("Toggled Tooltips %s!",
                            TooltipToggleClient.doRenderTooltips() ? "on" : "off"))
                    .withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD), false);
        }

        if (TOGGLE_DIFFS.consumeClick()) {
            TooltipToggleClient.toggleDiffs();
            client.player.displayClientMessage(Component.literal(String.format("Toggled Diffs %s!",
                    TooltipToggleClient.doDisplayDiffs() ? "on" : "off"))
                    .withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD), false);
        }

        if (MARK_DIFF.consumeClick()) {
            if (client.screen instanceof AbstractContainerScreen abstractContainerScreen) {
                AbstractContainerScreenAccessor accessor = (AbstractContainerScreenAccessor) abstractContainerScreen;
            }
        }
    }

    private static KeyMapping createKeyMap(String id, int code, String category) {
        return new KeyMapping(String.format("key.%s.%s", TooltipToggleClient.MOD_ID, id), code, category);
    }
}
