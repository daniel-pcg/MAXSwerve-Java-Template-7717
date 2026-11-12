package frc.robot.practicas;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FmapTest {
  private static final double[] TAG_1 = {
    -0.5, -0.866025, 0, 6.808597,
    0.866025, -0.5, 0, -3.859403,
    0, 0, 1, 1.355852,
    0, 0, 0, 1
  };

  @Test
  void indexRow0Col0() {
    assertEquals(0, Pending.call(() -> Fmap.index(0, 0)));
  }

  @Test
  void indexRow1Col0() {
    assertEquals(4, Pending.call(() -> Fmap.index(1, 0)));
  }

  @Test
  void indexRow2Col3() {
    assertEquals(11, Pending.call(() -> Fmap.index(2, 3)));
  }

  @Test
  void translationOfTag1() {
    assertArrayEquals(
        new double[] {6.808597, -3.859403, 1.355852},
        Pending.call(() -> Fmap.translation(TAG_1)),
        1e-3);
  }

  @Test
  void sizeInMeters() {
    assertEquals(0.1651, Pending.call(() -> Fmap.sizeMeters(165.1)), 1e-3);
  }

  @Test
  void yawOfTag1() {
    assertEquals(120.0, Pending.call(() -> Fmap.yawDegrees(TAG_1)), 1e-3);
  }
}
