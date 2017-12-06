package com.spisoft.quicknote.synchro;

import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spisoft.quicknote.PreferenceHelper;
import com.spisoft.quicknote.R;
import com.spisoft.sync.Log;
import com.spisoft.sync.browsing.FilePickerActivity;
import com.spisoft.sync.wrappers.*;
import com.spisoft.sync.wrappers.Wrapper;

/**
 * A placeholder fragment containing a simple view.
 */
public class AccountConfigActivityFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private Preference mBrowsePreference;
    private int mAccountId;
    private int mAccountType;
    private Wrapper mWrapper;
    private String mCurrentlySetPath;

    public AccountConfigActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.empty_pref);
        mAccountId =  getActivity().getIntent().getIntExtra(AccountConfigActivity.EXTRA_ACCOUNT_ID,-1);
        Log.d("accounddebug","config "+mAccountId);
        mAccountType =  getActivity().getIntent().getIntExtra(AccountConfigActivity.EXTRA_ACCOUNT_TYPE,-1);
        mWrapper = com.spisoft.sync.wrappers.WrapperFactory.getWrapper(getActivity(),mAccountType, mAccountId);
        mCurrentlySetPath = mWrapper.getRemoteSyncDir(PreferenceHelper.getRootPath(getActivity()));
        mBrowsePreference = new Preference(getActivity());
        mBrowsePreference.setTitle(R.string.remote_folder);
        mBrowsePreference.setSummary(mCurrentlySetPath);
        mBrowsePreference.setOnPreferenceClickListener(this);
        getPreferenceScreen().addPreference(mBrowsePreference);


    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        Intent intent = new Intent(getActivity(), FilePickerActivity.class);
        intent.putExtra(FilePickerActivity.EXTRA_ACCOUNT_ID, mAccountId);
        intent.putExtra(FilePickerActivity.EXTRA_START_PATH, mCurrentlySetPath);
        getActivity().startActivity(intent);
        return true;
    }
}
