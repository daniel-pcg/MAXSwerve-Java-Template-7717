package frc.robot.practicas;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoordenadasTest {
  @Test
  void pointInFrontOfTheCamera() {
    assertArrayEquals(
        new double[] {2.3, 0.0, 0.5},
        Pending.call(() -> Coordenadas.cameraPointToRobot(new double[] {0, 0, 2}, 0.3, 0.0, 0.5)),
        1e-3);
  }

  @Test
  void pointToTheRightOfTheCamera() {
    assertArrayEquals(
        new double[] {2.3, 0.5, 0.5},
        Pending.call(() -> Coordenadas.cameraPointToRobot(new double[] {0.4, 0, 2}, 0.3, 0.1, 0.5)),
        1e-3);
  }

  @Test
  void pointAboveTheCameraAxis() {
    assertArrayEquals(
        new double[] {2.3, 0.0, 0.7},
        Pending.call(() -> Coordenadas.cameraPointToRobot(new double[] {0, -0.2, 2}, 0.3, 0.0, 0.5)),
        1e-3);
  }

  @Test
  void tenCentimeterError() {
    assertEquals(
        0.1,
        Pending.call(() -> Coordenadas.offsetErrorMeters(new double[] {2.3, 0, 0.5}, new double[] {2.4, 0, 0.5})),
        1e-3);
  }

  @Test
  void distanceIn3d() {
    assertEquals(
        7.0,
        Pending.call(() -> Coordenadas.offsetErrorMeters(new double[] {0, 0, 0}, new double[] {2, 3, 6})),
        1e-3);
  }

  @Test
  void wpiBlueIsRecommended() {
    assertTrue(Pending.call(() -> Coordenadas.isRecommendedForFrc("WPIBlue")));
  }

  @Test
  void wpiRedIsNotRecommended() {
    assertFalse(Pending.call(() -> Coordenadas.isRecommendedForFrc("WPIRed")));
  }

  @Test
  void centeredFrcIsNotThePreferredOne() {
    assertFalse(Pending.call(() -> Coordenadas.isRecommendedForFrc("FRC")));
  }
}
