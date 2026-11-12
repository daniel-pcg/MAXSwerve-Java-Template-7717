package frc.robot.practicas;

import static org.junit.jupiter.api.Assumptions.abort;

import java.util.function.Supplier;

/** Skips a test, instead of failing it, while the practice function is not implemented yet. */
final class Pending {
  private Pending() {}

  static <T> T call(Supplier<T> body) {
    try {
      return body.get();
    } catch (UnsupportedOperationException e) {
      return abort("sin implementar");
    }
  }
}
