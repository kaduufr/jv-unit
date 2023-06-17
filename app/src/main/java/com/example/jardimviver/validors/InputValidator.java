package com.example.jardimviver.validors;

import android.text.TextUtils;

public class InputValidator {
  public static boolean isValidName(String name) {
    return !TextUtils.isEmpty(name) && name.length() > 3;
  }

  public static boolean isValidSurname(String surname) {
    return !TextUtils.isEmpty(surname) && surname.length() > 3;
  }

  public static boolean isValidAddress(String address) {
    return !TextUtils.isEmpty(address) && address.length() > 3;
  }

  public static boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    return !TextUtils.isEmpty(email) && email.matches(emailRegex);
  }

  public static boolean isValidAge(Integer age) {
    return age > 0;
  }

}

