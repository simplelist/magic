package hutool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by jackshi on 16/9/21.
 */
public class Test {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        while (true) {
            System.out.println("请输入你的大名:");
            Scanner scanner=new Scanner(System.in);
            String name=scanner.next();
            if (name.equals("bye")) break;
            sb.append("\n\n------" + name + "--------\n");
            Map<Integer, List> result = new HashMap<Integer, List>();


            List<Integer> list = new ArrayList<Integer>();
            for (int j = 1; j <= 33; j++) {
                list.add(j);
            }
            Collections.shuffle(list);
            Collections.shuffle(list);
            Collections.shuffle(list);
            Random random = new Random();
            Set lastSet = new HashSet(list.subList(29, 32));
            list.removeAll(lastSet);
            while (lastSet.size() < 6) {
                lastSet.add(list.get(random.nextInt(list.size())));
            }
            list.addAll(lastSet);
            for (int i = 0; i < list.size(); i += 6) {
                List<Integer> sub = list.subList(i, i + 6);
                result.put(result.size() + 1, sub);
            }

            for (Integer key : result.keySet()) {
                List temp = result.get(key);
                Collections.sort(temp);
                sb.append(temp + "\n");
            }
            System.err.println("下一个............");
        }
        writeStringToFile2("a.txt", sb, true);
    }

    public static void writeStringToFile2(String filePath, StringBuffer content, boolean over) {
        try {
            FileWriter fw = new FileWriter(filePath, over);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content.toString());
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
