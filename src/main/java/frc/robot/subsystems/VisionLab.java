package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionLabConstants;
import frc.robot.visionlab.LimelightSnapshot;
import frc.robot.visionlab.Practica1Report;
import frc.robot.visionlab.Report;
import java.util.Map;

/** Reads the Limelight every cycle and publishes each practice's results to the dashboard. */
public class VisionLab extends SubsystemBase {
  private final NetworkTable m_limelight =
      NetworkTableInstance.getDefault().getTable(VisionLabConstants.kLimelightTableName);

  @Override
  public void periodic() {
    LimelightSnapshot snapshot = LimelightSnapshot.read(m_limelight);
    SmartDashboard.putBoolean("VisionLab/VeTag", snapshot.hasTarget());
    SmartDashboard.putNumber("VisionLab/TagId", snapshot.tid());

    publish(Practica1Report.compute(snapshot));
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
