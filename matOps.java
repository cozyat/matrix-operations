import static java.lang.System.*;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.io.*;

@SuppressWarnings("all")
public class matOps {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] arr = new int[2][2];
        for (int i = 0; i < arr.length; i++) {
            String[] line = input.nextLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }

        System.out.println("\n");
        System.out.println("Determinant: " + determinant(arr) + "\n");
        System.out.println("Trace: " + trace(arr) + "\n");
        System.out.println("Scalar: " + Arrays.deepToString(scalar(arr)) + "\n");
        System.out.println("Inverse Matrix: " + Arrays.deepToString(inverse(arr)) + "\n");
        System.out.println("Transposed Matrix: " + Arrays.deepToString(transpose(arr)) + "\n");
        System.out.println("System of Equations from Matrix: " + system(arr));
      
        input.close();
    }

    private static int determinant(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    private static int trace(int[][] matrix) {
        return matrix[0][0] + matrix[1][1];
    }

    private static double[][] scalar(int[][] matrix) {
        double[][] sclr = new double[2][2];
        Scanner mthdsclr = new Scanner(System.in);

        System.out.print("Enter a scalar: ");
        int scalar = mthdsclr.nextInt();

        for (int m = 0; m < sclr.length; m++) {
            for (int n = 0; n < sclr[m].length; n++) {
                sclr[m][n] = matrix[m][n] * scalar;
            }
        }
        return sclr;
    }

    private static double[][] inverse(int[][] matrix) {
        double[][] inv = new double[2][2];
        inv[0][0] = matrix[1][1];
        inv[0][1] = -matrix[0][1];
        inv[1][0] = -matrix[1][0];
        inv[1][1] = matrix[0][0];

        if (determinant(matrix) != 0) {
            for (int x = 0; x < inv.length; x++) {
                for (int y = 0; y < inv[x].length; y++) {
                    inv[x][y] *= 1.0 / determinant(matrix);
                    inv[x][y] = Math.round(inv[x][y] * 100.0) / 100.0;
                }
            }
            return inv;
        } else {
            return new double[][]{{0}};
        }
    }

    private static int[][] transpose(int[][] matrix) {
        int[][] tran = new int[2][2];
        for (int u = 0; u < 2; u++) {
            for (int v = 0; v < 2; v++) {
                tran[u][v] = matrix[v][u];
            }
        }
        return tran;
    }

    private static String system(int[][] matrix) {
        System.out.println(matrix[0][0] + "x" + " + " + matrix[0][1] + "y = ?");
        System.out.println(matrix[1][0] + "x" + " + " + matrix[1][1] + "y = ?");

        Scanner mthdsys = new Scanner(System.in);
        double[][] inv = inverse(matrix);
        double[] consts = new double[2];
        double[][] sys;

        System.out.println("\n" + "Enter first equation's constant: ");
        consts[0] = Double.parseDouble(mthdsys.nextLine());

        System.out.println("\n" + "Enter second equation's constant: ");
        consts[1] = Double.parseDouble(mthdsys.nextLine());

        mthdsys.close();

        sys = new double[][]{{inv[0][0] * consts[0] + inv[0][1] * consts[1]}, {inv[1][0] * consts[0] + inv[1][1] * consts[1]}};

        for (int a = 0; a < sys.length; a++) {
            for (int b = 0; b < sys[a].length; b++) {
                sys[a][b] = Math.round(sys[a][b] * 100.0) / 100.0;
            }
        }
        return "x = " + sys[0][0] + ", y = " + sys[1][0];
    }
}
