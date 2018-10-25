package frc.utils.configuration;


public class AydanConfig extends Configuration {
    @Override
    public String getUsername() {
        return "Aydan";
    }

    @Override
    public double getRotationMultiplier() {
        return 0.8;
    }

    @Override
    public ButtonMode getTransmissionButtonMode() {
        return ButtonMode.TOGGLE;
    }

    @Override
    public ButtonMode getArmTransmissionButtonMode() {
        return ButtonMode.TOGGLE;
    }

    @Override
    public ButtonMode getClawOpenButtonMode() {
        return ButtonMode.TOGGLE;
    }
}