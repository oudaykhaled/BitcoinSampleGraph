package com.ouday.oudayn26.di.builder;

import com.ouday.bitcoin.presentation.ui.BitcoinChartFragment;
import com.ouday.profile.presentation.ui.fragment.LoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentBuilder {

    @ContributesAndroidInjector
    BitcoinChartFragment getBitcoinChartFragment();

    @ContributesAndroidInjector
    LoginFragment getLoginFragment();

}
