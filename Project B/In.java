import java.util.*;

public class In {
    private static Scanner In = new Scanner(System.in);

    public static String nextLine() {
        return In.nextLine();
    }

    public static char nextChar() {
        return In.nextLine().charAt(0);
    }

    public static char nextUpperChar() {
        return In.nextLine().toUpperCase().charAt(0);
    }

    public static int nextInt() {
        int i = In.nextInt();
        In.nextLine();
        return i;
    }

    public static double nextDouble() {
        double d = In.nextDouble();
        In.nextLine();
        return d;
    }
}
