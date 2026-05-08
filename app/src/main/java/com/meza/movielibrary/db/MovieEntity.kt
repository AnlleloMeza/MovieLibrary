package com.meza.movielibrary.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.meza.movielibrary.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val year: Int,
    val genre: String,
    val rating: Float,
    val watched: Boolean
)

fun MovieEntity.toMovie() = Movie(id, title, year, genre, rating, watched)
fun Movie.toEntity() = MovieEntity(id, title, year, genre, rating, watched)