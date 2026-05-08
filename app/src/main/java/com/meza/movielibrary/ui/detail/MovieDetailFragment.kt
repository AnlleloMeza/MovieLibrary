package com.meza.movielibrary.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.meza.movielibrary.R
import com.meza.movielibrary.databinding.FragmentMovieDetailBinding
import com.meza.movielibrary.viewmodel.MovieViewModel

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentMovieDetailBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieId = arguments?.getInt("movieId") ?: -1
        viewModel.loadMovie(movieId)

        viewModel.selectedMovie.observe(viewLifecycleOwner) { movie ->
            if (movie == null) return@observe

            binding.tvTitle.text  = movie.title
            binding.tvYear.text   = "Año: ${movie.year}"
            binding.tvGenre.text  = "Género: ${movie.genre}"
            binding.tvRating.text = "Rating: ${movie.rating}"

            binding.cbWatched.setOnCheckedChangeListener(null)
            binding.cbWatched.isChecked = movie.watched
            binding.cbWatched.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked != movie.watched) {
                    viewModel.update(movie.copy(watched = isChecked))
                }
            }

            binding.btnEdit.setOnClickListener {
                val bundle = Bundle().apply { putInt("movieId", movie.id) }
                findNavController().navigate(R.id.action_detail_to_edit, bundle)
            }
            binding.btnDelete.setOnClickListener {
                viewModel.delete(movie)
                findNavController().navigate(R.id.movieListFragment)
            }
            binding.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}