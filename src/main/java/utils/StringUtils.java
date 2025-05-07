package utils;

public class StringUtils {

    public static boolean isEmpty(String s) {
        return (s == null) || (s.replaceAll(" ", "").isEmpty());
    }
}
