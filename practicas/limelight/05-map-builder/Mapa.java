import java.util.Arrays;
import java.util.function.Supplier;

public class Mapa {
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
        check("distancia 3D entre tags", () -> distance3d(new double[] {0, 0, 0}, new double[] {3, 4, 0}), 5.0);
        check("distancia 3D con altura", () -> distance3d(new double[] {1, 1, 1}, new double[] {1, 1, 4}), 3.0);
        check("punto medio", () -> midpoint(new double[] {0, 2, 4}, new double[] {2, 4, 8}), new double[] {1, 3, 6});
        check("punto medio igual", () -> midpoint(new double[] {5, 5, 5}, new double[] {5, 5, 5}), new double[] {5, 5, 5});
        System.out.println(passed + " de " + total + " pruebas pasaron");
    }

    static double distance3d(double[] a, double[] b) {
        throw new UnsupportedOperationException();
    }

    static double[] midpoint(double[] a, double[] b) {
        throw new UnsupportedOperationException();
    }
}
