package frc.utils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

import org.w3c.dom.*;

public class Configuration {
    public enum ButtonMode {
        /**
         * The button must be held for the action to occur.
         */
        HOLD,
        /**
         * The button's action can be toggled on and off.
         */
        TOGGLE
    }

    /**
     * The name of the person using this configuration. This is also used to name the XML file.
     */
    private String username = "Default";

    /**
     * Determines which Xbox controller joystick is used to control the drive train.
     */
    private Hand movementHand = Hand.kLeft;

    /**
     * The multiplier for the robot's throttle (Y axis on joystick).
     */
    private float throttleMultiplier = 1.0f;

    /**
     * The multiplier for the robot's rotation (X axis on joystick).
     */
    private float rotationMultiplier = 1.0f;

    /**
     * The button to control the transmission with.
     */
    private int transmissionButton = 1;

    /**
     * Determines how the transmission button operates (hold = normally low gear).
     */
    private ButtonMode transmissionButtonMode = ButtonMode.HOLD;

    /**
     * The button to control the claw with.
     */
    private int clawButton = 3;

    /**
     * Determines how the claw button operates (hold = normally closed).
     */
    private ButtonMode clawButtonMode = ButtonMode.HOLD;

    /**
     * The axis to use for sucking in cubes.
     */
    private int intakeInputAxis = 3;

    /**
     * The axis to use for spitting out cubes.
     */
    private int intakeOutputAxis = 2;

    /**
     * Creates an empty configuration.
     */
    public Configuration() {
    }

