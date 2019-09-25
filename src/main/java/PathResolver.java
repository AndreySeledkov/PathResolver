import java.io.*;
import java.util.Scanner;

/**
 * Created by Andrey on 25.09.2019.
 */
public class PathResolver {

    public static void main(String[] args) {
        new PathResolver().resolve();
    }

    public void resolve() {
        String inResources = "src/main/resources/input.txt";
        String outResources = "src/main/resources/output.txt";

        int inArr[][] = readInDataArr(inResources);

        int result = findBestPath(inArr, inArr.length, inArr[0].length);

        writeResult(result, outResources);
    }


    private int[][] readInDataArr(String resources) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(resources));

            if (scanner.hasNext()) {
                int m = scanner.nextInt();
                int n = scanner.nextInt();

                if (m <= 0 || n <= 0) {
                    throw new IllegalArgumentException("M rows and N columns required");
                }

                int[][] inArr = new int[m][n];

                for (int i = 0; i < m; i++) {
                    if (scanner.hasNext()) {
                        for (int j = 0; j < n; j++) {
                            if (scanner.hasNextInt()) {
                                inArr[i][j] = scanner.nextInt();
                            }
                        }
                    }
                }
                return inArr;
            } else {
                throw new IllegalArgumentException("M rows and N columns required");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return null;
    }

    private int findBestPath(int[][] inArr, int m, int n) {
        int[][] outArr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int candidate1 = (i - 1) >= 0 ? outArr[i - 1][j] + inArr[i][j] : inArr[i][j];
                int candidate2 = (j - 1) >= 0 ? outArr[i][j - 1] + inArr[i][j] : inArr[i][j];
                outArr[i][j] = Math.max(candidate1, candidate2);
            }
        }
        return outArr[m - 1][n - 1];
    }

    private boolean writeResult(int result, String resource) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(resource));
            writer.write(String.valueOf(result));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
