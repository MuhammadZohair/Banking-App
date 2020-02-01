package com.shimirokach.bankingapp.utils;

import android.content.Context;

import org.joda.time.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * The constant DEBIT.
     */
    public static final int DEBIT = 0;
    /**
     * The constant CREDIT.
     */
    public static final int CREDIT = 1;

    /**
     * The constant INSERT.
     */
    public static final int INSERT = 22;
    /**
     * The constant UPDATE.
     */
    public static final int UPDATE = 33;
    /**
     * The constant DELETE.
     */
    public static final int DELETE = 44;
    /**
     * The constant DELETE_ALL.
     */
    public static final int DELETE_ALL = 55;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{6,}$", Pattern.CASE_INSENSITIVE);

    /**
     * Validate email boolean.
     *
     * @param emailStr the email str
     * @return the boolean
     */
    public static boolean validateEmail(CharSequence emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    /**
     * Validate password boolean.
     *
     * @param passwordStr the password str
     * @return the boolean
     */
    public static boolean validatePassword(CharSequence passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.find();
    }

    /**
     * Generate token string.
     *
     * @return the string
     */
    public static String generateToken() {
        String charstring = "abcdefghijklmnopqrstuvwxyz0123456789";
        String randalphanum = "";
        double randroll;
        String randchar;
        for
        (double i = 0; i < 150; i++) {
            randroll = Math.random();
            randchar = "";
            for
            (int j = 1; j <= 35; j++) {
                if
                (randroll <= (1.0 / 36.0 * j)) {
                    randchar = Character.toString(charstring.charAt(j - 1));
                    break;
                }
            }
            randalphanum += randchar;
        }
        return randalphanum;
    }

    /**
     * Success.
     *
     * @param context the context
     * @param message the message
     */
    public static void success(Context context, String message) {
        Toasty.success(context, message, Toasty.LENGTH_SHORT, true).show();
    }

    /**
     * Error.
     *
     * @param context the context
     * @param message the message
     */
    public static void error(Context context, String message) {
        Toasty.error(context, message, Toasty.LENGTH_SHORT, true).show();
    }

    /**
     * Info.
     *
     * @param context the context
     * @param message the message
     */
    public static void info(Context context, String message) {
        Toasty.info(context, message, Toasty.LENGTH_SHORT, true).show();
    }

    /**
     * Warning.
     *
     * @param context the context
     * @param message the message
     */
    public static void warning(Context context, String message) {
        Toasty.warning(context, message, Toasty.LENGTH_SHORT, true).show();
    }

    /**
     * Gets current date.
     *
     * @return the current date
     */
    public static String getCurrentDate() {
        StringBuilder stringBuilder = new StringBuilder();
        switch (DateTime.now().getDayOfWeek()) {
            case 1:
                stringBuilder.append("Monday,  ");
                break;
            case 2:
                stringBuilder.append("Tuesday,  ");
                break;
            case 3:
                stringBuilder.append("Wednesday,  ");
                break;
            case 4:
                stringBuilder.append("Thursday,  ");
                break;
            case 5:
                stringBuilder.append("Friday,  ");
                break;
            case 6:
                stringBuilder.append("Saturday,  ");
                break;
            case 7:
                stringBuilder.append("Sunday,  ");
                break;
        }

        stringBuilder.append(DateTime.now().getDayOfMonth() + "/" +
                DateTime.now().getMonthOfYear() + "/" +
                DateTime.now().getYear());

        return stringBuilder.toString();
    }
}
