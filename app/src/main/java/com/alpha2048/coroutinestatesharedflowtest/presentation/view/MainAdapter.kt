package com.alpha2048.coroutinestatesharedflowtest.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpha2048.coroutinestatesharedflowtest.databinding.ItemRepoBinding
import com.alpha2048.coroutinestatesharedflowtest.domain.entity.RepoItemEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class MainAdapter(private val parentScope: CoroutineScope) : RecyclerView.Adapter<ItemViewHolder>() {

    private var repoItems: List<RepoItemEntity> = emptyList()

    private val _onClick: MutableSharedFlow<RepoItemEntity> = MutableSharedFlow(0)
    val onClick: SharedFlow<RepoItemEntity> = _onClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val repoItem = repoItems[position]
        holder.binding.repoItem = repoItem
        holder.binding.repoTextStargazersCount.text = repoItem.stargazersCount.toString()

        holder.binding.clickView.setOnClickListener {
            parentScope.launch {
                _onClick.emit(repoItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return repoItems.size
    }

    fun setItem(repoItems: List<RepoItemEntity>) {
        this.repoItems = repoItems
        notifyDataSetChanged()
    }

}

class ItemViewHolder(val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root)