package com.ouday.oudayn26.di.ioc

import android.app.Activity
import android.content.Intent
import com.ouday.bitcoin.presentation.ui.BitcoinActivity
import com.ouday.core.ioc.ProfileIoc

class ProfileIocImpl: ProfileIoc {

    override fun onLoginSucceeded(activity: Activity) {
        var intent = Intent(activity, BitcoinActivity::class.java)
        activity.startActivity(intent)
    }

}