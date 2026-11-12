package frc.robot.visionlab;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/** AprilTag map read from a Limelight .fmap file. */
public final class FieldMap {
  /** One fiducial: its size in millimeters and its 4x4 transform stored row by row. */
  public record Tag(double sizeMm, double[] transform) {}

  private final Map<Integer, Tag> m_tags;
  private final String m_error;

  private FieldMap(Map<Integer, Tag> tags, String error) {
    m_tags = tags;
    m_error = error;
  }

  public static FieldMap of(Map<Integer, Tag> tags) {
    return new FieldMap(new TreeMap<>(tags), null);
  }

  /** Reads the file. A missing or broken file gives an empty map with an error, never an exception. */
  public static FieldMap load(Path file) {
    try {
      JsonNode root = new ObjectMapper().readTree(file.toFile());
      Map<Integer, Tag> tags = new TreeMap<>();
      for (JsonNode fiducial : root.path("fiducials")) {
        JsonNode transform = fiducial.path("transform");
        if (transform.size() != 16) {
          continue;
        }
        double[] values = new double[16];
        for (int i = 0; i < 16; i++) {
          values[i] = transform.get(i).asDouble();
        }
        tags.put(fiducial.path("id").asInt(), new Tag(fiducial.path("size").asDouble(), values));
      }
      if (tags.isEmpty()) {
        return new FieldMap(Map.of(), "el mapa no tiene tags validos");
      }
      return new FieldMap(tags, null);
    } catch (IOException e) {
      return new FieldMap(Map.of(), "no se pudo leer " + file.getFileName());
    }
  }

  public boolean loaded() {
    return m_error == null;
  }

  public String error() {
    return m_error;
  }

  public int size() {
    return m_tags.size();
  }

  public Optional<Tag> tag(int id) {
    return Optional.ofNullable(m_tags.get(id));
  }
}
