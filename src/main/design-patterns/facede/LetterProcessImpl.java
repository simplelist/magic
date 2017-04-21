package facede;

/**
 * Created by Administrator on 2016/10/5.
 */
public class LetterProcessImpl implements ILetterProcess {
    public void writeContext(String context) {
        System.out.println("开始写信,内容是:" + context);
    }

    public void fillEnvelope(String address) {
        System.out.println("填写地址:" + address);
    }

    public void letterIntoEnvelope() {
        System.out.println("把信放到信封哩");
    }

    public void sendLetter() {
        System.out.println("咻咻咻,发信");
    }
}
