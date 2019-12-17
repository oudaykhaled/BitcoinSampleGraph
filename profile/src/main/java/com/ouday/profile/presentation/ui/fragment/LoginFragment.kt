package com.ouday.profile.presentation.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.ouday.core.ioc.ProfileIoc
import com.ouday.core.network.Status
import com.ouday.core.presentation.BaseFragment
import com.ouday.core.presentation.ViewModelFactory
import com.ouday.profile.R
import com.ouday.profile.data.model.Profile
import com.ouday.profile.presentation.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var profileIoc: ProfileIoc

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ProfileViewModel::class.java)

        viewModel.getLoginLiveData().observe(this@LoginFragment,androidx.lifecycle.Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.ERROR -> {
                    it.message
                    dismissLoading()
                    onLoginSuccess(null)
                }
                Status.SUCCESS -> {
                    dismissLoading()
                    it.data?.let {data ->
                        onLoginSuccess(data)
                    }
                }
            }
        })


        btnLogin.setOnClickListener {
            activity?.let { profileIoc.onLoginSucceeded(it) }
            viewModel.requestLogin(etUsername.text.toString(), etPassword.text.toString())
        }
    }

    private fun onLoginSuccess(data: Profile?) {
        activity?.let { profileIoc.onLoginSucceeded(it) }
    }

}
