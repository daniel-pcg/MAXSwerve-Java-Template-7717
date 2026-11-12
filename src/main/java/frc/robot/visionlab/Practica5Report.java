package frc.robot.visionlab;

import frc.robot.practicas.Fmap;
import frc.robot.practicas.Mapa;
import java.util.Optional;

/** Practice 5: check the classroom map by comparing the robot pose with the seen tag's position. */
public final class Practica5Report {
  private Practica5Report() {}

  public static Report compute(LimelightSnapshot snapshot, FieldMap map) {
    return Report.run("Practica5", report -> {
      if (!map.loaded()) {
        report.put("Estado", "mapa no cargado: " + map.error());
        return;
      }
      double[] botpose = snapshot.botposeFieldCenter();
      if (!snapshot.hasTarget() || botpose.length < 3) {
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

      double[] robotXyz = {botpose[0], botpose[1], botpose[2]};
      report.put("RobotXYZ", robotXyz);

      // Uses the student's practice 4 to read the tag position from the map.
      double[] tagXyz = Fmap.translation(tag.get().transform());
      report.put("TagXYZ", tagXyz)
          .put("DistanciaRobotTagM", Mapa.distance3d(robotXyz, tagXyz))
          .put("PuntoMedioXYZ", Mapa.midpoint(robotXyz, tagXyz))
          .put("Estado", "ok");
    });
  }
}