    /**
     * Creates a configuration from an XML file. The data is read from ~/configs/$(username).xml
     */
    public Configuration(File file) {
        try {
            this.username = file.getName().substring(0, file.getName().length() - 4);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            
            NodeList movementHandList = doc.getElementsByTagName("MovementHand");
            if (movementHandList.getLength() != 1) {
                throw new XMLParseException("Wrong number of MovementHand elements: "
                                            + String.valueOf(movementHandList.getLength()));
            }
            Node movementHandNode = movementHandList.item(0);
            movementHand = Hand.valueOf(movementHandNode.getTextContent());

            NodeList throttleMultiplierList = doc.getElementsByTagName("ThrottleMultiplier");
            if (throttleMultiplierList.getLength() != 1) {
                throw new XMLParseException("Wrong number of ThrottleMultiplier elements: "
                                            + String.valueOf(throttleMultiplierList.getLength()));
            }
            Node throttleMultiplierNode = throttleMultiplierList.item(0);
            throttleMultiplier = Float.valueOf(throttleMultiplierNode.getTextContent());

            NodeList rotationMultiplierList = doc.getElementsByTagName("RotationMultiplier");
            if (rotationMultiplierList.getLength() != 1) {
                throw new XMLParseException("Wrong number of RotationMultiplier elements: "
                                            + String.valueOf(rotationMultiplierList.getLength()));
            }
            Node rotationMultiplierNode = rotationMultiplierList.item(0);
            throttleMultiplier = Float.valueOf(rotationMultiplierNode.getTextContent());

            NodeList transmissionButtonList = doc.getElementsByTagName("TransmissionButton");
            if (transmissionButtonList.getLength() != 1) {
                throw new XMLParseException("Wrong number of TransmissionButton elements: "
                                            + String.valueOf(movementHandList.getLength()));
            }
            Node transmissionButtonNode = transmissionButtonList.item(0);
            transmissionButton = Integer.valueOf(transmissionButtonNode.getTextContent());
            transmissionButtonMode = ButtonMode.valueOf(transmissionButtonNode.getAttributes()
                                                        .getNamedItem("mode").getTextContent().toUpperCase());
            
            NodeList clawButtonList = doc.getElementsByTagName("ClawButton");
            if (clawButtonList.getLength() != 1) {
                throw new XMLParseException("Wrong number of ClawButton elements: "
                                            + String.valueOf(clawButtonList.getLength()));
            }
            Node clawButtonNode = clawButtonList.item(0);
            clawButton = Integer.valueOf(clawButtonNode.getTextContent());
            clawButtonMode = ButtonMode.valueOf(clawButtonNode.getAttributes()
                                                .getNamedItem("mode").getTextContent().toUpperCase());

            NodeList intakeInputAxisList = doc.getElementsByTagName("IntakeInputAxis");
            if (intakeInputAxisList.getLength() != 1) {
                throw new XMLParseException("Wrong number of IntakeInputAxis: "
                                            + String.valueOf(intakeInputAxisList.getLength()));
            }
            Node intakeInputAxisNode = intakeInputAxisList.item(0);
            intakeInputAxis = Integer.valueOf(intakeInputAxisNode.getTextContent());

            NodeList intakeOutputAxisList = doc.getElementsByTagName("IntakeOutputAxis");
            if (intakeOutputAxisList.getLength() != 1) {
                throw new XMLParseException("Wrong number of IntakeInputAxis: "
                                            + String.valueOf(intakeOutputAxisList.getLength()));
            }
            Node intakeOutputAxisNode = intakeOutputAxisList.item(0);
            intakeInputAxis = Integer.valueOf(intakeOutputAxisNode.getTextContent());
        } catch (Exception ex) {
            DriverStation.reportError("Failed to load XML file: " + ex.getMessage(), ex.getStackTrace());
            return;
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Hand getMovementHand() {
        return this.movementHand;
    }

    public void setMovementHand(Hand movementHand) {
        this.movementHand = movementHand;
    }

    public float getThrottleMultiplier() {
        return this.throttleMultiplier;
    }

    public void setThrottleMultiplier(float throttleMultiplier) {
        this.throttleMultiplier = throttleMultiplier;
    }

    public float getRotationMultiplier() {
        return this.rotationMultiplier;
    }

    public void setRotationMultiplier(float rotationMultiplier) {
        this.rotationMultiplier = rotationMultiplier;
    }

    public int getTransmissionButton() {
        return this.transmissionButton;
    }

    public void setTransmissionButton(int button) {
        this.transmissionButton = button;
    }

    public ButtonMode getTransmissionButtonMode() {
        return this.transmissionButtonMode;
    }

    public void setTransmissionButtonMode(ButtonMode mode) {
        this.transmissionButtonMode = mode;
    }

    public int getClawButton() {
        return this.clawButton;
    }

    public void setClawButton(int button) {
        this.clawButton = button;
    }

    public ButtonMode getClawButtonMode() {
        return this.clawButtonMode;
    }

    public void setClawButtonMode(ButtonMode mode) {
        this.clawButtonMode = mode;
    }

    public int getIntakeInputAxis() {
        return intakeInputAxis;
    }

    public void setIntakeInputAxis(int axis) {
        intakeInputAxis = axis;
    }

    public int getIntakeOutputAxis() {
        return intakeOutputAxis;
    }

    public void setIntakeOutputAxis(int axis) {
        intakeOutputAxis = axis;
    }

    /**
     * Saves this configuration to an XML file. The data is saved to ~/configs/$(this.username).xml
     */
    public void saveToXML(File file) {
        if (!verify()) {
            DriverStation.reportWarning("Verification failed when trying to save configuration", false);
            return;
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("Configuration");
            doc.appendChild(rootElement);

            Element movementHandElement = doc.createElement("MovementHand");
            movementHandElement.appendChild(doc.createTextNode(movementHand.name()));
            rootElement.appendChild(movementHandElement);

            Element throttleMultiplierElement = doc.createElement("ThrottleMultiplier");
            throttleMultiplierElement.appendChild(doc.createTextNode(String.valueOf(throttleMultiplier)));
            rootElement.appendChild(throttleMultiplierElement);

            Element rotationMultiplierElement = doc.createElement("RotationMultiplier");
            rotationMultiplierElement.appendChild(doc.createTextNode(String.valueOf(rotationMultiplier)));
            rootElement.appendChild(rotationMultiplierElement);

            Element transmissionButtonElement = doc.createElement("TransmissionButton");
            Attr transmissionButtonModeAttr = doc.createAttribute("mode");
            transmissionButtonModeAttr.setValue(transmissionButtonMode.name().toLowerCase());
            transmissionButtonElement.setAttributeNode(transmissionButtonModeAttr);
            transmissionButtonElement.appendChild(doc.createTextNode(String.valueOf(transmissionButton)));
            rootElement.appendChild(transmissionButtonElement);

            Element clawButtonElement = doc.createElement("ClawButton");
            Attr clawButtonModeAttr = doc.createAttribute("mode");
            clawButtonModeAttr.setValue(clawButtonMode.name().toLowerCase());
            clawButtonElement.setAttributeNode(clawButtonModeAttr);
            clawButtonElement.appendChild(doc.createTextNode(String.valueOf(clawButton)));
            rootElement.appendChild(clawButtonElement);

            Element intakeInputAxisElement = doc.createElement("IntakeInputAxis");
            intakeInputAxisElement.appendChild(doc.createTextNode(String.valueOf(intakeInputAxis)));
            rootElement.appendChild(intakeInputAxisElement);

            Element intakeOutputAxisElement = doc.createElement("IntakeOutputAxis");
            intakeOutputAxisElement.appendChild(doc.createTextNode(String.valueOf(intakeOutputAxis)));
            rootElement.appendChild(intakeOutputAxisElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            System.out.println("Saved to /home/lvuser/config.xml");
        } catch (Exception ex) {
            DriverStation.reportError("Failed to save XML file: " + ex.getMessage(), ex.getStackTrace());
            return;
        }
    }

    /**
     * Verify all values in the file to ensure that everything will work. This checks for:
     * - Missing username
     * 
     * @param outputToConsole If true, information will be logged to the console. This is ignored when connected to the FMS.
     * @return If true, all values are acceptable.
     */
    public boolean verify(boolean outputToConsole) {
        if (DriverStation.getInstance().isFMSAttached()) {
            outputToConsole = false;
        }

        if (username == null || username.equals("")) {
            if (outputToConsole) {
                System.out.println("Bad username");
            }
            return false;
        }

        return true;
    }

    /**
     * Verify all values in the file to ensure that everything will work. This checks for:
     * - Missing username
     * 
     * @return If true, all values are acceptable.
     */
    public boolean verify() {
        return verify(false);
    }
}