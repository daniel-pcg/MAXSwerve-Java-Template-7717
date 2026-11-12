import java.util.Arrays;
import java.util.function.Supplier;

public class Localizacion {
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
        double[] botpose = {1.5, 2.0, 0.0, 0.0, 0.0, 90.0};
        check("botposeX", () -> botposeX(botpose), 1.5);
        check("botposeYawDegrees", () -> botposeYawDegrees(botpose), 90.0);
        check("distanceMeters", () -> distanceMeters(new double[] {1, 1, 0, 0, 0, 0}, new double[] {4, 5, 0, 0, 0, 0}), 5.0);
        check("rechaza si no hay tags", () -> shouldReject(0, 0.0, 0.0), true);
        check("rechaza un tag con ambiguedad alta", () -> shouldReject(1, 0.8, 1.0), true);
        check("rechaza un tag muy lejos", () -> shouldReject(1, 0.5, 4.0), true);
        check("acepta un tag cercano y claro", () -> shouldReject(1, 0.5, 2.0), false);
        check("acepta varios tags", () -> shouldReject(2, 0.9, 5.0), false);
        check("stdDevsMt1", () -> stdDevsMt1(), new double[] {0.5, 0.5, 9999999.0});
        System.out.println(passed + " de " + total + " pruebas pasaron");
    }

    static double botposeX(double[] botpose) {
        throw new UnsupportedOperationException();
    }

    static double botposeYawDegrees(double[] botpose) {
        throw new UnsupportedOperationException();
    }

    static double distanceMeters(double[] a, double[] b) {
        throw new UnsupportedOperationException();
    }

    static boolean shouldReject(int tagCount, double ambiguity, double distToCamera) {
        throw new UnsupportedOperationException();
    }

    static double[] stdDevsMt1() {
        throw new UnsupportedOperationException();
    }
}
