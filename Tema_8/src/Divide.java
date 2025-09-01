import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class Divide {
    static Stack<Short> a = new Stack<>(),
            b = new Stack<>(),
            c = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        short numDiscos = 0;

        while (numDiscos <= 0) {
            System.out.print("Ingrese el número de discos: ");
            try {
                numDiscos = scanner.nextShort();
                if (numDiscos <= 0) {
                    System.out.println("Por favor, ingrese un número mayor que 0.");
                } else if (numDiscos > 20) {
                    System.out.println("¡Advertencia! Con " + numDiscos + " discos, la solución tomará " +
                            ((long)Math.pow(2, numDiscos) - 1) + " movimientos.");
                    System.out.print("¿Desea continuar? (s/n): ");
                    String respuesta = scanner.next();
                    if (!respuesta.equalsIgnoreCase("s")) {
                        numDiscos = 0;
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }

        for (short i = numDiscos; i > 0; i--) {
            a.push(i);
        }

        System.out.println("\nEstado inicial con " + numDiscos + " discos:");
        System.out.print("Torre A: "); a.show();
        System.out.print("Torre B: "); b.show();
        System.out.print("Torre C: "); c.show();
        System.out.println("-----------------------------");

        long startTime = System.currentTimeMillis();
        hanoi(a, b, c, numDiscos);
        long endTime = System.currentTimeMillis();

        System.out.println("¡Resuelto en " + (endTime - startTime) + " ms!");

        scanner.close();
    }

    /**
     * @param a Torre A
     * @param b Torre B
     * @param c Torre C
     * @param n número de elementos
     */
    public static void hanoi(Stack a, Stack b, Stack c, short n) {
        if (n == 1) {
            move(a, b);
            show();
        } else {
            hanoi(a, c, b, (short)(n - 1));
            move(a, b);
            show();
            hanoi(c, b, a, (short)(n - 1));
        }
    }

    /**
     * Mover el disco del tope de A hacia B
     * @param a
     * @param b
     */
    public static void move(Stack a, Stack b) {
        try {
            b.push(a.pop());
        } catch (Exception ex) {
            Logger.getLogger(Divide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostrar el estado actual de las torres
     */
    public static void show() {
        System.out.print("Torre A: "); a.show();
        System.out.print("Torre B: "); b.show();
        System.out.print("Torre C: "); c.show();
        System.out.println("-----------------------------");
    }
}