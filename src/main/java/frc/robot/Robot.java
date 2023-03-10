// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;

  private final WPI_VictorSPX m_leftFrontMotor = new WPI_VictorSPX(1);
  private final WPI_VictorSPX m_leftRearMotor = new WPI_VictorSPX(2);
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_leftFrontMotor, m_leftRearMotor);

  private final WPI_VictorSPX m_rightFrontMotor = new WPI_VictorSPX(3);
  private final WPI_VictorSPX m_rightRearMotor = new WPI_VictorSPX(4);
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_rightFrontMotor, m_rightRearMotor);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightFrontMotor.setInverted(true);
    m_rightRearMotor.setInverted(true);

    m_myRobot = new DifferentialDrive(m_leftMotors, m_rightMotors);
    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(-m_leftStick.getY(), -m_rightStick.getY());
  }
}
