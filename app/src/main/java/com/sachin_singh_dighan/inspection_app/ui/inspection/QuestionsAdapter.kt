package com.sachin_singh_dighan.inspection_app.ui.inspection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin_singh_dighan.inspection_app.data.local.entity.QuestionEntity
import com.sachin_singh_dighan.inspection_app.databinding.LayoutOptionBinding
import com.sachin_singh_dighan.inspection_app.utils.ItemClickListener
import com.sachin_singh_dighan.inspection_app.utils.setNestedUi

class QuestionsAdapter(
    private var questionsList: ArrayList<QuestionEntity>
) : RecyclerView.Adapter<QuestionsAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<QuestionEntity>

    lateinit var setNestedUi: setNestedUi<RecyclerView>

    class DataViewHolder(private val binding: LayoutOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            question: QuestionEntity,
            itemClickListener: ItemClickListener<QuestionEntity>,
            setNestedUi: setNestedUi<RecyclerView>
        ) {
            binding.tvQuestion.text = question.name
            setNestedUi(binding.rvAnswerChoices)
            itemClickListener(bindingAdapterPosition, question)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = questionsList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(questionsList[position], itemClickListener, setNestedUi)
    }

    fun addData(list: List<QuestionEntity>) {
        questionsList.clear()
        questionsList.addAll(list)
    }
}