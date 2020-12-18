package com.example.sofra.data.local;


import android.app.Activity;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static SharedPreferences sharedPreferences = null;
    public static String USER_API_TOKEN = "USER_API_TOKEN";
    private static String USER_ID = "USER_ID";
    private static String USER_NAME = "USER_NAME";
    private static String USER_EMAIL = "USER_EMAIL";
    private static String USER_BID = "USER_BID";
    private static String USER_PHONE = "USER_PHONE";
    private static String USER_DLD = "USER_DLD";
    private static String USER_BLOOD_TYPE_ID = "USER_BLOOD_TYPE_ID";
    private static String USER_CITY_ID = "USER_CITY_ID";
    private static String USER_CITY_NAME = "USER_CITY_NAME";
    private static String USER_GOVERMENT_ID = "USER_GOVERMENT_ID";
    private static String USER_GOVERMENT_NAME = "USER_GOVERMENT_NAME";
    private static String USER_BLOOD_TYPE_NAME = "USER_BLOOD_TYPE_NAME";
    public static String User_password = "password";
    public static String KEY_IS_CHECK_BOX = "isCheckBox";

    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Blood", activity.MODE_PRIVATE);

        }
    }

    public static void SaveData(Activity activity, String data_Key, String data_Value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void SaveData(Activity activity, String data_Key, boolean data_Value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static String LoadData(Activity activity, String data_Key) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getString(data_Key, null);
    }

    public static boolean LoadBoolean(Activity activity, String data_Key) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getBoolean(data_Key, false);
    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }
    public static void setSharedPreferencesClient(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "sofra", activity.MODE_PRIVATE);
        }
    }

    public static boolean LoadBooleanClient(Activity activity, String data_Key) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferencesClient(activity);
        }

        return sharedPreferences.getBoolean(data_Key, false);
    }

}
