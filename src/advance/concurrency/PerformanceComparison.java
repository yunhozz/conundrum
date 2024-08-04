package advance.concurrency;

import java.util.concurrent.atomic.AtomicLong;

public class PerformanceComparison {

    private static final int maxCnt = 1000;

    static class Blocking {
        private static final long startTime = System.currentTimeMillis();
        private static long count = 0;

        public static void main(String[] args) throws Exception {
            for (int i = 0; i < maxCnt; i++) {
                new Thread(Blocking::plus).start();
            }

            Thread.sleep(2000);
            System.out.println("count = " + count); // 1000
            System.out.println("maxCnt = " + maxCnt); // 1000
        }

        private static synchronized void plus() {
            if (++count == maxCnt) {
                System.out.println(System.currentTimeMillis() - startTime); // 1250 ~ 1350 ms
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignore) {}
        }

        private void plus2() {
            synchronized (this) {
                if (++count == maxCnt) {
                    System.out.println(System.currentTimeMillis() - startTime); // 30 ~ 50 ms
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignore) {}
        }
    }

    static class NonBlocking {
        private static final long startTime = System.currentTimeMillis();
        private static final AtomicLong count = new AtomicLong();

        public static void main(String[] args) throws Exception {
            for (int i = 0; i < maxCnt; i++) {
                new Thread(NonBlocking::plus).start();
            }

            Thread.sleep(2000);
            System.out.println("count = " + count); // 1000
            System.out.println("maxCnt = " + maxCnt); // 1000
        }

        private static void plus() {
            if (count.incrementAndGet() == maxCnt) {
                System.out.println(System.currentTimeMillis() - startTime); // 30 ~ 50 ms
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignore) {}
        }
    }
}
