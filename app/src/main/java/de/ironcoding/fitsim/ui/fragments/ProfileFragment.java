package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.auth.AuthUI;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentProfileBinding;
import de.ironcoding.fitsim.ui.presenter.ProfilePresenter;

/**
 * Created by larsl on 27.04.2017.
 */

public class ProfileFragment extends BaseFragment<ProfilePresenter> implements ProfilePresenter.LoginCallback {

    public static final int REQUEST_CODE_SIGN_IN = 1;

    public static ProfileFragment getInstance() {
        return new ProfileFragment();
    }

    @Override
    public ProfilePresenter createPresenter() {
        return new ProfilePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        binding.setProfilePresenter(mPresenter);
        return binding.getRoot();
    }

    @Override
    public void login() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setProviders(
                                AuthUI.EMAIL_PROVIDER)
                        .build(),
                REQUEST_CODE_SIGN_IN);
    }
}
