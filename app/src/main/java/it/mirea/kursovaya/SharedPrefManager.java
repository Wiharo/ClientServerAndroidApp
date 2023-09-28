package it.mirea.kursovaya;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "HomeAccaunting";

    private static final String KEY_USER_ID = "id";
    private static final String KEY_ACCOUNT_ID = "account_id";
    private static final String KEY_CURRENCY_ID = "currency_id";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void saveUserDetails(int userId, int accountId, int currencyId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, userId);
        editor.putInt(KEY_ACCOUNT_ID, accountId);
        editor.putInt(KEY_CURRENCY_ID, currencyId);
        editor.apply();
    }


}
