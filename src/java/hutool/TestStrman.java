package hutool;

import org.junit.Test;
import strman.Strman;

import java.util.Arrays;

/**
 * Created by simplelist on 2016/9/3.
 */
public class TestStrman {
    @Test
    public void testMan() {
        // append 在一个字符串后追加任意个数的字符串
        String s1 = Strman.append("f", "o", "o", "b", "a", "r");
        System.out.println("append:" + s1); // result => "foobar"

        // prepend 在一个字符串前追加任意个数的字符串
        String s1pre = Strman.prepend("r", "f", "o", "o", "b", "a");
        System.out.println("prepend:" + s1pre); // result => "foobar"

        // appendArray 在一个字符串后先后追加一个字符串数组中的元素
        String s2 = Strman.appendArray("f", new String[]{"o", "o", "b", "a", "r"});
        System.out.println("append:" + s2); //  result => "foobar"

        // at 根据字符串的索引获取到对应的字符。如果索引是负数,则逆向获取,超出则抛出异常
//        Optional<String> s3 = Strman.at("foobar", 3);
//        System.out.println("at:" + s3.get()); // result => "b"

        // between 得到一个字符串中,开始字符串和结束字符串之间的字符串的数组
        String[] s4 = Strman.between("[abc], [def]", "c", "]");
        System.out.println("between:" + Arrays.toString(s4)); // result => "[abc, def]"

        // chars 得到一个字符串中所有字符构成的字符串数组
        String[] s5 = Strman.chars("title");
        System.out.println("chars:" + Arrays.toString(s5)); // result => "[t, i, t, l, e]"

        // collapseWhitespace 替换掉连续的多个空格为一个空格
        String s6 = Strman.collapseWhitespace("foo    bar");
        System.out.println("chars:" + s6); // result => "foo bar"

        // contains 判断一个字符串是否包含另外一个字符串,第三个参数,表示字符串大小写是否敏感
        boolean s7 = Strman.contains("foo bar", "foo");
        boolean s8 = Strman.contains("foo bar", "FOO", false);
        System.out.println("contains:" + s7 + ", " + s8); // result => "true, true"

        // containsAll 判断一个字符串是否包含某字符串数组中的所有元素
        boolean s9 = Strman.containsAll("foo bar", new String[]{"foo", "bar"});
        boolean s10 = Strman.containsAll("foo bar", new String[]{"FOO", "bar"}, false);
        System.out.println("containsAll:" + s9 + ", " + s10); // result => "true, true"

        // containsAny 判断一个字符串是否包含某字符串数组中的任意一个元素
        boolean s11 = Strman.containsAny("foo bar", new String[]{"FOO", "BAR", "Test"}, false);
        System.out.println("containsAny:" + s11); // result => "true"

        // countSubstr 判断一个字符串包含某字符串的个数
        long s12 = Strman.countSubstr("aaaAAAaaa", "aaa");
        long s13 = Strman.countSubstr("aaaAAAaaa", "aaa", false, false);
        System.out.println("countSubstr:" + s12 + ", " + s13); // result => "2, 3"

        // endsWith 判断一个字符串是否以某个字符串结尾
        boolean s14 = Strman.endsWith("foo bar", "bar");
        boolean s15 = Strman.endsWith("foo bar", "BAR", false);
        System.out.println("endsWith:" + s14 + ", " + s15); // result => "true, true"

        // ensureLeft 确保一个字符串以某个字符串开头,如果不是,则在前面追加该字符串,并将字符串结果返回
        String s16 = Strman.ensureLeft("foobar", "foo");
        String s17 = Strman.ensureLeft("bar", "foo");
        String s18 = Strman.ensureLeft("foobar", "FOO", false);
        System.out.println("ensureLeft:" + s16 + ", " + s17 + ", " + s18);
        // result => "foobar, foobar, foobar"

        // ensureRight 确保一个字符串以某个字符串结尾,如果不是,则在后面追加该字符串,并将字符串结果返回
        String s16r = Strman.ensureRight("foobar", "bar");
        String s17r = Strman.ensureRight("foo", "bar");
        String s18r = Strman.ensureRight("fooBAR", "bar", false);
        System.out.println("ensureRight:" + s16r + ", " + s17r + ", " + s18r);
        // result => "foobar, foobar, fooBAR"

        // base64Encode 将字符串转成Base64编码的字符串
        String s19 = Strman.base64Encode("strman");
        System.out.println("base64Encode:" + s19); // result => "c3RybWFu"

        // binDecode 将二进制编码（16位）转成字符串字符
        String s20 = Strman.binDecode("0000000001000001");
        System.out.println("binDecode:" + s20); // result => "A"

        // binEncode 将字符串字符转成二进制编码（16位）
        String s21 = Strman.binEncode("A");
        System.out.println("binEncode:" + s21); // result => "0000000001000001"

        // decDecode 将十进制编码（5位）转成字符串字符
        String s22 = Strman.decDecode("00065");
        System.out.println("decDecode:" + s22); // result => "A"

        // decEncode 将字符串转成十进制编码（5位）
        String s23 = Strman.decEncode("A");
        System.out.println("decEncode:" + s23); // result => "00065"

        // first 得到从字符串开始到索引n的字符串
        String s24 = Strman.first("foobar", 3);
        System.out.println("first:" + s24); // result => "foo"

        // last 得到从字符串结尾倒数索引n的字符串
        String s24l = Strman.last("foobar", 3);
        System.out.println("last:" + s24l); // result => "bar"

        // head 得到字符串的第一个字符
        String s25 = Strman.head("foobar");
        System.out.println("head:" + s25); // result => "f"

        // hexDecode 将字符串字符转成十六进制编码（4位）
        String s26 = Strman.hexDecode("0041");
        System.out.println("hexDecode:" + s26); // result => "A"

        // hexEncode 将十六进制编码（4位）转成字符串字符
        String s27 = Strman.hexEncode("A");
        System.out.println("hexEncode:" + s27); // result => "0041"

        // inequal 测试两个字符串是否相等
        boolean s28 = Strman.unequal("a", "b");
        System.out.println("unequal:" + s28); // result => "true"

        // insert 将子字符串插入到字符串某索引位置处
        String s29 = Strman.insert("fbar", "oo", 1);
        System.out.println("insert:" + s29); // result => "foobar"

        // leftPad 将字符串从左补齐直到总长度为n为止
        String s30 = Strman.leftPad("1", "0", 5);
        System.out.println("leftPad:" + s30); // result => "00001"

        // rightPad 将字符串从右补齐直到总长度为n为止
        String s30r = Strman.rightPad("1", "0", 5);
        System.out.println("rightPad:" + s30r); // result => "10000"

        // lastIndexOf 此方法返回在指定值的最后一个发生的调用字符串对象中的索引，从偏移量中向后搜索
        int s31 = Strman.lastIndexOf("foobarfoobar", "F", false);
        System.out.println("lastIndexOf:" + s31); // result => "6"

        // leftTrim 移除字符串最左边的所有空格
        String s32 = Strman.leftTrim("   strman   ");
        System.out.println("leftTrim:" + s32); // result => "strman   "

        // rightTrim 移除字符串最右边的所有空格
        String s32r = Strman.rightTrim("   strman   ");
        System.out.println("rightTrim:" + s32r); // result => "   strman"

        // removeEmptyStrings 移除字符串数组中的空字符串
        String[] s33 = Strman.removeEmptyStrings(new String[]{"aa", "", "   ", "bb", "cc", null});
        System.out.println("removeEmptyStrings:" + Arrays.toString(s33));
        // result => "[aa, bb, cc]"

        // removeLeft 得到去掉前缀（如果存在的话）后的新字符串
        String s34 = Strman.removeLeft("foobar", "foo");
        System.out.println("removeLeft:" + s34); // result => "bar"

        // removeRight 得到去掉后缀（如果存在的话）后的新字符串
        String s34r = Strman.removeRight("foobar", "bar");
        System.out.println("removeRight:" + s34r); // result => "foo"

        // removeNonWords 得到去掉不是字符的字符串
        String s35 = Strman.removeNonWords("foo&bar-");
        System.out.println("removeNonWords:" + s35); // result => "foobar"

        // removeSpaces 移除所有空格
        String s36 = Strman.removeSpaces("   str  man   ");
        System.out.println("removeSpaces:" + s36); // result => "   strman"

        // repeat 得到给定字符串和重复次数的新字符串
        String s37 = Strman.repeat("1", 3);
        System.out.println("repeat:" + s37); // result => "111"

        // reverse 得到反转后的字符串
        String s38 = Strman.reverse("foobar");
        System.out.println("reverse:" + s38); // result => "raboof"

        // safeTruncate 安全的截断字符串，不切一个字的一半,它总是返回最后一个完整的单词
        String s39 = Strman.safeTruncate("A Javascript string manipulation library.", 19, "...");
        System.out.println("safeTruncate:" + s39); // result => "A Javascript..."

        // truncate 不太安全的截断字符串
        String s40 = Strman.truncate("A Javascript string manipulation library.", 19, "...");
        System.out.println("truncate:" + s40); // result => "A Javascript str..."

        // htmlDecode 将html字符反转义
        String s41 = Strman.htmlDecode("&SHcy;");
        System.out.println("htmlDecode:" + s41); // result => "Ш"

        // htmlEncode 将html字符转义
        String s42 = Strman.htmlEncode("Ш");
        System.out.println("htmlEncode:" + s42); // result => "&SHcy;"

        // shuffle 将给定字符串转成随机字符顺序的字符串
        String s43 = Strman.shuffle("shekhar");
        System.out.println("shuffle:" + s43); // result => "rhsheak"

        // slugify 将字符串分段(用"-"分段)
        String s44 = Strman.slugify("foo bar");
        System.out.println("slugify:" + s44); // result => "foo-bar"

        // transliterate 删除所有非有效字符,如:á => a
        String s45 = Strman.transliterate("fóõ bár");
        System.out.println("transliterate:" + s45); // result => "foo bar"

        // surround 给定的“前缀”和“后缀”来包裹一个字符串
        String s46 = Strman.surround("div", "<", ">");
        System.out.println("surround:" + s46); // result => "<div>"

        // tail 得到去掉第一个字符后的字符串
        String s47 = Strman.tail("foobar");
        System.out.println("tail:" + s47); // result => "oobar"

        // toCamelCase 转成驼峰式的字符串
        String s48 = Strman.toCamelCase("Camel Case");
        String s48_2 = Strman.toCamelCase("camel-case");
        System.out.println("tail:" + s48 + ", " + s48_2); // result => "camelCase, camelCase"

        // toStudlyCase 转成Studly式的字符串
        String s49 = Strman.toStudlyCase("hello world");
        System.out.println("toStudlyCase:" + s49); // result => "HelloWorld"

        // toDecamelize 转成Decamelize式的字符串
        String s50 = Strman.toDecamelize("helloWorldQu", null);
        System.out.println("toDecamelize:" + s50); // result => "hello world"

        // toKebabCase 转成Kebab式的字符串
        String s51 = Strman.toKebabCase("hello World");
        System.out.println("toKebabCase:" + s51); // result => "hello-world"

        // toSnakeCase 转成Snake式的字符串
        String s52 = Strman.toSnakeCase("hello world");
        System.out.println("toSnakeCase:" + s52); // result => "hello_world"
    }

}
