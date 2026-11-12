package frc.robot.visionlab;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;

class Practica5ReportTest {
  private static final double[] TAG_AT_3_4_0 = {
    1, 0, 0, 3,
    0, 1, 0, 4,
    0, 0, 1, 0,
    0, 0, 0, 1
  };
  private static final FieldMap MAP = FieldMap.of(Map.of(2, new FieldMap.Tag(165.1, TAG_AT_3_4_0)));

  private static LimelightSnapshot robotAtOriginSeeing(int tagId) {
    double[] botpose = {0, 0, 0, 0, 0, 0};
    return new LimelightSnapshot(
        1, tagId, Snapshots.NONE, Snapshots.NONE, Snapshots.NONE, Snapshots.NONE, Snapshots.NONE, botpose);
  }

  @Test
  void reportsNoTag() {
    assertEquals("sin tag", Practica5Report.compute(Snapshots.empty(), MAP).get("Estado"));
  }

  @Test
  void reportsTagMissingFromTheMap() {
    Report report = Practica5Report.compute(robotAtOriginSeeing(9), MAP);
    assertEquals("el tag 9 no esta en el mapa", report.get("Estado"));
  }

  @Test
  void comparesRobotAndTagAndNeverThrows() {
    Report report = Practica5Report.compute(robotAtOriginSeeing(2), MAP);
    assertArrayEquals(new double[] {0, 0, 0}, (double[]) report.get("RobotXYZ"), 1e-9);

    Object status = report.get("Estado");
    assertTrue("sin implementar".equals(status) || "ok".equals(status), String.valueOf(status));
    if ("ok".equals(status)) {
      assertArrayEquals(new double[] {3, 4, 0}, (double[]) report.get("TagXYZ"), 1e-3);
      assertEquals(5.0, (double) report.get("DistanciaRobotTagM"), 1e-3);
      assertArrayEquals(new double[] {1.5, 2, 0}, (double[]) report.get("PuntoMedioXYZ"), 1e-3);
    }
  }
}
