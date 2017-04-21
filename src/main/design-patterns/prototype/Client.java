package prototype;

import org.junit.Test;

import java.util.Random;

/**
 * Created by simplelist on 2016/9/19.
 */
public class Client {
    private static int MAX_COUNT = 6;

    @Test
    public void testMail() {
        int i = 0;
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("XX银行版权所有");
        while (i < MAX_COUNT) {
            //以下是每封邮件不同的地方
            Mail cloneMail = mail.clone();//克隆方法,该对象的构造函数不会被调用
            cloneMail.setAppellation(getRandString(5) + " 先生/女士");
            cloneMail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
            sendMail(cloneMail);
            i++;
        }
    }

    private String getRandString(int legth) {
        String source = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < legth; i++) {
            sb.append(source.charAt(rand.nextInt(source.length())));
        }
        return sb.toString();
    }

    private void sendMail(Mail mail) {
        System.out.println("标题: " + mail.getSubject() + " \t收件人:" + mail.getReceiver() + "\t...发送成功");
    }


}
