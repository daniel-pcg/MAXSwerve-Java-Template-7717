package frc.robot.visionlab;

import frc.robot.practicas.Fmap;
import java.util.Optional;

/** Practice 4: read the seen tag from the .fmap map with the student's functions. */
public final class Practica4Report {
  private Practica4Report() {}

  public static Report compute(LimelightSnapshot snapshot, FieldMap map) {
    return Report.run("Practica4", report -> {
      if (!map.loaded()) {
        report.put("Estado", "mapa no cargado: " + map.error());
        return;
      }
      if (!snapshot.hasTarget()) {
        report.put("Estado", "sin tag");
        return;
      }

      int id = (int) snapshot.tid();
      report.put("TagVisto", id);
      Optional<FieldMap.Tag> tag = map.tag(id);
      if (tag.isEmpty()) {
        report.put("Estado", "el tag " + id + " no esta en el mapa");
        return;
      }

      double[] transform = tag.get().transform();
      report.put("XYZ", Fmap.translation(transform))
          .put("YawGrados", Fmap.yawDegrees(transform))
          .put("TamanoM", Fmap.sizeMeters(tag.get().sizeMm()))
          .put("Estado", "ok");
    });
  }
}
