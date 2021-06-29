 package com.labpanel.feature.professor.presentation.view.auth.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.labpanel.R
import com.labpanel.databinding.ActivityProfessorLoginBinding
import com.labpanel.feature.professor.domain.model.UserLoginData
import com.labpanel.feature.professor.presentation.view.auth.authstate.EmailState
import com.labpanel.feature.professor.presentation.view.auth.authstate.PasswordState
import com.labpanel.feature.professor.presentation.view.auth.registration.ProfessorRegistrationActivity
import com.labpanel.feature.professor.presentation.view.profile.ProfileActivity
import com.labpanel.feature.app.presentation.view.viewevents.LoadingState

class ProfessorLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfessorLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener: AuthStateListener
    private var isValidEmail = false
    private var isValidPassword = false
    private val viewModel by lazy {
        ViewModelProviders.of(this)[ProfessorLoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfessorLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

        subscribeUI()
        initViews()
    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authStateListener)
    }

    private fun subscribeUI() {
        subscribeCurrentUserState()
        subscribeLoadingEvent()
        subscribeEmailValidation()
        subscribePasswordValidation()
    }

    private fun initViews() {
        clickToLogin()
        createAnAccount()
    }

    private fun subscribeCurrentUserState() {
        authStateListener = AuthStateListener {
            if (auth.currentUser != null) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun subscribeLoadingEvent() {
        viewModel.onLoadingState.observe(this, Observer {
            when (it) {
                LoadingState.Show -> binding.progressBar.visibility = VISIBLE
                LoadingState.Hide -> binding.progressBar.visibility = INVISIBLE
            }
        })
    }

    private fun subscribeEmailValidation() {
        viewModel.onEmailState.observe(this, Observer {
            when (it) {
                EmailState.ValidEmail -> {
                    isValidEmail = true
                    signIn()
                }
                EmailState.InvalidEmail -> {
                    viewModel.hideLoading()
                    isValidEmail = false
                    binding.edtLoginEmail.error = getString(R.string.auth_invalid_email_error)
                    binding.edtLoginEmail.requestFocus()
                }
            }
        })
    }

    private fun subscribePasswordValidation() {
        viewModel.onPasswordState.observe(this, Observer {
            when (it) {
                PasswordState.ValidPassword -> {
                    isValidPassword = true
                    signIn()
                }
                PasswordState.InvalidPassword -> {
                    viewModel.hideLoading()
                    isValidPassword = false
                    binding.edtLoginPassword.error = getString(R.string.auth_invalid_password_error)
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

    private fun signIn() {
        if (isValidEmail && isValidPassword) {
            auth.signInWithEmailAndPassword(
                binding.edtLoginEmail.text.toString().trim(),
                binding.edtLoginPassword.text.toString().trim()
            )
                .addOnCompleteListener(this, OnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "logged in from input", Toast.LENGTH_LONG).show()
                        //TODO intent to profile
                    } else {
                        viewModel.hideLoading()
                        nonExistentAccount()
                    }
                })

        }
    }

    private fun createAnAccount() {
        binding.btnLoginRegister.setOnClickListener {
            startActivity(
                Intent(
                    this@ProfessorLoginActivity,
                    ProfessorRegistrationActivity::class.java
                )
            )
        }
    }

    private fun sendUserLoginDataToValidation() {
        val userLoginData = UserLoginData(
            email = binding.edtLoginEmail.text.toString().trim(),
            password = binding.edtLoginPassword.text.toString().trim()
        )
        viewModel.validateUserLoginData(userLoginData)
    }

    private fun nonExistentAccount() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.login_non_existent_account_dialog_title)
        builder.setMessage(R.string.login_non_existent_account_dialog_messagem)
        builder.setPositiveButton(R.string.login_non_existent_account_dialog_positive_btn) { _: DialogInterface, _: Int ->
            startActivity(
                Intent(
                    this@ProfessorLoginActivity,
                    ProfessorRegistrationActivity::class.java
                )
            )
        }
        builder.setNegativeButton(R.string.login_non_existent_account_dialog_negative_btn) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.cancel()
        }
        builder.create().show()
    }
}