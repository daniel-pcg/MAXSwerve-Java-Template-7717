package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ApproachTagConstants;
import frc.robot.subsystems.DriveSubsystem;

/** Drives toward whichever AprilTag the Limelight sees and holds 1 m from it. Stops when no tag. */
public class ApproachTagCommand extends Command {
  private final DriveSubsystem m_drive;
  private final NetworkTable m_limelight =
      NetworkTableInstance.getDefault().getTable(ApproachTagConstants.kLimelightTableName);

  public ApproachTagCommand(DriveSubsystem drive) {
    m_drive = drive;
    addRequirements(drive);
  }

  @Override
  public void execute() {
    boolean hasTarget = m_limelight.getEntry("tv").getDouble(0) >= 0.5;
    // targetpose_cameraspace: [x, y, z, ...] in meters; z is the distance straight out of the lens.
    double[] targetInCamera =
        m_limelight.getEntry("targetpose_cameraspace").getDoubleArray(new double[0]);
    if (!hasTarget || targetInCamera.length < 3) {
      m_drive.drive(0, 0, 0, false);
      SmartDashboard.putString("ApproachTag/Estado", "sin tag");
      return;
    }

    double distanceMeters = targetInCamera[2];
    double txDegrees = m_limelight.getEntry("tx").getDouble(0);
    ApproachTagController.Output output = ApproachTagController.calculate(distanceMeters, txDegrees);
    m_drive.drive(output.forward(), 0, output.rotation(), false);

    SmartDashboard.putNumber("ApproachTag/TagId", m_limelight.getEntry("tid").getDouble(-1));
    SmartDashboard.putNumber("ApproachTag/DistanciaM", distanceMeters);
    SmartDashboard.putNumber("ApproachTag/TxGrados", txDegrees);
    SmartDashboard.putString("ApproachTag/Estado", output.atGoal() ? "a 1 m" : "acercandose");
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.drive(0, 0, 0, false);
  }
}
