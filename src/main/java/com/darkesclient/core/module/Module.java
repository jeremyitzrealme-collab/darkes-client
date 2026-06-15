package com.darkesclient.core.module;

import com.darkesclient.core.config.Config;

/**
 * Base class for all Darkes Client modules.
 * Modules are modular features that can be toggled and configured.
 */
public abstract class Module {
    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled = false;
    protected Config config;

    /**
     * Creates a new module.
     * @param name Module display name
     * @param description Module description
     * @param category Module category
     */
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.config = new Config(name);
    }

    /**
     * Called when the module is enabled.
     */
    public void onEnable() {
        // Override in subclasses
    }

    /**
     * Called when the module is disabled.
     */
    public void onDisable() {
        // Override in subclasses
    }

    /**
     * Called every tick while the module is enabled.
     */
    public void onTick() {
        // Override in subclasses
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public Config getConfig() {
        return config;
    }
}
