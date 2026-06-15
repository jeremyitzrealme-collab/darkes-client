package com.darkesclient;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client-side entrypoint for Darkes Client.
 * Initializes client-specific systems (rendering, input, UI).
 */
public class DarkesClientClientEntrypoint implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("darkes-client-client");

    @Override
    public void onInitializeClient() {
        LOGGER.info("[Darkes Client] Initializing client systems...");
        
        // Initialize client systems
        DarkesClient.initializeClient();
        
        LOGGER.info("[Darkes Client] Client initialization complete!");
    }
}
