package advance.concurrency;

public class ThreadNotSafe {

    private static long count = 0;

    static class Test_1 {
        public static void main(String[] args) throws Exception {
            int maxCnt = 10;
            for (int i = 0; i < maxCnt; i++) {
                new Thread(Test_1::plusWithoutSync).start();
            }

            Thread.sleep(100);
            System.out.println("count = " + count); // 9 or 10
            System.out.println("maxCnt = " + maxCnt); // 10
        }

        private static void plusWithoutSync() {
            count++;
        }
    }

    static class Test_2 {
        public static void main(String[] args) throws Exception {
            int maxCnt = 10;
            for (int i = 0; i < maxCnt; i++) {
                new Thread(Test_2::plusWithSync).start();
            }

            Thread.sleep(100);
            System.out.println("count = " + count); // 10
            System.out.println("maxCnt = " + maxCnt); // 10
        }

        private static synchronized void plusWithSync() {
            count++;
        }
    }
}
