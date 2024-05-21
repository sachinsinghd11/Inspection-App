package com.sachin_singh_dighan.inspection_app.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sachin_singh_dighan.inspection_app.InspectionApplication
import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import com.sachin_singh_dighan.inspection_app.databinding.ActivityLoginBinding
import com.sachin_singh_dighan.inspection_app.di.component.DaggerActivityComponent
import com.sachin_singh_dighan.inspection_app.di.module.ActivityModule
import com.sachin_singh_dighan.inspection_app.ui.base.UiState
import com.sachin_singh_dighan.inspection_app.ui.inspection.InspectionActivity
import com.sachin_singh_dighan.inspection_app.ui.register.RegisterActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    companion object {
        const val PREFS_KEY = "prefs"
        const val EMAIL_KEY = "email"
        const val PWD_KEY = "pwd"
        fun getInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private var emailEdt: String = ""
    private var pwdEdt: String = ""

    private var email: String = ""
    private var pwd: String = ""

    @Inject
    lateinit var viewModel: LoginViewModel

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setObserver()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as InspectionApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    private fun setupUI() {
        binding.buttonLogin.setOnClickListener {
            emailEdt = binding.editEmail.editText?.text?.toString()?.trim() ?: ""
            pwdEdt = binding.editPassword.editText?.text?.toString()?.trim() ?: ""
            viewModel.loginUser(emailEdt ?: "", pwdEdt ?: "")
        }
        binding.buttonRegister.setOnClickListener {
            startActivity(RegisterActivity.getInstance(this@LoginActivity))
            finish()
        }

    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect() {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            it.data.let { auth ->
                                getUser(auth)
                            }
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@LoginActivity,
                                "Please Register First",
                                Toast.LENGTH_SHORT
                            )
                                .show();

                        }
                    }
                }
            }
        }
    }

    private fun getUser(credentials: AuthenticationEntity) {
        viewModel.sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
        email = viewModel.sharedPreferences.getString(EMAIL_KEY, "").toString()
        pwd = viewModel.sharedPreferences.getString(PWD_KEY, "").toString()

        if (TextUtils.isEmpty(emailEdt) && TextUtils.isEmpty(pwdEdt)) {

            Toast.makeText(this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show()

        } else {
            if (credentials.email == emailEdt && credentials.password == pwdEdt) {
                val editor: SharedPreferences.Editor = viewModel.sharedPreferences.edit()

                editor.putString(EMAIL_KEY, credentials.email)
                editor.putString(PWD_KEY, credentials.password)
                editor.apply()

                startActivity(InspectionActivity.getInstance(this@LoginActivity))
                finish()
            } else {
                Toast.makeText(this, "User not found. Please register..", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (email.isNotEmpty() && pwd.isNotEmpty()) {
            startActivity(InspectionActivity.getInstance(this@LoginActivity))
            finish()
        }
    }
}