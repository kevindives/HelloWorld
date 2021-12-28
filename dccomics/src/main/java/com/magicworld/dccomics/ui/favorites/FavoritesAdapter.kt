package com.magicworld.dccomics.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.magicworld.dccomics.R
import com.magicworld.dccomics.data.local.SuperheroeLocal
import com.squareup.picasso.Picasso

class FavoritesAdapter(
    private val superheroesList: ArrayList<SuperheroeLocal>,
    private val onItemClicked: (SuperheroeLocal) -> Unit
) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_superhoroe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val superheroe = superheroesList[position]
        holder.itemView.setOnClickListener { onItemClicked(superheroesList[position]) }
        holder.bind(superheroe)
    }

    override fun getItemCount(): Int {
        return superheroesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        private var aliasTextView: TextView = itemView.findViewById(R.id.alias_text_view)
        private var pictureImageView: ImageView =
            itemView.findViewById((R.id.picture_image_view))

        fun bind(superheroe: SuperheroeLocal) {
            aliasTextView.text = superheroe.alias
            nameTextView.text = superheroe.name
            Picasso.get().load(superheroe.urlPicture).into(pictureImageView)
        }
    }

    fun appendItems(newItems: ArrayList<SuperheroeLocal>) {
        this.superheroesList.clear()
        this.superheroesList.addAll(newItems)
        notifyDataSetChanged()
    }
}
