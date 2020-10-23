package com.labpanel.presentation.view.auth.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.labpanel.R
import com.labpanel.databinding.ActivityProfessorRegistrationBinding
import com.labpanel.domain.auth.model.UserRegistrationData
import com.labpanel.presentation.view.auth.authstate.EmailState
import com.labpanel.presentation.view.auth.authstate.NameState
import com.labpanel.presentation.view.auth.authstate.PasswordState
import com.labpanel.presentation.view.viewevents.LoadingState

class ProfessorRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfessorRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel by lazy { ViewModelProviders.of(this)[ProfessorRegistrationViewModel::class.java] }
    private var isValidName = false
    private var isValidEmail = false
    private var isValidPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfessorRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

        subscribeUI()
        initViews()
    }

    private fun subscribeUI() {
        subscribeLoadingEvent()
        subscribeEmailValidation()
        subscribePasswordValidation()
        subscribeNameValidation()
    }

    private fun initViews() {
        clickToLoginInstead()
        clickToCreateNewAccount()
    }

    private fun subscribeLoadingEvent() {
        viewModel.onLoadingState.observe(this, Observer {
            when (it) {
                LoadingState.Show -> binding.progressBar.visibility = View.VISIBLE
                LoadingState.Hide -> binding.progressBar.visibility = View.INVISIBLE
            }
        })
    }

    private fun subscribeNameValidation() {
        viewModel.onNameState.observe(this, Observer {
            when (it) {
                NameState.ValidName -> {
                    isValidName = true
                    createNewAccount()
                }
                NameState.NullOrEmptyName -> {
                    viewModel.hideLoading()
                    isValidName = false
                    binding.edtRegistrationName.error =
                        getString(R.string.registration_empty_name_input_error)
                    binding.edtRegistrationName.requestFocus()
                }
            }
        })
    }

    private fun subscribeEmailValidation() {
        viewModel.onEmailState.observe(this, Observer {
            when (it) {
                EmailState.ValidEmail -> {
                    isValidEmail = true
                    createNewAccount()
                }
                EmailState.InvalidEmail -> {
                    viewModel.hideLoading()
                    isValidEmail = false
                    binding.edtRegistrationEmail.error =
                        getString(R.string.auth_invalid_email_error)
                    binding.edtRegistrationEmail.requestFocus()
                }
            }
        })
    }

    private fun subscribePasswordValidation() {
        viewModel.onPasswordState.observe(this, Observer {
            when (it) {
                PasswordState.ValidPassword -> {
                    isValidPassword = true
                    createNewAccount()
                }
                PasswordState.InvalidPassword -> {
                    viewModel.hideLoading()
                    isValidPassword = false
                    binding.edtRegistrationPassword.error =
                        getString(R.string.auth_invalid_password_error)
                    binding.edtRegistrationPassword.requestFocus()
                }
            }
        })
    }

    private fun createNewAccount() {
        if (isValidName && isValidEmail && isValidPassword) {
            auth.createUserWithEmailAndPassword(
                binding.edtRegistrationEmail.text.toString().trim(),
                binding.edtRegistrationPassword.text.toString().trim()
            )
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //TODO abrir tela de profile e salvar dados de usuário
                        viewModel.hideLoading()
                        Toast.makeText(this, "registrado com sucesso", Toast.LENGTH_LONG).show()
                    } else {
                        viewModel.hideLoading()
                        Toast.makeText(this, "erro no registro", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    private fun sendUserLoginDataToValidation() {
        val userRegistrationData = UserRegistrationData(
            name = binding.edtRegistrationName.text.toString().trim(),
            email = binding.edtRegistrationEmail.text.toString().trim(),
            password = binding.edtRegistrationPassword.text.toString().trim()
        )
        viewModel.validateUserRegistrationData(userRegistrationData)
    }

    private fun clickToLoginInstead() {
        binding.btnRegistrationLoginInstead.setOnClickListener {
            finish()
        }
    }

    private fun clickToCreateNewAccount() {
        binding.btnRegistrationContinue.setOnClickListener {
            viewModel.showLoading()
            sendUserLoginDataToValidation()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}