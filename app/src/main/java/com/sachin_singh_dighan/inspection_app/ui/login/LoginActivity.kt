package com.sachin_singh_dighan.inspection_app.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sachin_singh_dighan.inspection_app.InspectionApplication
import com.sachin_singh_dighan.inspection_app.data.model.Authentication
import com.sachin_singh_dighan.inspection_app.databinding.ActivityLoginBinding
import com.sachin_singh_dighan.inspection_app.di.component.DaggerActivityComponent
import com.sachin_singh_dighan.inspection_app.di.module.ActivityModule
import com.sachin_singh_dighan.inspection_app.ui.base.UiState
import com.sachin_singh_dighan.inspection_app.ui.inspection.InspectionActivity
import com.sachin_singh_dighan.inspection_app.ui.register.RegisterActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    companion object{
        fun getInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
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
            val email = binding.editEmail.editText?.text?.toString()?.trim()
            val password = binding.editPassword.editText?.text?.toString()?.trim()
            viewModel.loginUser(email ?: "", password ?: "")
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
                            Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun getUser(credentials: Authentication) {
        //if (credentials.email.isNotEmpty() && credentials.password.isNotEmpty()) {
            //Navigate to HomeScreen
            startActivity(InspectionActivity.getInstance(this@LoginActivity))
            finish()
        //}

    }
}