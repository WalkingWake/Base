package dev.lazygarde.multi.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.lazygarde.multi.theme.databinding.ItemSelectThemeBinding


class ThemeAdapter(
    list: List<Int>
) : RecyclerView.Adapter<ThemeAdapter.ViewHolder>() {

    private val newList: List<Int> =
        listOf(list.last()) + list + listOf(list.first())


    inner class ViewHolder(private val binding: ItemSelectThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.ivBackgroundTheme.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSelectThemeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newList[position])
    }
}