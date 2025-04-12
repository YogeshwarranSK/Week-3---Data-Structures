package Day16;
public class StringConcatComparison {
    private static final int N = 1_000_000;
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) runAllBenchmarks(10_000);
        runAllBenchmarks(N);
    }
    private static void runAllBenchmarks(int iterations) {
        benchmarkString(iterations);
        benchmarkStringBuilder(iterations);
        benchmarkStringBuffer(iterations);
        System.out.println();
    }
    private static void benchmarkString(int iterations) {
        long start = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < iterations; i++) result += "a";
        long end = System.currentTimeMillis();
        System.out.println("String (O(N^2))      : " + (end - start) + " ms");
    }
    private static void benchmarkStringBuilder(int iterations) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) sb.append("a");
        long end = System.currentTimeMillis();
        System.out.println("StringBuilder (O(N)) : " + (end - start) + " ms");
    }
    private static void benchmarkStringBuffer(int iterations) {
        long start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) sbf.append("a");
        long end = System.currentTimeMillis();
        System.out.println("StringBuffer (O(N))  : " + (end - start) + " ms");
    }
}
