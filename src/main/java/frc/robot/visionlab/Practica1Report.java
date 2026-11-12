package frc.robot.visionlab;

import frc.robot.practicas.Localizacion;

/** Practice 1: MegaTag1 pose from botpose_wpiblue and the rejection rules. */
public final class Practica1Report {
  private Practica1Report() {}

  public static Report compute(LimelightSnapshot snapshot) {
    return Report.run("Practica1", report -> {
      double[] botpose = snapshot.botposeWpiBlue();
      if (botpose.length < 11) {
        report.put("Estado", "sin datos de la camara");
        return;
      }

      // botpose_wpiblue: [7] tag count, [9] average tag distance.
      // rawfiducials, per tag: id, txnc, tync, ta, distToCamera, distToRobot, ambiguity.
      int tagCount = (int) botpose[7];
      double[] fiducials = snapshot.rawFiducials();
      double ambiguity = fiducials.length >= 7 ? fiducials[6] : 0.0;
      double distToCamera = fiducials.length >= 7 ? fiducials[4] : botpose[9];
      report.put("TagCount", tagCount)
          .put("Ambiguedad", ambiguity)
          .put("DistanciaCamaraM", distToCamera);

      report.put("X", Localizacion.botposeX(botpose))
          .put("YawGrados", Localizacion.botposeYawDegrees(botpose))
          .put("DistanciaAlOrigenM", Localizacion.distanceMeters(new double[6], botpose))
          .put("Rechazar", Localizacion.shouldReject(tagCount, ambiguity, distToCamera))
          .put("StdDevs", Localizacion.stdDevsMt1())
          .put("Estado", "ok");
    });
  }
}
