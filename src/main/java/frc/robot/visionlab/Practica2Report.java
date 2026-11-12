package frc.robot.visionlab;

import frc.robot.Constants.VisionLabConstants;
import frc.robot.practicas.MegaTag2;

/** Practice 2: MegaTag2 pose from botpose_orb_wpiblue, its rejection rules and the IMU mode. */
public final class Practica2Report {
  private Practica2Report() {}

  public static Report compute(LimelightSnapshot snapshot, double turnRateDegPerSec, boolean enabled) {
    return Report.run("Practica2", report -> {
      report.put("GiroGradosPorSeg", turnRateDegPerSec);
      double[] botpose = snapshot.botposeOrbWpiBlue();
      if (botpose.length < 11) {
        report.put("Estado", "sin datos de la camara");
        return;
      }

      int tagCount = (int) botpose[7];
      boolean turning = Math.abs(turnRateDegPerSec) > VisionLabConstants.kTurningThresholdDegPerSec;
      report.put("TagCount", tagCount)
          .put("X", botpose[0])
          .put("Y", botpose[1]);

      report.put("NecesitaYaw", MegaTag2.needsRobotYaw("MT2"))
          .put("Rechazar", MegaTag2.shouldRejectMt2(turnRateDegPerSec, tagCount))
          .put("ModoIMU", MegaTag2.imuMode(!enabled, enabled || turning))
          .put("StdDevs", MegaTag2.stdDevsMt2())
          .put("Estado", "ok");
    });
  }
}
