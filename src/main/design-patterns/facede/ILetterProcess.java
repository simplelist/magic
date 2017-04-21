package facede;

/**
 * Created by Administrator on 2016/10/5.
 */
public interface ILetterProcess {
    public void writeContext(String context);

    public void fillEnvelope(String address);

    public void letterIntoEnvelope();

    public void sendLetter();
}
