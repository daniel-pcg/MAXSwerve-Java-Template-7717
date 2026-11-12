package frc.robot.practicas;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MegaTag2Test {
  @Test
  void megaTag2NeedsYaw() {
    assertTrue(Pending.call(() -> MegaTag2.needsRobotYaw("MT2")));
  }

  @Test
  void megaTag1DoesNotNeedYaw() {
    assertFalse(Pending.call(() -> MegaTag2.needsRobotYaw("MT1")));
  }

  @Test
  void acceptsNormalTurning() {
    assertFalse(Pending.call(() -> MegaTag2.shouldRejectMt2(100.0, 2)));
  }

  @Test
  void rejectsFastTurningEitherWay() {
    assertTrue(Pending.call(() -> MegaTag2.shouldRejectMt2(-400.0, 2)));
  }

  @Test
  void rejectsWhenNoTags() {
    assertTrue(Pending.call(() -> MegaTag2.shouldRejectMt2(0.0, 0)));
  }

  @Test
  void acceptsExactlyAtTheLimit() {
    assertFalse(Pending.call(() -> MegaTag2.shouldRejectMt2(360.0, 1)));
  }

  @Test
  void rejectsJustOverTheLimit() {
    assertTrue(Pending.call(() -> MegaTag2.shouldRejectMt2(361.0, 1)));
  }

  @Test
  void stdDevsMt2() {
    assertArrayEquals(new double[] {0.7, 0.7, 9999999.0}, Pending.call(MegaTag2::stdDevsMt2), 1e-3);
  }

  @Test
  void imuModeWhileWaitingForAuto() {
    assertEquals(1, Pending.call(() -> MegaTag2.imuMode(true, false)));
  }

  @Test
  void imuModeWhileEnabledOrTurning() {
    assertEquals(2, Pending.call(() -> MegaTag2.imuMode(false, true)));
  }

  @Test
  void enabledWinsOverWaiting() {
    assertEquals(2, Pending.call(() -> MegaTag2.imuMode(true, true)));
  }

  @Test
  void imuModeOtherwise() {
    assertEquals(0, Pending.call(() -> MegaTag2.imuMode(false, false)));
  }
}
