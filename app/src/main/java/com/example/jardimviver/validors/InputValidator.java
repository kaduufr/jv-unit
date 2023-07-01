package com.example.jardimviver.validors;

import android.text.TextUtils;

public class InputValidator {
  public static boolean isNotEmptyAndShort(String text) {
    return !TextUtils.isEmpty(text) && text.length() >= 3;
  }

  public static boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    return !TextUtils.isEmpty(email) && email.matches(emailRegex);
  }

  public static boolean isValidAge(Integer age) {
    return age > 0;
  }

}

