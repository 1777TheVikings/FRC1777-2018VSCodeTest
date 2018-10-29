package frc.utils.configuration;


public class ColbyConfig extends Configuration {
    @Override
    public String getUsername() {
        return "Colby";
    }

    @Override
    public double getRotationMultiplier() {
        return 0.7;
    }

    @Override
    public int getClawOpenButton() {
        return 6;
    }

    @Override
    public double getArmMultiplier() {
        return 0.4;
    }
}