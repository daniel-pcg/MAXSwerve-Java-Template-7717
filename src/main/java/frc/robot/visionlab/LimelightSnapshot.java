package frc.robot.visionlab;

import edu.wpi.first.networktables.NetworkTable;

/** One cycle of raw Limelight data, read from NetworkTables. */
public record LimelightSnapshot(
    double tv,
    double tid,
    double[] botposeWpiBlue,
    double[] botposeOrbWpiBlue,
    double[] rawFiducials,
    double[] targetPoseCameraSpace,
    double[] targetPoseRobotSpace,
    double[] botposeFieldCenter) {

  public static LimelightSnapshot read(NetworkTable table) {
    return new LimelightSnapshot(
        table.getEntry("tv").getDouble(0),
        table.getEntry("tid").getDouble(-1),
        table.getEntry("botpose_wpiblue").getDoubleArray(new double[0]),
        table.getEntry("botpose_orb_wpiblue").getDoubleArray(new double[0]),
        table.getEntry("rawfiducials").getDoubleArray(new double[0]),
        table.getEntry("targetpose_cameraspace").getDoubleArray(new double[0]),
        table.getEntry("targetpose_robotspace").getDoubleArray(new double[0]),
        // "botpose" is the robot pose in field space, with the origin at the field center,
        // the same frame the .fmap map uses.
        table.getEntry("botpose").getDoubleArray(new double[0]));
  }

  public boolean hasTarget() {
    return tv >= 0.5;
  }
}
