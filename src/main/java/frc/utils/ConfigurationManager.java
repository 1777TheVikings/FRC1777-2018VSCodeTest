package frc.utils;

import java.io.File;
import java.util.ArrayList;

public class ConfigurationManager {
    private static File SAVE_PATH = new File("/home/lvuser/configs");

    private Configuration currentConfiguration;

    public ConfigurationManager() {
        ArrayList<String> allConfigs = getAllConfigs();
        if (!allConfigs.contains("Default")) {
            Configuration defaultConfig = new Configuration();
            defaultConfig.setUsername("Default");  // other defaults are already set
            defaultConfig.saveToXML(new File(SAVE_PATH, "Default.xml"));
            allConfigs.add("Default");
        }
        currentConfiguration = new Configuration(new File(SAVE_PATH, "Default.xml"));
    }

    public Configuration getCurrentConfiguration() {
        return currentConfiguration;
    }

    public void selectConfiguration(Configuration configuration) {
        this.currentConfiguration = configuration;
    }

    public void selectConfiguration(String configurationName) {
        this.currentConfiguration = new Configuration(new File(SAVE_PATH, configurationName + ".xml"));
    }

    /**
     * Gets an array containing the names of all config files. If the folder does not already
     * exist, the folder is created.
     * 
     * @return Names of all config files
     */
    public ArrayList<String> getAllConfigs() {
        if (!SAVE_PATH.exists()) {
            SAVE_PATH.mkdirs();
        }

        ArrayList<String> output = new ArrayList<String>();

        File[] allFiles = SAVE_PATH.listFiles();
        for (int i = 0; i < allFiles.length; i++) {
            if (allFiles[i].isFile() && allFiles[i].getName().endsWith(".xml")) {
                String name = allFiles[i].getName();
                output.add(name.substring(0, name.length() - 4));
            }
        }

        return output;
    }
}