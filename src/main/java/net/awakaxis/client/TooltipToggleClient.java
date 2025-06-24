package net.awakaxis.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TooltipToggleClient implements ClientModInitializer {
    public static final String MOD_ID = "tooltiptoggle";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static boolean renderTooltips = true;

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static boolean doRenderTooltips() {
        return renderTooltips;
    }

    public static void toggleTooltips() {
        renderTooltips = !renderTooltips;
    }

    @Override
    public void onInitializeClient() {
        TooltipToggleKeyMappings.register();
        LOGGER.info("Hello Tooltip haters!");

        ClientTickEvents.START_CLIENT_TICK.register(TooltipToggleKeyMappings::handleKeybinds);
    }
}
