package frc.robot.visionlab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FieldMapTest {
  @Test
  void loadsTheDeployedClassroomMap() {
    FieldMap map = FieldMap.load(Path.of("src/main/deploy/aula.fmap"));
    assertTrue(map.loaded(), map.error());
    assertEquals(1, map.size());
    FieldMap.Tag tag = map.tag(1).orElseThrow();
    assertEquals(165.1, tag.sizeMm(), 1e-9);
    assertEquals(6.808597, tag.transform()[3], 1e-9);
    assertEquals(-3.859403, tag.transform()[7], 1e-9);
  }

  @Test
  void missingFileGivesAnErrorInsteadOfThrowing(@TempDir Path dir) {
    FieldMap map = FieldMap.load(dir.resolve("no-existe.fmap"));
    assertFalse(map.loaded());
    assertEquals(0, map.size());
  }

  @Test
  void skipsFiducialsWithoutAFullTransform(@TempDir Path dir) throws IOException {
    Path file = dir.resolve("roto.fmap");
    Files.writeString(file, "{\"type\": \"frc\", \"fiducials\": [{\"id\": 3, \"size\": 100, \"transform\": [1, 2]}]}");
    FieldMap map = FieldMap.load(file);
    assertFalse(map.loaded());
    assertEquals("el mapa no tiene tags validos", map.error());
  }
}
