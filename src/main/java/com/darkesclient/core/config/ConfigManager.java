package com.darkesclient.core.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Manages configuration files for modules and features.
 * Handles loading, saving, and access to configurations.
 */
public class ConfigManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("ConfigManager");
    private static final String CONFIG_DIR = "config/darkes-client";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    private final Map<String, Config> configs = new HashMap<>();
    private final Path configPath;

    public ConfigManager() {
        this.configPath = Paths.get(CONFIG_DIR);
        ensureConfigDirectory();
        LOGGER.info("ConfigManager initialized at: {}", CONFIG_DIR);
    }

    /**
     * Ensures the config directory exists.
     */
    private void ensureConfigDirectory() {
        try {
            Files.createDirectories(configPath);
        } catch (IOException e) {
            LOGGER.error("Failed to create config directory", e);
        }
    }

    /**
     * Gets or creates a configuration.
     * @param name Config name
     * @return The configuration
     */
    public Config getOrCreateConfig(String name) {
        return configs.computeIfAbsent(name, k -> new Config(name));
    }

    /**
     * Saves a configuration to disk.
     * @param config The configuration to save
     */
    public void saveConfig(Config config) {
        try {
            Path filePath = configPath.resolve(config.getName() + ".json");
            String json = GSON.toJson(config.getData());
            Files.write(filePath, json.getBytes());
            LOGGER.debug("Config saved: {}", config.getName());
        } catch (IOException e) {
            LOGGER.error("Failed to save config: {}", config.getName(), e);
        }
    }

    /**
     * Loads a configuration from disk.
     * @param name Config name
     * @return The configuration
     */
    public Config loadConfig(String name) {
        try {
            Path filePath = configPath.resolve(name + ".json");
            if (Files.exists(filePath)) {
                String json = new String(Files.readAllBytes(filePath));
                JsonObject data = GSON.fromJson(json, JsonObject.class);
                Config config = new Config(name);
                data.entrySet().forEach(entry -> 
                    config.getData().add(entry.getKey(), entry.getValue())
                );
                LOGGER.debug("Config loaded: {}", name);
                return config;
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load config: {}", name, e);
        }
        return new Config(name);
    }

    /**
     * Saves all configurations.
     */
    public void saveAll() {
        configs.values().forEach(this::saveConfig);
    }
}
