package com.example.marvelheroes.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.R
import com.example.marvelheroes.data.models.response.CharacterDetails
import com.example.marvelheroes.databinding.RowCharacterBinding
import com.example.marvelheroes.databinding.RowLoadingBinding
import com.squareup.picasso.Picasso

class UserListRVAdapter(private var userList: List<CharacterDetails>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ItemViewHolder(binding: RowCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgCharacter = binding.imgCharacter
        val tvCharacterName = binding.tvName
        val tvCharacterDesc = binding.tvCharacterDesc
    }

    class LoadingViewHolder(binding: RowLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        val progressCharacterLoading = binding.progressCircularUsers
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_character, parent, false)
            val binding = RowCharacterBinding.bind(view)
            ItemViewHolder(binding)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_loading, parent, false)
            val binding = RowLoadingBinding.bind(view)
            LoadingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            populateItemRows(holder, position)
        } else if (holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    fun submitUserList(newList: List<CharacterDetails>) {
        val oldList = userList
        userList = newList
        notifyItemRangeInserted(oldList.size - 1, newList.size)
    }

    override fun getItemViewType(position: Int): Int {
        return if (userList[position].isLoadingView) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    private fun showLoadingView(holder: LoadingViewHolder, position: Int) {
        holder.progressCharacterLoading.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun populateItemRows(holder: ItemViewHolder, position: Int) {
        Picasso.get().load(userList[position].thumbnail.path).into(holder.imgCharacter)
        holder.tvCharacterName.text = userList[position].name
        holder.tvCharacterDesc.text = userList[position].description
    }

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }

}