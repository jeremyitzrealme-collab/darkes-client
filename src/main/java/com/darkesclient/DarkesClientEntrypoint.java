package com.darkesclient;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entrypoint for Darkes Client mod initialization.
 * Handles core system setup and loading.
 */
public class DarkesClientEntrypoint implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("darkes-client");
    public static final String MOD_ID = "darkes-client";
    public static final String MOD_NAME = "Darkes Client";
    public static final String MOD_VERSION = "1.0.0";

    @Override
    public void onInitialize() {
        LOGGER.info("[{}] Initializing Darkes Client v{}", MOD_NAME, MOD_VERSION);
        
        // Initialize core systems
        DarkesClient.initialize();
        
        LOGGER.info("[{}] Initialization complete!", MOD_NAME);
    }
}
