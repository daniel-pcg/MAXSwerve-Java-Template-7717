package frc.robot.visionlab;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.Test;

class Practica4ReportTest {
  private static final double[] TAG_1 = {
    -0.5, -0.866025, 0, 6.808597,
    0.866025, -0.5, 0, -3.859403,
    0, 0, 1, 1.355852,
    0, 0, 0, 1
  };
  private static final FieldMap MAP = FieldMap.of(Map.of(1, new FieldMap.Tag(165.1, TAG_1)));

  private static LimelightSnapshot seeing(int tagId) {
    return new LimelightSnapshot(1, tagId, Snapshots.NONE, Snapshots.NONE, Snapshots.NONE, Snapshots.NONE, Snapshots.NONE);
  }

  @Test
  void reportsMapNotLoaded() {
    FieldMap missing = FieldMap.load(Path.of("no-existe.fmap"));
    Report report = Practica4Report.compute(seeing(1), missing);
    assertEquals("mapa no cargado: no se pudo leer no-existe.fmap", report.get("Estado"));
  }

  @Test
  void reportsNoTag() {
    Report report = Practica4Report.compute(Snapshots.empty(), MAP);
    assertEquals("sin tag", report.get("Estado"));
  }

  @Test
  void reportsTagMissingFromTheMap() {
    Report report = Practica4Report.compute(seeing(7), MAP);
    assertEquals("el tag 7 no esta en el mapa", report.get("Estado"));
  }

  @Test
  void readsTheSeenTagAndNeverThrows() {
    Report report = Practica4Report.compute(seeing(1), MAP);
    assertEquals(1, report.get("TagVisto"));

    Object status = report.get("Estado");
    assertTrue("sin implementar".equals(status) || "ok".equals(status), String.valueOf(status));
    if ("ok".equals(status)) {
      assertArrayEquals(new double[] {6.808597, -3.859403, 1.355852}, (double[]) report.get("XYZ"), 1e-3);
      assertEquals(120.0, (double) report.get("YawGrados"), 1e-3);
      assertEquals(0.1651, (double) report.get("TamanoM"), 1e-3);
    }
  }
}
