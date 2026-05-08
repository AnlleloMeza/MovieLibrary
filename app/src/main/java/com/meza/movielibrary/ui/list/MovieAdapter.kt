package com.meza.movielibrary.ui.list

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.meza.movielibrary.databinding.ItemMovieBinding
import com.meza.movielibrary.model.Movie

class MovieAdapter(
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies = emptyList<Movie>()

    fun submitList(list: List<Movie>) {
        movies = list
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvGenreYear.text = "${movie.genre} · ${movie.year}"
            binding.tvWatched.visibility = if (movie.watched) View.VISIBLE else View.GONE
            binding.root.setOnClickListener { onClick(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movies[position])

    override fun getItemCount() = movies.size
}