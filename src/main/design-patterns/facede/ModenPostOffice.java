package facede;

/**
 * Created by Administrator on 2016/10/5.
 */
public class ModenPostOffice {
    private ILetterProcess letterProcess = new LetterProcessImpl();
    private Police letterPolice = new Police();

    public void sendLetter(String context, String address) {
        letterProcess.writeContext(context);
        letterProcess.fillEnvelope(address);

        letterPolice.checkLetter(letterProcess);

        letterProcess.letterIntoEnvelope();
        letterProcess.sendLetter();
    }
}
