package com.sachin_singh_dighan.inspection_app.ui.register

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
import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import com.sachin_singh_dighan.inspection_app.databinding.ActivityRegisterBinding
import com.sachin_singh_dighan.inspection_app.di.component.DaggerActivityComponent
import com.sachin_singh_dighan.inspection_app.di.module.ActivityModule
import com.sachin_singh_dighan.inspection_app.ui.base.UiState
import com.sachin_singh_dighan.inspection_app.ui.login.LoginActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    @Inject
    lateinit var viewModel: RegisterViewModel

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setObserver()
    }


    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as InspectionApplication).applicationComponent)
            .activityModule(
                ActivityModule(this)
            ).build().injectRegisterActivity(this)
    }

    private fun setupUI() {
        binding.buttonRegister.setOnClickListener {
            val email = binding.editEmail.editText?.text?.toString()?.trim()
            val password = binding.editPassword.editText?.text?.toString()?.trim()
            viewModel.registerUser(email ?: "", password ?: "")
        }
        binding.buttonLogin.setOnClickListener {
            startActivity(LoginActivity.getInstance(this@RegisterActivity))
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
                            Toast.makeText(this@RegisterActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun getUser(credentials: AuthenticationEntity) {
        if (credentials.email.isNotEmpty() && credentials.password.isNotEmpty()) {
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            startActivity(LoginActivity.getInstance(this@RegisterActivity))
        }

    }
}