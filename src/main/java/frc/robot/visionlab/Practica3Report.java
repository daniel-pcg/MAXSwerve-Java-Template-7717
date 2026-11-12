package frc.robot.visionlab;

import frc.robot.practicas.Coordenadas;

/** Practice 3: move the seen tag from camera space to robot space and compare with the Limelight. */
public final class Practica3Report {
  private Practica3Report() {}

  public static Report compute(LimelightSnapshot snapshot, double forward, double right, double up) {
    return Report.run("Practica3", report -> {
      double[] camera = snapshot.targetPoseCameraSpace();
      if (!snapshot.hasTarget() || camera.length < 3) {
        report.put("Estado", "sin tag");
        return;
      }

      // targetpose_cameraspace and targetpose_robotspace start with the tag's x, y, z in meters.
      double[] cameraXyz = {camera[0], camera[1], camera[2]};
      report.put("CamaraXYZ", cameraXyz);
      double[] limelight = snapshot.targetPoseRobotSpace();
      double[] limelightXyz = limelight.length >= 3
          ? new double[] {limelight[0], limelight[1], limelight[2]}
          : null;
      if (limelightXyz != null) {
        report.put("LimelightRobotXYZ", limelightXyz);
      }

      double[] robotXyz = Coordenadas.cameraPointToRobot(cameraXyz, forward, right, up);
      report.put("TagEnRobotXYZ", robotXyz);
      if (limelightXyz != null) {
        report.put("DiferenciaConLimelightM", Coordenadas.offsetErrorMeters(robotXyz, limelightXyz));
      }
      report.put("WPIBlueRecomendado", Coordenadas.isRecommendedForFrc("WPIBlue"))
          .put("Estado", "ok");
    });
  }
}
