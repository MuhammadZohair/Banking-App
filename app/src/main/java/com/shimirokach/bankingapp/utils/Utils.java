package com.shimirokach.bankingapp.utils;

import android.content.Context;

import org.joda.time.DateTime;

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

    /**
     * Generate token string.
     *
     * @return the string
     */
    public static String generateToken() {
        String charString = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomAlpha = new StringBuilder();
        double randomRoll;
        String randomChar;
        for
        (double i = 0; i < 150; i++) {
            randomRoll = Math.random();
            randomChar = "";
            for
            (int j = 1; j <= 35; j++) {
                if
                (randomRoll <= (1.0 / 36.0 * j)) {
                    randomChar = Character.toString(charString.charAt(j - 1));
                    break;
                }
            }
            randomAlpha.append(randomChar);
        }
        return randomAlpha.toString();
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

        stringBuilder.append(DateTime.now().getDayOfMonth())
                .append("/")
                .append(DateTime.now().getMonthOfYear())
                .append("/")
                .append(DateTime.now().getYear());

        return stringBuilder.toString();
    }
}
