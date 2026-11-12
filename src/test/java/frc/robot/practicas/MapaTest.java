package frc.robot.practicas;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MapaTest {
  @Test
  void distanceOnTheFloor() {
    assertEquals(5.0, Pending.call(() -> Mapa.distance3d(new double[] {0, 0, 0}, new double[] {3, 4, 0})), 1e-3);
  }

  @Test
  void distanceWithHeight() {
    assertEquals(3.0, Pending.call(() -> Mapa.distance3d(new double[] {1, 1, 1}, new double[] {1, 1, 4})), 1e-3);
  }

  @Test
  void midpoint() {
    assertArrayEquals(
        new double[] {1, 3, 6},
        Pending.call(() -> Mapa.midpoint(new double[] {0, 2, 4}, new double[] {2, 4, 8})),
        1e-3);
  }

  @Test
  void midpointOfTheSamePoint() {
    assertArrayEquals(
        new double[] {5, 5, 5},
        Pending.call(() -> Mapa.midpoint(new double[] {5, 5, 5}, new double[] {5, 5, 5})),
        1e-3);
  }
}
