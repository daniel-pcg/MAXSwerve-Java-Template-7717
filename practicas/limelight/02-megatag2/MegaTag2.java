import java.util.Arrays;
import java.util.function.Supplier;

public class MegaTag2 {
    static int passed = 0;
    static int total = 0;

    static void check(String name, Supplier<Object> actual, Object expected) {
        total++;
        Object value;
        try {
            value = actual.get();
        } catch (RuntimeException e) {
            System.out.println("FAIL " + name + " -> sin implementar");
            return;
        }
        boolean ok;
        if (expected instanceof double[] && value instanceof double[]) {
            double[] a = (double[]) expected;
            double[] b = (double[]) value;
            ok = a.length == b.length;
            for (int i = 0; ok && i < a.length; i++) {
                ok = Math.abs(a[i] - b[i]) < 1e-3;
            }
        } else if (expected instanceof Double && value instanceof Double) {
            ok = Math.abs((Double) expected - (Double) value) < 1e-3;
        } else {
            ok = expected.equals(value);
        }
        if (ok) {
            passed++;
        }
        String shown = value instanceof double[] ? Arrays.toString((double[]) value) : String.valueOf(value);
        System.out.println((ok ? "PASS " : "FAIL ") + name + " -> " + shown + (ok ? "" : "  (esperado " + (expected instanceof double[] ? Arrays.toString((double[]) expected) : expected) + ")"));
    }

    public static void main(String[] args) {
        check("MT2 necesita yaw", () -> needsRobotYaw("MT2"), true);
        check("MT1 no necesita yaw", () -> needsRobotYaw("MT1"), false);
        check("gira normal", () -> shouldRejectMt2(100.0, 2), false);
        check("gira muy rapido", () -> shouldRejectMt2(-400.0, 2), true);
        check("sin tags", () -> shouldRejectMt2(0.0, 0), true);
        check("justo en el limite", () -> shouldRejectMt2(360.0, 1), false);
        check("apenas sobre el limite", () -> shouldRejectMt2(361.0, 1), true);
        check("stdDevsMt2", () -> stdDevsMt2(), new double[] {0.7, 0.7, 9999999.0});
        check("esperando el autonomo", () -> imuMode(true, false), 1);
        check("habilitado o girando", () -> imuMode(false, true), 2);
        check("habilitado tiene prioridad", () -> imuMode(true, true), 2);
        check("sin nada de lo anterior", () -> imuMode(false, false), 0);
        System.out.println(passed + " de " + total + " pruebas pasaron");
    }

    static boolean needsRobotYaw(String algorithm) {
        throw new UnsupportedOperationException();
    }

    static boolean shouldRejectMt2(double gyroRateDegPerSec, int tagCount) {
        throw new UnsupportedOperationException();
    }

    static double[] stdDevsMt2() {
        throw new UnsupportedOperationException();
    }

    static int imuMode(boolean waitingForAuto, boolean enabledOrTurning) {
        throw new UnsupportedOperationException();
    }
}
