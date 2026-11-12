package frc.robot.practicas;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LocalizacionTest {
  private static final double[] BOTPOSE = {1.5, 2.0, 0.0, 0.0, 0.0, 90.0};

  @Test
  void botposeX() {
    assertEquals(1.5, Pending.call(() -> Localizacion.botposeX(BOTPOSE)), 1e-3);
  }

  @Test
  void botposeYawDegrees() {
    assertEquals(90.0, Pending.call(() -> Localizacion.botposeYawDegrees(BOTPOSE)), 1e-3);
  }

  @Test
  void distanceMeters() {
    double[] a = {1, 1, 0, 0, 0, 0};
    double[] b = {4, 5, 0, 0, 0, 0};
    assertEquals(5.0, Pending.call(() -> Localizacion.distanceMeters(a, b)), 1e-3);
  }

  @Test
  void rejectsWhenNoTags() {
    assertTrue(Pending.call(() -> Localizacion.shouldReject(0, 0.0, 0.0)));
  }

  @Test
  void rejectsSingleTagWithHighAmbiguity() {
    assertTrue(Pending.call(() -> Localizacion.shouldReject(1, 0.8, 1.0)));
  }

  @Test
  void rejectsSingleTagFarAway() {
    assertTrue(Pending.call(() -> Localizacion.shouldReject(1, 0.5, 4.0)));
  }

  @Test
  void acceptsCloseClearSingleTag() {
    assertFalse(Pending.call(() -> Localizacion.shouldReject(1, 0.5, 2.0)));
  }

  @Test
  void acceptsMultipleTags() {
    assertFalse(Pending.call(() -> Localizacion.shouldReject(2, 0.9, 5.0)));
  }

  @Test
  void stdDevsMt1() {
    assertArrayEquals(new double[] {0.5, 0.5, 9999999.0}, Pending.call(Localizacion::stdDevsMt1), 1e-3);
  }
}
