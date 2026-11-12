package frc.robot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ApproachTagControllerTest {
  @Test
  void drivesForwardWhenFar() {
    ApproachTagController.Output output = ApproachTagController.calculate(2.0, 0.0);
    assertEquals(1.0, output.forward(), 1e-9);
    assertEquals(0.0, output.rotation(), 1e-9);
    assertFalse(output.atGoal());
  }

  @Test
  void backsUpWhenTooClose() {
    ApproachTagController.Output output = ApproachTagController.calculate(0.5, 0.0);
    assertEquals(-0.5, output.forward(), 1e-9);
  }

  @Test
  void stopsAtOneMeterWhenAligned() {
    ApproachTagController.Output output = ApproachTagController.calculate(1.02, 1.0);
    assertEquals(0.0, output.forward(), 1e-9);
    assertEquals(0.0, output.rotation(), 1e-9);
    assertTrue(output.atGoal());
  }

  @Test
  void turnsClockwiseTowardATagOnTheRight() {
    ApproachTagController.Output output = ApproachTagController.calculate(1.0, 10.0);
    assertEquals(-0.3, output.rotation(), 1e-9);
    assertFalse(output.atGoal());
  }

  @Test
  void turnsCounterclockwiseTowardATagOnTheLeft() {
    assertEquals(0.3, ApproachTagController.calculate(1.0, -10.0).rotation(), 1e-9);
  }

  @Test
  void clampsLargeOutputs() {
    ApproachTagController.Output output = ApproachTagController.calculate(6.0, 90.0);
    assertEquals(1.0, output.forward(), 1e-9);
    assertEquals(-1.0, output.rotation(), 1e-9);
  }
}
