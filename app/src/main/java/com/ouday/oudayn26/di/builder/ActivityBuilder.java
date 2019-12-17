package com.ouday.oudayn26.di.builder;

import com.ouday.bitcoin.presentation.ui.BitcoinActivity;
import com.ouday.oudayn26.MainActivity;
import com.ouday.profile.presentation.ui.activity.ProfileActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBuilder {

    @ContributesAndroidInjector
    BitcoinActivity getBitcoinActivity();

    @ContributesAndroidInjector
    MainActivity getMainActivity();

    @ContributesAndroidInjector
    ProfileActivity getProfileActivity();

}
