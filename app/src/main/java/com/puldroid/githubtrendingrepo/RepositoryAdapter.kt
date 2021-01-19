package com.puldroid.githubtrendingrepo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo.view.*

class RepositoryAdapter(private val list: List<Response>) : RecyclerView.Adapter<ItemViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
    return ItemViewHolder(layout)
  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    holder.itemView.tvRepoName.text = list[position].name
    Picasso.get().load(list[position].avatar).into(holder.itemView.imgAvatar)
    holder.itemView.tvLanguage.text = list[position].language
  }

}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)