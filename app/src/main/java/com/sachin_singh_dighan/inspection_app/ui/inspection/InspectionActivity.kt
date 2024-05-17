package com.sachin_singh_dighan.inspection_app.ui.inspection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sachin_singh_dighan.inspection_app.InspectionApplication
import com.sachin_singh_dighan.inspection_app.data.model.Inspection
import com.sachin_singh_dighan.inspection_app.databinding.ActivityInspectionBinding
import com.sachin_singh_dighan.inspection_app.di.component.DaggerActivityComponent
import com.sachin_singh_dighan.inspection_app.di.module.ActivityModule
import com.sachin_singh_dighan.inspection_app.ui.base.UiState
import com.sachin_singh_dighan.inspection_app.ui.login.LoginActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class InspectionActivity : AppCompatActivity() {

    companion object{
        fun getInstance(context: Context): Intent {
            return Intent(context, InspectionActivity::class.java)
        }
    }
    @Inject
    lateinit var viewModel: InspectionViewModel

    lateinit var binding: ActivityInspectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityInspectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setObserver()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as InspectionApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().injectInspectionActivity(this)
    }

    private fun setupUI() {
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect() {
                    when (it) {
                        is UiState.Success -> {
                            //binding.progressBar.visibility = View.GONE
                            it.data.let { inspection ->
                                getInspection(inspection)
                            }
                        }

                        is UiState.Loading -> {
                            //binding.progressBar.visibility = View.VISIBLE
                        }

                        is UiState.Error -> {
                            //binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@InspectionActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun getInspection(inspection: Inspection) {

    }
}