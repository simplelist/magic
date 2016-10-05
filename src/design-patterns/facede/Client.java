package facede;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Client {
    @Test
    public void test1() {
        ILetterProcess letterProcess = new LetterProcessImpl();

        letterProcess.writeContext("不能告诉你");

        letterProcess.fillEnvelope("我要寄送到以下地址.........");

        letterProcess.letterIntoEnvelope();

        letterProcess.sendLetter();
    }

    @Test
    public void test2() {
        ModenPostOffice postOffice = new ModenPostOffice();

        postOffice.sendLetter("信件的内容是...........", "这里哩这里");
    }
}
