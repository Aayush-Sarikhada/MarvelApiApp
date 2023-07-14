package com.example.marvelheroes.ui.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.R
import com.example.marvelheroes.data.models.response.CharacterDetails
import com.example.marvelheroes.databinding.RowCharacterBinding
import com.squareup.picasso.Picasso

class UserListRVAdapter(private var userList: List<CharacterDetails>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ItemViewHolder(binding: RowCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    class LoadingViewHolder(binding: RowCharacterBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_user_data, parent, false)
            val binding = RowCharacterBinding.bind(view)
            ItemViewHolder(binding)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_loading, parent, false)
            val binding = RowCharacterBinding.bind(view)
            LoadingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder) {
            populateItemRows(holder,position)
        } else if(holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    fun submitUserList(newList: List<UserInfo>) {

        Log.d("DEBUG IN RVADAPTER", newList.toString())
        val oldList = userList
        userList = newList
        Log.d("DEBUG IN RVADAPTER", userList.toString())
        notifyItemRangeInserted(oldList.size-1,newList.size)
    }

    override fun getItemViewType(position: Int): Int {
        return if(userList[position].id == -1) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    private fun showLoadingView(holder: LoadingViewHolder, position: Int) {
        holder.progressUsers.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun populateItemRows(holder: ItemViewHolder, position: Int) {
        Picasso.get().load(userList[position].profilePicture).into(holder.imgView)
        holder.tvId.text = "ID: ${userList[position].id}"
        holder.tvEmail.text = "Email: ${userList[position].email}"
        holder.tvName.text = "Name: ${userList[position].name}"
        holder.tvCreatedOn.text = "Created on: ${userList[position].createdAt}"
    }

}