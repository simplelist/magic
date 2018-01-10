package currency;

import org.junit.Test;

public class Test1 {
    @Test
    public void testAvaiableCpu() {
        System.out.println(availableCPU());
    }

    /**
     * 获取可用 cpu 数量
     *
     * @return
     */
    private int availableCPU() {
        return Runtime.getRuntime().availableProcessors();
    }


}
