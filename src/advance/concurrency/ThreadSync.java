package advance.concurrency;

public class ThreadSync {

    private static long count = 0;

    static class DelayTest_1 {
        public static void main(String[] args) throws Exception {
            int maxCnt = 1000;
            for (int i = 0; i < maxCnt; i++) {
                new Thread(DelayTest_1::plusWithDelay).start();
            }

            Thread.sleep(1000);
            System.out.println("count = " + count); // 1000
            System.out.println("maxCnt = " + maxCnt); // 1000
        }

        private static void plusWithDelay() {
            count++;
            try {
                Thread.sleep(1); // complex logic
            } catch (InterruptedException ignore) {}
        }
    }

    static class DelayTest_2 {
        public static void main(String[] args) throws Exception {
            int maxCnt = 1000;
            for (int i = 0; i < maxCnt; i++) {
                new Thread(DelayTest_2::plusWithDelay).start();
            }

            Thread.sleep(1000);
            System.out.println("count = " + count); // 800 ~ 820
            System.out.println("maxCnt = " + maxCnt); // 1000
        }

        private static synchronized void plusWithDelay() {
            count++;
            try {
                Thread.sleep(1); // complex logic
            } catch (InterruptedException ignore) {}
        }
    }
}
