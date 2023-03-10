import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pack {
    private static final String name = "com.github.oshi:oshi-core:6.2.2";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(":\\D+\\d?+:"); //去掉空格符合换行符
        Matcher matcher = pattern.matcher(name);
        String d = matcher.replaceAll("");
        System.out.println(d);

        /*pattern = Pattern.compile(":\\d.+"); //去掉空格符合换行符
        matcher = pattern.matcher(name);
        result = matcher.replaceAll("");
        System.out.println(result);*/

        pattern = Pattern.compile("^\\D+.\\d?+\\D+\\d?+:"); //去掉空格符合换行符
        matcher = pattern.matcher(name);
        String result = matcher.replaceAll("");
        d = d.replaceAll(result, "");
        d = d.replaceAll("\\.", "\\\\\\");
        System.out.println("\\a");
    }

}
