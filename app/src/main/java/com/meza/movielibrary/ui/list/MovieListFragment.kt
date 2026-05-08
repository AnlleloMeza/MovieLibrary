package com.meza.movielibrary.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.meza.movielibrary.R
import com.meza.movielibrary.databinding.FragmentMovieListBinding
import com.meza.movielibrary.viewmodel.MovieViewModel

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentMovieListBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MovieAdapter { movie ->
            val bundle = Bundle().apply { putInt("movieId", movie.id) }
            findNavController().navigate(R.id.action_list_to_detail, bundle)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            binding.tvEmpty.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_list_to_edit)
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}