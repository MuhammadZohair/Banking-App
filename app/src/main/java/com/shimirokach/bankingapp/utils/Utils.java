package com.shimirokach.bankingapp.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class Utils {

    public static final int DEBIT = 0;
    public static final int CREDIT = 1;

    public static final int INSERT = 22;
    public static final int UPDATE = 33;
    public static final int DELETE = 44;
    public static final int DELETE_ALL = 55;

    public static final int LOGIN = 111;


    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{6,}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(CharSequence emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePassword(CharSequence passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.find();
    }

    public static String generateToken()
    {
        String charstring = "abcdefghijklmnopqrstuvwxyz0123456789";
        String randalphanum = "";
        double randroll;
        String randchar;
        for
        (double i = 0; i < 150; i++)
        {
            randroll = Math.random();
            randchar = "";
            for
            (int j = 1; j <= 35; j++)
            {
                if
                (randroll <= (1.0 / 36.0 * j))
                {
                    randchar = Character.toString(charstring.charAt(j - 1));
                    break;
                }
            }
            randalphanum += randchar;
        }
        return randalphanum;
    }

    public static void getDate(Activity context, EditText editText) {
        Calendar myCalendar = Calendar.getInstance();


        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            String startDateStr = sdf.format(myCalendar.getTime());
            editText.setText(startDateStr);

        };
        new DatePickerDialog(Objects.requireNonNull(context), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static void getTime(Context context, EditText editText) {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, (timePicker, hourOfDay, minute1) -> {
            boolean isPM = (hourOfDay >= 12);
            String time = " " + String.format("%02d:%02d %s",
                    (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute1, isPM ? "PM" : "AM");
            editText.setText(time);
        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public static void success(Context context, String message) {
        Toasty.success(context, message, Toasty.LENGTH_SHORT, true).show();
    }

    public static void error(Context context, String message) {
        Toasty.error(context, message, Toasty.LENGTH_SHORT, true).show();
    }

    public static void info(Context context, String message) {
        Toasty.info(context, message, Toasty.LENGTH_SHORT, true).show();
    }

    public static void warning(Context context, String message) {
        Toasty.warning(context, message, Toasty.LENGTH_SHORT, true).show();
    }

    public static void saveDataInSharedPrefs(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static String getDataFromSharedPrefs(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, "");
    }

    public static void clearSharedPrefs(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("email", "").apply();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("password", "").apply();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("type", "").apply();
    }

}
