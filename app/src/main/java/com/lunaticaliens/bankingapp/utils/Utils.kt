package com.lunaticaliens.bankingapp.utils

import android.content.Context
import es.dmoral.toasty.Toasty
import org.joda.time.DateTime
import java.util.regex.Pattern

object Utils {
    const val DEBIT = 0
    const val CREDIT = 1
    const val INSERT = 22
    const val UPDATE = 33
    const val DELETE = 44
    const val DELETE_ALL = 55

    private val VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
    private val VALID_PASSWORD_REGEX =
        Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{6,}$", Pattern.CASE_INSENSITIVE)

    @JvmStatic
    fun validateEmail(emailStr: CharSequence?): Boolean {
        val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr)
        return matcher.find()
    }

    @JvmStatic
    fun validatePassword(passwordStr: CharSequence?): Boolean {
        val matcher = VALID_PASSWORD_REGEX.matcher(passwordStr)
        return matcher.find()
    }

    @JvmStatic
    fun generateToken(): String? {
        val charstring = "abcdefghijklmnopqrstuvwxyz0123456789"
        var randalphanum: String? = ""
        var randroll: Double
        var randchar: String?
        for (i in 0..149) {
            randroll = Math.random()
            randchar = ""
            for (j in 1..35) {
                if (randroll <= 1.0 / 36.0 * j) {
                    randchar = Character.toString(charstring[j - 1])
                    break
                }
            }
            randalphanum += randchar
        }
        return randalphanum
    }

    @JvmStatic
    fun success(context: Context?, message: String?) {
        Toasty.success(context!!, message!!, Toasty.LENGTH_SHORT, true).show()
    }

    @JvmStatic
    fun error(context: Context?, message: String?) {
        Toasty.error(context!!, message!!, Toasty.LENGTH_SHORT, true).show()
    }

    @JvmStatic
    fun info(context: Context?, message: String?) {
        Toasty.info(context!!, message!!, Toasty.LENGTH_SHORT, true).show()
    }

    fun warning(context: Context?, message: String?) {
        Toasty.warning(context!!, message!!, Toasty.LENGTH_SHORT, true).show()
    }

    @JvmStatic
    val currentDate: String
        get() {
            val stringBuilder = StringBuilder()
            when (DateTime.now().dayOfWeek) {
                1 -> stringBuilder.append("Monday,  ")
                2 -> stringBuilder.append("Tuesday,  ")
                3 -> stringBuilder.append("Wednesday,  ")
                4 -> stringBuilder.append("Thursday,  ")
                5 -> stringBuilder.append("Friday,  ")
                6 -> stringBuilder.append("Saturday,  ")
                7 -> stringBuilder.append("Sunday,  ")
            }
            stringBuilder.append(
                DateTime.now().dayOfMonth.toString() + "/" +
                        DateTime.now().monthOfYear + "/" +
                        DateTime.now().year
            )
            return stringBuilder.toString()
        }
}