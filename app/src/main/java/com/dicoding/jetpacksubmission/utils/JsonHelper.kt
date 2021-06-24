package com.dicoding.jetpacksubmission.utils

import android.content.Context
import com.dicoding.jetpacksubmission.data.model.response.GenreItem
import com.dicoding.jetpacksubmission.data.model.response.MovieItem
import com.dicoding.jetpacksubmission.data.model.response.TvShowItem
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class JsonHelper(private val context: Context) {
    private fun parsingDataToString(data: String): String? {
        return try {
            val `is` = context.assets.open(data)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun getMovies(): List<MovieItem> {
        val movies = ArrayList<MovieItem>()
        try {
            val responseObject = JSONObject(parsingDataToString("movies.json"))
            val listArray = responseObject.getJSONArray("result")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val releaseDate = movie.getString("releaseDate")
                val posterPath = movie.getString("posterPath")
                val overview = movie.getString("overview")
                val voteAverage = movie.getDouble("voteAverage")

                val movieResponse = MovieItem(
                    id = id,
                    title = title,
                    releaseDate = releaseDate,
                    posterPath = posterPath,
                    overview = overview,
                    voteAverage = voteAverage
                )
                movies.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.localizedMessage
        }
        return movies
    }

    fun getMovieById(id: Int): MovieItem {
        val movieData = String.format("movie_%s.json", id)
        var movie: MovieItem? = null
        try {
            val result = parsingDataToString(movieData)
            if (result != null) {
                val response = JSONObject(result)

                val movieId = response.getInt("id")
                val title = response.getString("title")
                val releaseDate = response.getString("releaseDate")
                val posterPath = response.getString("posterPath")
                val overview = response.getString("overview")
                val voteAverage = response.getDouble("voteAverage")

                movie = MovieItem(
                    id = movieId,
                    title = title,
                    releaseDate = releaseDate,
                    posterPath = posterPath,
                    overview = overview,
                    voteAverage = voteAverage
                )
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movie as MovieItem
    }

    fun getTvShows():List<TvShowItem>{
        val tvShows = ArrayList<TvShowItem>()

        try {
            val responseObject = JSONObject(parsingDataToString("tvshows.json"))
            val listArray = responseObject.getJSONArray("result")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val id = tvShow.getInt("id")
                val name = tvShow.getString("name")
                val releaseDate = tvShow.getString("releaseDate")
                val posterPath = tvShow.getString("posterPath")
                val overview = tvShow.getString("overview")
                val voteAverage = tvShow.getDouble("voteAverage")

                val tvShowResponse = TvShowItem(
                    id = id,
                    name = name,
                    releaseDate = releaseDate,
                    posterPath = posterPath,
                    overview = overview,
                    voteAverage = voteAverage
                )

                tvShows.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return tvShows
    }

    fun getTvShowById(id: Int): TvShowItem {
        val tvShowData = String.format("tvshow_%s.json", id)
        var tvShow: TvShowItem? = null
        try {
            val result = parsingDataToString(tvShowData)
            if (result != null) {
                val response = JSONObject(result)

                val tvShowId = response.getInt("id")
                val name = response.getString("name")
                val releaseDate = response.getString("releaseDate")
                val posterPath = response.getString("posterPath")
                val overview = response.getString("overview")
                val voteAverage = response.getDouble("voteAverage")

                tvShow = TvShowItem(
                    id = tvShowId,
                    name = name,
                    releaseDate = releaseDate,
                    posterPath = posterPath,
                    overview = overview,
                    voteAverage = voteAverage
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return tvShow as TvShowItem
    }
}