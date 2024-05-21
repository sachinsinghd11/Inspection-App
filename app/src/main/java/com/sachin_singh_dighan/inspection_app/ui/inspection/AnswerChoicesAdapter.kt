package com.sachin_singh_dighan.inspection_app.ui.inspection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin_singh_dighan.inspection_app.data.local.entity.AnswerChoiceEntity
import com.sachin_singh_dighan.inspection_app.databinding.LayoutAnswerChoicesBinding
import com.sachin_singh_dighan.inspection_app.utils.ItemClickListener
import com.sachin_singh_dighan.inspection_app.utils.setNestedUi

class AnswerChoicesAdapter(
    private var answerChoiceList: ArrayList<AnswerChoiceEntity>
) : RecyclerView.Adapter<AnswerChoicesAdapter.DataViewHolder>() {


    lateinit var itemClickListener: ItemClickListener<AnswerChoiceEntity>

    lateinit var setNestedUi: setNestedUi<RecyclerView>

    class DataViewHolder(private val binding: LayoutAnswerChoicesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var lastSelectedPosition = 0
        fun bind(
            answerChoice: AnswerChoiceEntity,
            itemClickListener: ItemClickListener<AnswerChoiceEntity>
        ) {

            binding.rbOption.text = answerChoice.name
            //binding.rbOption.isChecked = answerChoice.id == lastSelectedPosition
            binding.rbOption.setOnCheckedChangeListener { compoundButton, b ->

                if (b) {
                    lastSelectedPosition = answerChoice.id
                    binding.rbOption.isChecked = true
                    //lastSelectedPosition = position
                    //binding.rbOption.isChecked = lastSelectedPosition == position
                    itemClickListener(bindingAdapterPosition, answerChoice)
                } else {
                    binding.rbOption.isChecked = false
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutAnswerChoicesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = answerChoiceList.size
    override fun onBindViewHolder(
        holder: DataViewHolder,
        position: Int
    ) {
        holder.bind(answerChoiceList[position], itemClickListener)
    }

    fun addData(list: List<AnswerChoiceEntity>) {
        answerChoiceList.clear()
        answerChoiceList.addAll(list)
    }

}