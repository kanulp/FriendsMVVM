package com.kanulp.friendsmvvm.ui.posts

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kanulp.friendsmvvm.R
import com.kanulp.friendsmvvm.data.entities.Friend

class PostsGridAdapter(var recyclerDataArrayList: List<Friend>?, mcontext: Context?, var listener: OnItemClickListener) : RecyclerView.Adapter<PostsGridAdapter.PostViewHolder>() {

    var mcontext : Context? =null
    private var selectedItems: SparseBooleanArray? = null

    init {
        selectedItems = SparseBooleanArray()
        this.mcontext = mcontext
         setHasStableIds(true)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        // Inflate Layout
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_friends, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val recyclerData: Friend = recyclerDataArrayList!![position]
        holder.mBackground.isSelected = selectedItems!!.get(position, false);


        if (selectedItems!!.get(position, false)) {
            selectedItems?.delete(position);
            holder.mBackground.isSelected = false;
        }
        else {
            selectedItems?.put(position, true);
            holder.mBackground.isSelected = true;
        }

        holder.text.text = recyclerData.email
        Glide.with(holder.itemView)
                .load(recyclerData.avatar)
                .into(holder.img);
        holder.itemView.setOnClickListener{
            listener?.onItemClick(position)
            holder.mBackground.isSelected = true;
        }
    }

    override fun getItemCount(): Int {
        return recyclerDataArrayList!!.size
    }
    override fun getItemViewType(position: Int) = position
    override fun getItemId(position: Int): Long = position.toLong()


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        var text: TextView = itemView.findViewById(R.id.txt_email)
        var img: AppCompatImageView = itemView.findViewById(R.id.img)
        var mBackground : LinearLayout = itemView.findViewById(R.id.mBackground);

        override fun onClick(v: View?) {
            if (selectedItems!!.get(adapterPosition, false)) {
                selectedItems?.delete(adapterPosition);
                mBackground.isSelected = false;
            }
            else {
                selectedItems?.put(adapterPosition, true);
                mBackground.isSelected = true;
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}