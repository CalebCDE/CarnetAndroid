package com.spisoft.quicknote;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.spisoft.quicknote.synchro.AccountConfigActivity;
import com.spisoft.sync.Configuration;
import com.spisoft.sync.utils.Utils;

/**
 * Created by alexandre on 22/02/16.
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        Log.d("uiddebug",PreferenceHelper.getUid(this));
        Utils.context = this;

        Configuration.sOnAccountSelectedListener = new Configuration.OnAccountSelectedListener() {
            @Override
            public void onAccountSelected(int accountId, int accountType) {
                Intent intent = new Intent(MyApplication.this, AccountConfigActivity.class);
                intent.putExtra(AccountConfigActivity.EXTRA_ACCOUNT_ID, accountId);
                intent.putExtra(AccountConfigActivity.EXTRA_ACCOUNT_TYPE, accountType);
                startActivity(intent);
            }
        };
    }
}
