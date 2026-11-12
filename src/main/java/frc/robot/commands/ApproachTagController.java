package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import frc.robot.Constants.ApproachTagConstants;

/** Turns a Limelight reading into drive outputs that bring the robot to a fixed distance from a tag. */
public final class ApproachTagController {
  /** Outputs in [-1, 1], as DriveSubsystem.drive expects. Positive rotation is counterclockwise. */
  public record Output(double forward, double rotation, boolean atGoal) {}

  private ApproachTagController() {}

  /**
   * @param distanceMeters forward distance from the camera to the tag (targetpose_cameraspace z)
   * @param txDegrees horizontal offset of the tag; positive means the tag is to the right
   */
  public static Output calculate(double distanceMeters, double txDegrees) {
    double distanceError = distanceMeters - ApproachTagConstants.kTargetDistanceMeters;
    boolean atDistance = Math.abs(distanceError) < ApproachTagConstants.kDistanceToleranceMeters;
    boolean aligned = Math.abs(txDegrees) < ApproachTagConstants.kAngleToleranceDegrees;

    double forward = atDistance
        ? 0.0
        : MathUtil.clamp(ApproachTagConstants.kDistanceP * distanceError,
            -ApproachTagConstants.kMaxOutput, ApproachTagConstants.kMaxOutput);
    // A tag to the right needs a clockwise turn, which is a negative rotation.
    double rotation = aligned
        ? 0.0
        : MathUtil.clamp(-ApproachTagConstants.kRotationPPerDegree * txDegrees,
            -ApproachTagConstants.kMaxOutput, ApproachTagConstants.kMaxOutput);
    return new Output(forward, rotation, atDistance && aligned);
  }
}
