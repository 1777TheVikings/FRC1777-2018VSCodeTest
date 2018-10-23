package frc.utils.configuration;

public class ConfigurationManager {
    public static final Configuration[] allConfigs = {new Configuration(), new ColbyConfig(), new AydanConfig()};
    private int selectedConfig = 0;

    public Configuration getSelectedConfiguration() {
        return allConfigs[selectedConfig];
    }

    public int getConfigurationID() {
        return selectedConfig;
    }

    public void setConfigurationID(int id) {
        selectedConfig = id;
    }
}