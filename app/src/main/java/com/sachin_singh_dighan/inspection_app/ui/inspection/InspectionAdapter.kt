package com.sachin_singh_dighan.inspection_app.ui.inspection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin_singh_dighan.inspection_app.data.model.Category
import com.sachin_singh_dighan.inspection_app.databinding.LayoutInspectionBinding
import com.sachin_singh_dighan.inspection_app.utils.ItemClickListener
import com.sachin_singh_dighan.inspection_app.utils.setNestedUi

class InspectionAdapter(
    private val categoryList: ArrayList<Category>
) : RecyclerView.Adapter<InspectionAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<Category>

    lateinit var setNestedUi: setNestedUi<RecyclerView>

    class DataViewHolder(private val binding: LayoutInspectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isSelected = false
        fun bind(
            category: Category,
            itemClickListener: ItemClickListener<Category>,
            setNestedUi: setNestedUi<RecyclerView>
        ) {
            binding.tvCategory.text = category.name
            binding.tvCategory.setOnClickListener {
                itemClickListener(bindingAdapterPosition, category)
                if (isSelected) {
                    binding.ivCategorySelection.setImageResource(android.R.drawable.arrow_down_float)
                    binding.rvOptions.visibility = View.VISIBLE
                    setNestedUi(binding.rvOptions)
                    isSelected = false
                } else {
                    binding.ivCategorySelection.setImageResource(android.R.drawable.arrow_up_float)
                    binding.rvOptions.visibility = View.GONE
                    isSelected = true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInspectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(categoryList[position], itemClickListener, setNestedUi)
    }

    fun addData(list: List<Category>) {
        categoryList.clear()
        categoryList.addAll(list)
    }
}