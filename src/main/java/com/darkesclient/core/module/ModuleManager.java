package com.darkesclient.core.module;

import com.darkesclient.core.event.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Manages all modules in Darkes Client.
 * Handles module registration, enabling/disabling, and ticking.
 */
public class ModuleManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("ModuleManager");
    private final EventBus eventBus;
    private final Map<String, Module> modules = new HashMap<>();
    private final Map<Category, List<Module>> modulesByCategory = new HashMap<>();

    public ModuleManager(EventBus eventBus) {
        this.eventBus = eventBus;
        for (Category category : Category.values()) {
            modulesByCategory.put(category, new ArrayList<>());
        }
        LOGGER.info("ModuleManager initialized");
    }

    /**
     * Registers a module.
     * @param module The module to register
     */
    public void registerModule(Module module) {
        modules.put(module.getName(), module);
        modulesByCategory.get(module.getCategory()).add(module);
        LOGGER.debug("Module registered: {}", module.getName());
    }

    /**
     * Unregisters a module.
     * @param moduleName The name of the module to unregister
     */
    public void unregisterModule(String moduleName) {
        Module module = modules.remove(moduleName);
        if (module != null) {
            modulesByCategory.get(module.getCategory()).remove(module);
            if (module.isEnabled()) {
                module.setEnabled(false);
            }
            LOGGER.debug("Module unregistered: {}", moduleName);
        }
    }

    /**
     * Gets a module by name.
     * @param name Module name
     * @return The module, or null if not found
     */
    public Module getModule(String name) {
        return modules.get(name);
    }

    /**
     * Gets all modules in a category.
     * @param category The category
     * @return List of modules in the category
     */
    public List<Module> getModulesByCategory(Category category) {
        return new ArrayList<>(modulesByCategory.getOrDefault(category, new ArrayList<>()));
    }

    /**
     * Gets all modules.
     * @return List of all modules
     */
    public List<Module> getAllModules() {
        return new ArrayList<>(modules.values());
    }

    /**
     * Called every tick to update all enabled modules.
     */
    public void onTick() {
        modules.values().stream()
                .filter(Module::isEnabled)
                .forEach(Module::onTick);
    }
}
