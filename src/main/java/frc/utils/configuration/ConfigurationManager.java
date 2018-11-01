package frc.utils.configuration;

/**
 * This class manages the selection of {@link Configuration}s. This allows classes other than Robot to
 * access the configs before robotInit() has occurred.
 * 
 * New configs can be added by creating a new instance of it to allConfigs. The Configuration base
 * class should be used as the first item to act as a failsafe.
 */
public class ConfigurationManager {
    public static final Configuration[] allConfigs = {new Configuration(), new ColbyConfig(), new AydanConfig()};
    private int selectedConfig = 0;

    public Configuration getSelectedConfiguration() {
        return allConfigs[selectedConfig];
    }

    public int getConfigurationID() {
        return selectedConfig;
    }

    /**
     * Selects a configuration based on its index in the allConfigs list.
     * @param id
     */
    public void setConfigurationID(int id) {
        selectedConfig = id;
    }
}