package com.sofis.generico.utils.generalutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN_REGEX
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN_REGEX);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    /**
     *
     * @param hex
     */
    public static boolean validateEmail(String hex) {
        Matcher matcher = Pattern.compile(EMAIL_PATTERN_REGEX).matcher(hex);
        Boolean ret = matcher.matches();
        return ret;
    }
}
