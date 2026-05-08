package com.meza.movielibrary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.meza.movielibrary.db.AppDatabase
import com.meza.movielibrary.model.Movie
import com.meza.movielibrary.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository
    val movies: LiveData<List<Movie>>

    private val _selectedMovie = MutableLiveData<Movie?>()
    val selectedMovie: LiveData<Movie?> = _selectedMovie

    init {
        val dao = AppDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(dao)
        movies = repository.getMovies()
    }

    fun loadMovie(id: Int) {
        repository.getMovie(id).observeForever { _selectedMovie.value = it }
    }

    fun insert(movie: Movie) = viewModelScope.launch { repository.insert(movie) }
    fun update(movie: Movie) = viewModelScope.launch { repository.update(movie) }
    fun delete(movie: Movie) = viewModelScope.launch { repository.delete(movie) }
}