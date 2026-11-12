package frc.robot.visionlab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Practica2ReportTest {
  @Test
  void reportsMissingCameraData() {
    Report report = Practica2Report.compute(Snapshots.empty(), 0, false);
    assertEquals("sin datos de la camara", report.get("Estado"));
    assertEquals(0.0, (double) report.get("GiroGradosPorSeg"), 1e-9);
  }

  @Test
  void publishesCameraInputsAndNeverThrows() {
    Report report = Practica2Report.compute(
        Snapshots.withBotpose(Snapshots.botpose(3, 4, 90, 2, 2.0), Snapshots.NONE), 50, false);

    assertEquals(2, report.get("TagCount"));
    assertEquals(3.0, (double) report.get("X"), 1e-9);

    Object status = report.get("Estado");
    assertTrue("sin implementar".equals(status) || "ok".equals(status), String.valueOf(status));
    if ("ok".equals(status)) {
      assertFalse((boolean) report.get("Rechazar"));
      assertEquals(2, report.get("ModoIMU"));
    }
  }
}
