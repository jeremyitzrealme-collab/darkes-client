package com.darkesclient;

import com.darkesclient.core.config.ConfigManager;
import com.darkesclient.core.event.EventBus;
import com.darkesclient.core.module.ModuleManager;
import com.darkesclient.core.profile.ProfileManager;
import com.darkesclient.ui.dashboard.DashboardManager;
import com.darkesclient.ui.hud.HUDManager;
import com.darkesclient.ui.theme.ThemeManager;
import com.darkesclient.ui.widget.WidgetManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Central manager for Darkes Client.
 * Manages all core systems and provides global access.
 */
public class DarkesClient {
    private static final Logger LOGGER = LoggerFactory.getLogger("darkes-client");
    
    private static EventBus eventBus;
    private static ModuleManager moduleManager;
    private static ConfigManager configManager;
    private static ProfileManager profileManager;
    private static ThemeManager themeManager;
    private static HUDManager hudManager;
    private static DashboardManager dashboardManager;
    private static WidgetManager widgetManager;

    /**
     * Initializes core systems.
     */
    public static void initialize() {
        LOGGER.info("Initializing core systems...");
        
        // Initialize in dependency order
        eventBus = new EventBus();
        configManager = new ConfigManager();
        profileManager = new ProfileManager();
        moduleManager = new ModuleManager(eventBus);
        
        LOGGER.info("Core systems initialized successfully");
    }

    /**
     * Initializes client-specific systems.
     */
    public static void initializeClient() {
        LOGGER.info("Initializing client systems...");
        
        themeManager = new ThemeManager();
        widgetManager = new WidgetManager();
        hudManager = new HUDManager(widgetManager, themeManager);
        dashboardManager = new DashboardManager(moduleManager, configManager);
        
        LOGGER.info("Client systems initialized successfully");
    }

    // Getters
    public static EventBus getEventBus() {
        return eventBus;
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public static ProfileManager getProfileManager() {
        return profileManager;
    }

    public static ThemeManager getThemeManager() {
        return themeManager;
    }

    public static HUDManager getHUDManager() {
        return hudManager;
    }

    public static DashboardManager getDashboardManager() {
        return dashboardManager;
    }

    public static WidgetManager getWidgetManager() {
        return widgetManager;
    }
}
