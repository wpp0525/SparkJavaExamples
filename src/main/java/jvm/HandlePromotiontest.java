package jvm;

/**
 * VM参数：-Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 -XX:-HandlePromotionFailure  -XX:+PrintGCDetails
 */

public class HandlePromotiontest {
    private static final int _1MB = 1024 * 1024;
    @SuppressWarnings("unused")
    public static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];//Minor GC
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];//GC
    }

    public static void main(String[] args) {
        testHandlePromotion();
    }

}