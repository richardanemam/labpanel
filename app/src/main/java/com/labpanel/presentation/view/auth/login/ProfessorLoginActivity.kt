package com.labpanel.presentation.view.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.labpanel.R
import com.labpanel.databinding.ActivityProfessorLoginBinding
import com.labpanel.domain.auth.model.UserLoginData
import com.labpanel.presentation.view.auth.authstate.EmailState
import com.labpanel.presentation.view.auth.authstate.PasswordState
import com.labpanel.presentation.view.auth.registration.ProfessorRegistrationActivity
import com.labpanel.presentation.view.viewevents.LoadingState

class ProfessorLoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityProfessorLoginBinding
    private val viewModel by lazy {
        ViewModelProviders.of(this)[ProfessorLoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfessorLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        subscribeUI()
        initViews()
    }

    private fun subscribeUI() {
        subscribeLoadingEvent()
        subscribeEmailValidation()
        subscribePasswordValidation()
    }

    private fun initViews() {
        clickToLogin()
        createAnAccount()
    }

    private fun subscribeLoadingEvent() {
        viewModel.onLoadingState.observe(this, Observer {
            when(it) {
                LoadingState.Show -> binding.progressBar.visibility = VISIBLE
                LoadingState.Hide -> binding.progressBar.visibility = INVISIBLE
            }
        })
    }

    private fun subscribeEmailValidation() {
        viewModel.onEmailState.observe(this, Observer {
            when(it) {
                EmailState.ValidEmail -> Toast.makeText(this, "email valido", Toast.LENGTH_SHORT).show()
                EmailState.InvalidEmail -> {
                    viewModel.hideLoading()
                    binding.edtLoginEmail.error = getString(R.string.login_invalid_email_error)
                    binding.edtLoginEmail.requestFocus()
                }
            }
        })
    }

    private fun subscribePasswordValidation() {
        viewModel.onPasswordState.observe(this, Observer {
            when(it) {
                PasswordState.ValidPassword ->  Toast.makeText(this, "senha valido", Toast.LENGTH_SHORT).show()
                PasswordState.InvalidPassword -> {
                    viewModel.hideLoading()
                    binding.edtLoginPassword.error = getString(R.string.login_invalid_password_error)
                    binding.edtLoginPassword.requestFocus()
                }
            }
        })
    }

    private fun clickToLogin() {
        binding.btnAuthenticationContinue.setOnClickListener {
            viewModel.showLoading()
            sendUserLoginDataToValidation()
        }
    }

    private fun createAnAccount() {
        binding.btnLoginRegister.setOnClickListener {
            startActivity(Intent(this@ProfessorLoginActivity, ProfessorRegistrationActivity::class.java))
        }
    }

    private fun sendUserLoginDataToValidation() {
        val userLoginData = UserLoginData(
            email = binding.edtLoginEmail.text.toString().trim(),
            password = binding.edtLoginPassword.text.toString().trim()
        )
        viewModel.validateUserLoginData(userLoginData)
    }

}