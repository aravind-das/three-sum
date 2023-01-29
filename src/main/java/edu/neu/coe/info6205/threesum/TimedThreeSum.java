package edu.neu.coe.info6205.threesum;
import edu.neu.coe.info6205.util.Stopwatch;

public class TimedThreeSum {
    static final int MAX_RUNS = 5;
    static final int VALUE_RANGE = 1000000;
    static final int[] ARRAY_SIZES = {512, 1024, 2048, 4096, 8192, 16384};

    public static int[] generateArray(int n) {
        return new Source(n, VALUE_RANGE)
                .intsSupplier(10)
                .get();
    }

    public static long measureCubicApproach(int[] ints) {
        long ms = 0;

        try (Stopwatch sw = new Stopwatch()) {
            new ThreeSumCubic(ints).getTriples();
            ms = sw.lap();
        }

        return ms;
    }

    public static long measureQuadrithmicApproach(int[] ints) {
        long ms = 0;

        try (Stopwatch sw = new Stopwatch()) {
            new ThreeSumQuadrithmic(ints).getTriples();
            ms = sw.lap();
        }

        return ms;
    }

    public static long measureQuadraticApproach(int[] ints) {
        long ms = 0;

        try (Stopwatch sw = new Stopwatch()) {
            new ThreeSumQuadratic(ints).getTriples();
            ms = sw.lap();
        }

        return ms;
    }

    public static long measureQuadraticCaliperApproach(int[] ints) {
        long ms = 0;

        try (Stopwatch sw = new Stopwatch()) {
            new ThreeSumQuadraticWithCalipers(ints).getTriples();
            ms = sw.lap();
        }

        return ms;
    }

    public static void main(String[] args) {
        for (int size: ARRAY_SIZES) {
            long avgCubic = 0, avgQuadrithmic = 0, avgQuadratic = 0, avgQuadraticCaliper = 0;

            for (int i = 0; i < MAX_RUNS; i++) {
                int[] testArray = generateArray(size);

                if (size <= 4096) {
                    avgCubic += measureCubicApproach(testArray);
                }

                avgQuadrithmic += measureQuadrithmicApproach(testArray);
                avgQuadratic += measureQuadraticApproach(testArray);
                avgQuadraticCaliper += measureQuadraticCaliperApproach(testArray);
            }
            if (size <= 4096) {
                System.out.println("Cubic algorithm for array size " + size + ": " + avgCubic / MAX_RUNS + " ms");
            }

            System.out.println("Quadrithmic algorithm for array size " + size + ": " + avgQuadrithmic / MAX_RUNS + " ms");
            System.out.println("Quadratic algorithm for array size " + size + ": " + avgQuadratic / MAX_RUNS + " ms");
            System.out.println("Quadratic with caliper algorithm for array size " + size + ": " + avgQuadraticCaliper / MAX_RUNS + " ms");
            System.out.println();
        }
    }
}
