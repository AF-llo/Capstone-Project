package de.ironcoding.fitsim.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.auth.AuthUI;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.databinding.FragmentHighscoreBinding;
import de.ironcoding.fitsim.ui.presenter.HighscorePresenter;

/**
 * Created by larsl on 27.04.2017.
 */

public class HighscoreFragment extends BaseFragment<HighscorePresenter> implements HighscorePresenter.LoginCallback {

    public static final int REQUEST_CODE_SIGN_IN = 1;

    public static HighscoreFragment getInstance() {
        return new HighscoreFragment();
    }

    @Override
    public HighscorePresenter createPresenter() {
        return new HighscorePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentHighscoreBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_highscore, container, false);
        binding.setHighscorePresenter(mPresenter);
        return binding.getRoot();
    }

    @Override
    public void login() {
        List<AuthUI.IdpConfig> configs = new ArrayList<>();
        configs.add(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setTheme(R.style.FirebaseLoginTheme)
                        .setProviders(configs)
                        .build(),
                REQUEST_CODE_SIGN_IN);
    }
}
