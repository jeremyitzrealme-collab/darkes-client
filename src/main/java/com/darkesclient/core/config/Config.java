package com.darkesclient.core.config;

import com.google.gson.JsonObject;

/**
 * Represents a configuration section for a module or feature.
 * Stores key-value pairs that can be serialized/deserialized.
 */
public class Config {
    private final String name;
    private final JsonObject data;

    /**
     * Creates a new config.
     * @param name Config name
     */
    public Config(String name) {
        this.name = name;
        this.data = new JsonObject();
    }

    /**
     * Sets a boolean value.
     */
    public void setBoolean(String key, boolean value) {
        data.addProperty(key, value);
    }

    /**
     * Gets a boolean value.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        if (data.has(key)) {
            return data.get(key).getAsBoolean();
        }
        return defaultValue;
    }

    /**
     * Sets an integer value.
     */
    public void setInt(String key, int value) {
        data.addProperty(key, value);
    }

    /**
     * Gets an integer value.
     */
    public int getInt(String key, int defaultValue) {
        if (data.has(key)) {
            return data.get(key).getAsInt();
        }
        return defaultValue;
    }

    /**
     * Sets a float value.
     */
    public void setFloat(String key, float value) {
        data.addProperty(key, value);
    }

    /**
     * Gets a float value.
     */
    public float getFloat(String key, float defaultValue) {
        if (data.has(key)) {
            return data.get(key).getAsFloat();
        }
        return defaultValue;
    }

    /**
     * Sets a string value.
     */
    public void setString(String key, String value) {
        data.addProperty(key, value);
    }

    /**
     * Gets a string value.
     */
    public String getString(String key, String defaultValue) {
        if (data.has(key)) {
            return data.get(key).getAsString();
        }
        return defaultValue;
    }

    public String getName() {
        return name;
    }

    public JsonObject getData() {
        return data;
    }
}
