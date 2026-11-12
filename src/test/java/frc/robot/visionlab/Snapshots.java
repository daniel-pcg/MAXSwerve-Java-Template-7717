package frc.robot.visionlab;

final class Snapshots {
  private Snapshots() {}

  static final double[] NONE = new double[0];

  static double[] botpose(double x, double y, double yaw, int tagCount, double avgDist) {
    return new double[] {x, y, 0, 0, 0, yaw, 20, tagCount, 0, avgDist, 1};
  }

  static LimelightSnapshot withBotpose(double[] botpose, double[] rawFiducials) {
    return new LimelightSnapshot(1, 5, botpose, botpose, rawFiducials, NONE, NONE);
  }

  static LimelightSnapshot empty() {
    return new LimelightSnapshot(0, -1, NONE, NONE, NONE, NONE, NONE);
  }
}
