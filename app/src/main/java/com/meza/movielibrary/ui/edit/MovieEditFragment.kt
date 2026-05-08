package com.meza.movielibrary.ui.edit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.meza.movielibrary.databinding.FragmentMovieEditBinding
import com.meza.movielibrary.model.Movie
import com.meza.movielibrary.viewmodel.MovieViewModel

class MovieEditFragment : Fragment() {

    private var _binding: FragmentMovieEditBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentMovieEditBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieId = arguments?.getInt("movieId") ?: -1
        val isEditing = movieId != -1

        if (isEditing) {
            viewModel.loadMovie(movieId)
            viewModel.selectedMovie.observe(viewLifecycleOwner) { movie ->
                if (movie == null) return@observe
                binding.etTitle.setText(movie.title)
                binding.etYear.setText(movie.year.toString())
                binding.etGenre.setText(movie.genre)
                binding.etRating.setText(movie.rating.toString())
                binding.cbWatched.isChecked = movie.watched
            }
        }

        binding.btnSave.setOnClickListener {
            val title   = binding.etTitle.text.toString().trim()
            val year    = binding.etYear.text.toString().toIntOrNull() ?: 0
            val genre   = binding.etGenre.text.toString().trim()
            val rating  = binding.etRating.text.toString().toFloatOrNull() ?: 0f
            val watched = binding.cbWatched.isChecked

            if (title.isEmpty()) {
                binding.etTitle.error = "El título es obligatorio"
                return@setOnClickListener
            }

            val movie = Movie(
                id = if (isEditing) movieId else 0,
                title = title, year = year,
                genre = genre, rating = rating, watched = watched
            )
            if (isEditing) viewModel.update(movie) else viewModel.insert(movie)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}