package com.lucasdias.breed.presentation.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucasdias.android_core.extension.loadImage
import com.lucasdias.breed.presentation.view.databinding.BreedListItemBinding
import com.lucasdias.breed.presentation.view_model.model.UIBreed

class BreedListAdapter(private val navigate: (UIBreed) -> Unit) :
    ListAdapter<UIBreed, BreedListAdapter.ViewHolder>(BreedListDiffUtil) {

    fun update(breedList: List<UIBreed>) {
        submitList(breedList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = BreedListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding, navigate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(breed = getItem(position))
    }

    inner class ViewHolder(
        private val itemBinding: BreedListItemBinding,
        private val navigate: (UIBreed) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(breed: UIBreed) = with(itemBinding) {
            title.text = breed.name
            image.loadImage(url = breed.imageUrl)
            layout.setOnClickListener {
                navigate(breed)
            }
        }
    }

    private companion object BreedListDiffUtil : DiffUtil.ItemCallback<UIBreed>() {
        override fun areItemsTheSame(
            oldItem: UIBreed,
            newItem: UIBreed
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UIBreed,
            newItem: UIBreed
        ): Boolean {
            return oldItem == newItem
        }
    }
}
