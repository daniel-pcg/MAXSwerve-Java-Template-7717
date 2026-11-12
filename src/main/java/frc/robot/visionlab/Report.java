package frc.robot.visionlab;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/** Dashboard values that one practice produces in one cycle. */
public final class Report {
  private final String m_prefix;
  private final Map<String, Object> m_values = new LinkedHashMap<>();

  private Report(String prefix) {
    m_prefix = prefix;
  }

  /**
   * Runs one practice. Exceptions thrown by student code become a status message, so an
   * unfinished practice never stops the robot program.
   */
  public static Report run(String prefix, Consumer<Report> body) {
    Report report = new Report(prefix);
    try {
      body.accept(report);
    } catch (UnsupportedOperationException e) {
      report.put("Estado", "sin implementar");
    } catch (RuntimeException e) {
      report.put("Estado", "error: " + e.getClass().getSimpleName());
    }
    return report;
  }

  public Report put(String key, Object value) {
    m_values.put(m_prefix + "/" + key, value);
    return this;
  }

  public Object get(String key) {
    return m_values.get(m_prefix + "/" + key);
  }

  public Map<String, Object> values() {
    return m_values;
  }
}
