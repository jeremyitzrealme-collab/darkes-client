package com.darkesclient.core.profile;

import java.util.*;

/**
 * Represents a user profile storing module states and settings.
 */
public class UserProfile {
    private final String name;
    private final Map<String, Boolean> moduleStates = new HashMap<>();
    private final Map<String, String> settings = new HashMap<>();
    private long createdAt;
    private long lastModified;

    public UserProfile(String name) {
        this.name = name;
        this.createdAt = System.currentTimeMillis();
        this.lastModified = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setModuleState(String moduleName, boolean enabled) {
        moduleStates.put(moduleName, enabled);
        lastModified = System.currentTimeMillis();
    }

    public boolean getModuleState(String moduleName) {
        return moduleStates.getOrDefault(moduleName, false);
    }

    public void setSetting(String key, String value) {
        settings.put(key, value);
        lastModified = System.currentTimeMillis();
    }

    public String getSetting(String key) {
        return settings.get(key);
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getLastModified() {
        return lastModified;
    }
}
