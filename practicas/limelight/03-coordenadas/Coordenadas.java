import java.util.Arrays;
import java.util.function.Supplier;

public class Coordenadas {
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
        check("punto delante de la camara", () -> cameraPointToRobot(new double[] {0, 0, 2}, 0.3, 0.0, 0.5), new double[] {2.3, 0.0, 0.5});
        check("punto a la derecha de la camara", () -> cameraPointToRobot(new double[] {0.4, 0, 2}, 0.3, 0.1, 0.5), new double[] {2.3, 0.5, 0.5});
        check("punto sobre el eje de la camara", () -> cameraPointToRobot(new double[] {0, -0.2, 2}, 0.3, 0.0, 0.5), new double[] {2.3, 0.0, 0.7});
        check("error de 10 cm hacia adelante", () -> offsetErrorMeters(new double[] {2.3, 0, 0.5}, new double[] {2.4, 0, 0.5}), 0.1);
        check("distancia 3D", () -> offsetErrorMeters(new double[] {0, 0, 0}, new double[] {2, 3, 6}), 7.0);
        check("WPIBlue se recomienda", () -> isRecommendedForFrc("WPIBlue"), true);
        check("WPIRed no se recomienda", () -> isRecommendedForFrc("WPIRed"), false);
        check("FRC con origen al centro no es el preferido", () -> isRecommendedForFrc("FRC"), false);
        System.out.println(passed + " de " + total + " pruebas pasaron");
    }

    static double[] cameraPointToRobot(double[] camera, double forward, double right, double up) {
        throw new UnsupportedOperationException();
    }

    static double offsetErrorMeters(double[] a, double[] b) {
        throw new UnsupportedOperationException();
    }

    static boolean isRecommendedForFrc(String system) {
        throw new UnsupportedOperationException();
    }
}
