package info3.parcial2.utils;

import java.util.regex.Pattern;

public class Validator {
    public static boolean isAValidEmailAddress(String input) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }
}
