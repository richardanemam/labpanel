package com.presentation.view.auth.registration

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.domain.model.UserRegistrationData
import com.presentation.states.EmailState
import com.presentation.states.LoadingState
import com.presentation.states.NameState
import com.presentation.states.PasswordState
import com.professor.R
import com.professor.databinding.ActivityProfessorRegistrationBinding

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
        viewModel.onLoadingState.observe(this, {
            when (it) {
                LoadingState.Show -> binding.progressBar.visibility = View.VISIBLE
                LoadingState.Hide -> binding.progressBar.visibility = View.INVISIBLE
            }
        })
    }

    private fun subscribeNameValidation() {
        viewModel.onNameState.observe(this, {
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
        viewModel.onEmailState.observe(this, {
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
        viewModel.onPasswordState.observe(this, {
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
                getUserInputData().email,
                getUserInputData().password
            )
                .addOnCompleteListener(this, { task ->
                    if (task.isSuccessful) {
                        viewModel.hideLoading()
                        updateUserProfile()
                        Toast.makeText(this, "registrado com sucesso", Toast.LENGTH_LONG).show()
                    } else {
                        viewModel.hideLoading()
                        tryLater()
                    }
                })
        }
    }

    private fun updateUserProfile() {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(getUserInputData().name)
            .build()

        auth.currentUser?.updateProfile(profileUpdates)
    }

    private fun getUserInputData(): UserRegistrationData {
        return UserRegistrationData(
            name = binding.edtRegistrationName.text.toString().trim(),
            email = binding.edtRegistrationEmail.text.toString().trim(),
            password = binding.edtRegistrationPassword.text.toString().trim()
        )
    }

    private fun clickToLoginInstead() {
        binding.btnRegistrationLoginInstead.setOnClickListener {
            finish()
        }
    }

    private fun clickToCreateNewAccount() {
        binding.btnRegistrationContinue.setOnClickListener {
            viewModel.showLoading()
            viewModel.validateUserRegistrationData(getUserInputData())
        }
    }

    private fun tryLater() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.registration_error)
        builder.setPositiveButton(R.string.registration_error_btn_text) { _: DialogInterface, _: Int ->
           finish()
        }
        builder.create().show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}