/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.autonomous.runs.*;
import frc.robot.subsystems.*;

import frc.utils.configuration.ConfigurationManager;
import frc.utils.MB1013;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI oi = new OI();
  public static ConfigurationManager configManager = new ConfigurationManager();

  public static DriveTrain driveTrain = new DriveTrain();
  public static Arm arm = new Arm();
  public static Claw claw = new Claw();

  public static Compressor compressor = new Compressor(RobotMap.compressor);

  public static Command auto;
  public static SendableChooser<String> autoChooser;

  public static MB1013 distanceSensor = new MB1013(1);  // TODO: don't hardcode this
  public static PigeonIMU pigeon = new PigeonIMU(0);  // TODO: don't hardcode this

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    compressor.setClosedLoopControl(true);
    CameraServer.getInstance().startAutomaticCapture();
    configManager.setConfigurationID(1);

    autoChooser = new SendableChooser<>();
    autoChooser.addDefault("Cross line", "c");
    autoChooser.addObject("Start left side", "l");
    autoChooser.addObject("Start middle", "m");
    autoChooser.addObject("Start right side", "r");
    SmartDashboard.putData("auto_chooser", autoChooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("DistanceSensor", distanceSensor.getDistance());

    short[] accel = new short[3];
    pigeon.getBiasedAccelerometer(accel);
    SmartDashboard.putNumber("PigeonAccelX", accel[0]);
    SmartDashboard.putNumber("PigeonAccelY", accel[1]);
    SmartDashboard.putNumber("PigeonAccelZ", accel[2]);

    double[] ypr = new double[3];
    pigeon.getYawPitchRoll(ypr);
    SmartDashboard.putNumber("PigeonYaw", ypr[0]);
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */
    
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() != 3) {
      DriverStation.reportError("FMS data is incorrect: " + gameData, false);
      return;  // TODO: implement "cross line and stop" failsafe
    }
    String switchSide = gameData.substring(0, 1);

    String autoSelected = SmartDashboard.getString("auto_chooser", "c");
    switch (autoSelected) {
      case "l":
        if (switchSide.equals("L"))
          auto = new LeftToLeft();
        else if (switchSide.equals("R"))
          auto = new CrossLine();
        else {
          DriverStation.reportError("FMS data is incorrect: " + gameData, false);
          auto = new CrossLine();
        }
        break;
      case "m":
        if (switchSide.equals("L"))
          auto = new MiddleToLeft();
        else if (switchSide.equals("R"))
          auto = new MiddleToRight();
        else {
          DriverStation.reportError("FMS data is incorrect: " + gameData, false);
          auto = new CrossLine();
        }
        break;
      case "r":
        if (switchSide.equals("L"))
          auto = new CrossLine();
        else if (switchSide.equals("R"))
          // auto = new RightToRight();
          auto = new CrossLine();
        else {
          DriverStation.reportError("FMS data is incorrect: " + gameData, false);
          auto = new CrossLine();
        }
        break;
      case "c":
      default:
        auto = new CrossLine();
        break;
    }

    if (switchSide.equals("L"))
      auto = new MiddleToLeft();
    else if (switchSide.equals("R"))
      auto = new MiddleToRight();
    else {
      DriverStation.reportError("FMS data is incorrect: " + gameData, false);
      return;  // TODO: implement "cross line and stop" failsafe
    }

    auto.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // auto.cancel();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }
}
