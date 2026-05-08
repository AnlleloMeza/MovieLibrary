package com.meza.movielibrary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.meza.movielibrary.db.MovieDao
import com.meza.movielibrary.db.toEntity
import com.meza.movielibrary.db.toMovie
import com.meza.movielibrary.model.Movie

class MovieRepository(private val dao: MovieDao) {

    fun getMovies(): LiveData<List<Movie>> =
        dao.getAllMovies().map { list -> list.map { it.toMovie() } }

    fun getMovie(id: Int): LiveData<Movie?> =
        dao.getMovieById(id).map { it?.toMovie() }

    suspend fun insert(movie: Movie) = dao.insert(movie.toEntity())
    suspend fun update(movie: Movie) = dao.update(movie.toEntity())
    suspend fun delete(movie: Movie) = dao.delete(movie.toEntity())
}