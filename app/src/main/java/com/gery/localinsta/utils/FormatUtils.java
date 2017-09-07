package com.gery.localinsta.utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by gguij002 on 6/21/17.
 */

public class FormatUtils {

    private static final String HTTP_PROTOCOL_PREFIX = "http://";
    private static final String HTTPS_PROTOCOL_PREFIX = "https://";

    private static final int MIN_PASSWORD_LENGTH = 6;

    /**
     * If a URL is passed without having http in front of it, it will be added by this method.
     * Else it returns the URL unmodified
     *
     * @param url The Url in question
     * @return The valid version of the URL or Null if the URL is Null
     */
    public static String addHttpProtocolToUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }

        if (url.startsWith(HTTP_PROTOCOL_PREFIX) || url.startsWith(HTTPS_PROTOCOL_PREFIX)) {
            return url;
        }

        url = HTTP_PROTOCOL_PREFIX + url;
        return url;
    }

    /**
     * @param email The email to validate
     * @return true if email is valid, or false if not valid.
     */
    public static boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * @param passwordText The password to validate
     * @return true if password is valid, false if not valid.
     */
    public static boolean isValidPassword(CharSequence passwordText) {
        return !TextUtils.isEmpty(passwordText) && passwordText.length() >= MIN_PASSWORD_LENGTH;
    }
}
