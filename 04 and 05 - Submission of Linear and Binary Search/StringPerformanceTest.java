package Day15;
public class StringPerformanceTest {
    public static void main(String[] args) {
        int iterations = 1_000_000;
        long startBuilder = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Orange Juice");
        }
        long endBuilder = System.nanoTime();
        System.out.println("StringBuilder time (ms): " + (endBuilder -
                startBuilder) / 1_000_000);
        long startBuffer = System.nanoTime();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sf.append("Orange Juice");
        }
        long endBuffer = System.nanoTime();
        System.out.println("StringBuffer time (ms): " + (endBuffer -
                startBuffer) / 1_000_000);
    }
}