import java.util.Arrays;
import java.util.function.Supplier;

public class Fmap {
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
        double[] tag1 = new double[] {-0.5, -0.866025, 0, 6.808597, 0.866025, -0.5, 0, -3.859403, 0, 0, 1, 1.355852, 0, 0, 0, 1};
        check("indice fila 0 columna 0", () -> index(0, 0), 0);
        check("indice fila 1 columna 0", () -> index(1, 0), 4);
        check("indice fila 2 columna 3", () -> index(2, 3), 11);
        check("traslacion del tag 1", () -> translation(tag1), new double[] {6.808597, -3.859403, 1.355852});
        check("tamano en metros", () -> sizeMeters(165.1), 0.1651);
        check("giro del tag 1", () -> yawDegrees(tag1), 120.0);
        System.out.println(passed + " de " + total + " pruebas pasaron");
    }

    static int index(int row, int col) {
        throw new UnsupportedOperationException();
    }

    static double[] translation(double[] transform) {
        throw new UnsupportedOperationException();
    }

    static double sizeMeters(double sizeMm) {
        throw new UnsupportedOperationException();
    }

    static double yawDegrees(double[] transform) {
        throw new UnsupportedOperationException();
    }
}
