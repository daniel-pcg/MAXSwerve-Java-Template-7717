package frc.robot.visionlab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Practica1ReportTest {
  @Test
  void reportsMissingCameraData() {
    Report report = Practica1Report.compute(Snapshots.empty());
    assertEquals("sin datos de la camara", report.get("Estado"));
  }

  @Test
  void publishesCameraInputsAndNeverThrows() {
    double[] fiducials = {5, 0, 0, 1, 2.5, 2.6, 0.3};
    Report report = Practica1Report.compute(
        Snapshots.withBotpose(Snapshots.botpose(3, 4, 90, 1, 2.5), fiducials));

    assertEquals(1, report.get("TagCount"));
    assertEquals(0.3, (double) report.get("Ambiguedad"), 1e-9);
    assertEquals(2.5, (double) report.get("DistanciaCamaraM"), 1e-9);

    Object status = report.get("Estado");
    assertTrue("sin implementar".equals(status) || "ok".equals(status), String.valueOf(status));
    if ("ok".equals(status)) {
      assertEquals(3.0, (double) report.get("X"), 1e-3);
      assertEquals(5.0, (double) report.get("DistanciaAlOrigenM"), 1e-3);
      assertFalse((boolean) report.get("Rechazar"));
    }
  }
}
