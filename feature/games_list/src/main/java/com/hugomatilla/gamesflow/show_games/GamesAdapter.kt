package com.hugomatilla.gamesflow.show_games;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.game_list_item.view.*

class GamesAdapter(private val itemClick: (GamePresentation) -> Unit) :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private var games = emptyList<GamePresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.game_list_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindArticle(games.elementAt(position))
    }

    override fun getItemCount() = games.size

    internal fun setItems(items: List<GamePresentation>) {
        this.games = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, private val itemClick: (GamePresentation) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bindArticle(game: GamePresentation) {
            with(game) {
                itemView.title.text = title
                itemView.caption.text = caption
                itemView.rating.text = rating.toString()
                itemView.genre.text = genre.name
                Picasso
                    .get()
                    .load(imageUrl)
                    .into(itemView.image)
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}