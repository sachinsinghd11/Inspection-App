package com.sachin_singh_dighan.inspection_app.ui.inspection

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sachin_singh_dighan.inspection_app.InspectionApplication
import com.sachin_singh_dighan.inspection_app.data.model.AnswerChoice
import com.sachin_singh_dighan.inspection_app.data.model.Category
import com.sachin_singh_dighan.inspection_app.data.model.Question
import com.sachin_singh_dighan.inspection_app.databinding.ActivityInspectionBinding
import com.sachin_singh_dighan.inspection_app.di.component.DaggerActivityComponent
import com.sachin_singh_dighan.inspection_app.di.module.ActivityModule
import com.sachin_singh_dighan.inspection_app.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class InspectionActivity : AppCompatActivity() {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, InspectionActivity::class.java)
        }
    }

    @Inject
    lateinit var viewModel: InspectionViewModel

    @Inject
    lateinit var adapter: InspectionAdapter

    @Inject
    lateinit var questionsAdapter: QuestionsAdapter

    @Inject
    lateinit var answerChoicesAdapter: AnswerChoicesAdapter

    private lateinit var binding: ActivityInspectionBinding
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

    lateinit var answerChoiceRecyclerView: RecyclerView

    @SuppressLint("NotifyDataSetChanged")
    private fun setupUI() {
        setRecyclerviewLayout(binding.rvSurvey)
        binding.rvSurvey.adapter = adapter

        adapter.setNestedUi = { recyclerview ->
            setRecyclerviewLayout(recyclerview)
            recyclerview.adapter = questionsAdapter
        }

        questionsAdapter.setNestedUi = { recyclerview ->
            answerChoiceRecyclerView = recyclerview
            setRecyclerviewLayout(recyclerview)
            recyclerview.adapter = answerChoicesAdapter
        }

        adapter.itemClickListener = { _, category ->
            getQuestion(category.questions)
        }

        questionsAdapter.itemClickListener = { _, questions ->
            getAnswerChoice(questions.answerChoices)
        }

        answerChoicesAdapter.itemClickListener = { _, answerChoices ->
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect() {
                    when (it) {
                        is UiState.Success -> {
                            //binding.progressBar.visibility = View.GONE
                            it.data.let { inspection ->
                                binding.tvArea.text =
                                    "Take inspection for ${inspection.area.name} in ${inspection.inspectionType.name}"
                                getInspection(inspection.survey.categories)
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

    private fun setRecyclerviewLayout(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getInspection(categories: List<Category>) {
        adapter.addData(categories)
        //adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getQuestion(question: List<Question>) {
        questionsAdapter.addData(question)
        //adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAnswerChoice(answerChoice: List<AnswerChoice>) {
        answerChoicesAdapter.addData(answerChoice)
    }
}