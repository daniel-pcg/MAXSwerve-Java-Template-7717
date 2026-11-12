package frc.robot.visionlab;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Practica3ReportTest {
  @Test
  void reportsNoTag() {
    Report report = Practica3Report.compute(Snapshots.empty(), 0.3, 0, 0.5);
    assertEquals("sin tag", report.get("Estado"));
  }

  @Test
  void publishesCameraInputsAndNeverThrows() {
    LimelightSnapshot snapshot = new LimelightSnapshot(
        1, 5, Snapshots.NONE, Snapshots.NONE, Snapshots.NONE,
        new double[] {0, 0, 2, 0, 0, 0},
        new double[] {2.3, 0, 0.5, 0, 0, 0});
    Report report = Practica3Report.compute(snapshot, 0.3, 0, 0.5);

    assertArrayEquals(new double[] {0, 0, 2}, (double[]) report.get("CamaraXYZ"), 1e-9);
    assertArrayEquals(new double[] {2.3, 0, 0.5}, (double[]) report.get("LimelightRobotXYZ"), 1e-9);

    Object status = report.get("Estado");
    assertTrue("sin implementar".equals(status) || "ok".equals(status), String.valueOf(status));
    if ("ok".equals(status)) {
      assertArrayEquals(new double[] {2.3, 0, 0.5}, (double[]) report.get("TagEnRobotXYZ"), 1e-3);
      assertEquals(0.0, (double) report.get("DiferenciaConLimelightM"), 1e-3);
    }
  }
}
