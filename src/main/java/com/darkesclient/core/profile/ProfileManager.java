package com.darkesclient.core.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Manages user profiles for Darkes Client.
 * Stores module states, configurations, and UI settings per profile.
 */
public class ProfileManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("ProfileManager");
    private final Map<String, UserProfile> profiles = new HashMap<>();
    private String activeProfile = "default";

    public ProfileManager() {
        // Create default profile
        createProfile("default");
        LOGGER.info("ProfileManager initialized");
    }

    /**
     * Creates a new profile.
     * @param name Profile name
     */
    public void createProfile(String name) {
        profiles.put(name, new UserProfile(name));
        LOGGER.debug("Profile created: {}", name);
    }

    /**
     * Gets a profile by name.
     * @param name Profile name
     * @return The profile, or null if not found
     */
    public UserProfile getProfile(String name) {
        return profiles.get(name);
    }

    /**
     * Gets the active profile.
     * @return The active profile
     */
    public UserProfile getActiveProfile() {
        return profiles.get(activeProfile);
    }

    /**
     * Sets the active profile.
     * @param name Profile name
     */
    public void setActiveProfile(String name) {
        if (profiles.containsKey(name)) {
            activeProfile = name;
            LOGGER.debug("Active profile changed to: {}", name);
        }
    }

    /**
     * Gets all profiles.
     * @return List of all profiles
     */
    public List<UserProfile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    /**
     * Deletes a profile.
     * @param name Profile name
     */
    public void deleteProfile(String name) {
        if (!name.equals("default")) {
            profiles.remove(name);
            LOGGER.debug("Profile deleted: {}", name);
        }
    }
}
