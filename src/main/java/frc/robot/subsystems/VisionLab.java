package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionLabConstants;
import frc.robot.visionlab.LimelightSnapshot;
import frc.robot.visionlab.Practica1Report;
import frc.robot.visionlab.Practica2Report;
import frc.robot.visionlab.Report;
import java.util.Map;
import java.util.function.DoubleSupplier;

/** Reads the Limelight every cycle and publishes each practice's results to the dashboard. */
public class VisionLab extends SubsystemBase {
  private final NetworkTable m_limelight =
      NetworkTableInstance.getDefault().getTable(VisionLabConstants.kLimelightTableName);
  private final DoubleSupplier m_headingDegrees;
  private final DoubleSupplier m_turnRateDegPerSec;

  public VisionLab(DoubleSupplier headingDegrees, DoubleSupplier turnRateDegPerSec) {
    m_headingDegrees = headingDegrees;
    m_turnRateDegPerSec = turnRateDegPerSec;
  }

  @Override
  public void periodic() {
    double turnRate = m_turnRateDegPerSec.getAsDouble();
    // MegaTag2 needs the robot yaw every cycle: [yaw, yawRate, pitch, pitchRate, roll, rollRate].
    m_limelight.getEntry("robot_orientation_set")
        .setDoubleArray(new double[] {m_headingDegrees.getAsDouble(), turnRate, 0, 0, 0, 0});

    LimelightSnapshot snapshot = LimelightSnapshot.read(m_limelight);
    SmartDashboard.putBoolean("VisionLab/VeTag", snapshot.hasTarget());
    SmartDashboard.putNumber("VisionLab/TagId", snapshot.tid());

    publish(Practica1Report.compute(snapshot));

    Report megaTag2 = Practica2Report.compute(snapshot, turnRate, DriverStation.isEnabled());
    publish(megaTag2);
    if (VisionLabConstants.kSendImuModeToCamera && megaTag2.get("ModoIMU") instanceof Number mode) {
      m_limelight.getEntry("imumode_set").setNumber(mode.intValue());
    }
  }

  private static void publish(Report report) {
    for (Map.Entry<String, Object> entry : report.values().entrySet()) {
      Object value = entry.getValue();
      if (value instanceof Number number) {
        SmartDashboard.putNumber(entry.getKey(), number.doubleValue());
      } else if (value instanceof Boolean flag) {
        SmartDashboard.putBoolean(entry.getKey(), flag);
      } else if (value instanceof double[] array) {
        SmartDashboard.putNumberArray(entry.getKey(), array);
      } else {
        SmartDashboard.putString(entry.getKey(), String.valueOf(value));
      }
    }
  }
}
