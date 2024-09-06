package dev.lazygarde.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<MODEL, BINDING : ViewBinding>
    : RecyclerView.Adapter<BaseAdapter<MODEL, BINDING>.ViewHolder>() {

    abstract val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> BINDING

    abstract fun bind(model: MODEL, position: Int, binding: BINDING, context: Context)

    protected val list: MutableList<MODEL> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            bindingInflater.invoke(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(private val binding: BINDING) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MODEL, position: Int) {
            bind(model, position, binding, itemView.context)
        }

    }

    open fun updateList(
        newData: List<MODEL>,
        diffUtil: DiffUtil.Callback = BaseDiffUtilCallback(newData, list)
    ) {
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
        notifyItemChanged(0)
    }

}