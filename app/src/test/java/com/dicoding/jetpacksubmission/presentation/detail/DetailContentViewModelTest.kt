//package com.dicoding.jetpacksubmission.presentation.detail
//
//import com.dicoding.jetpacksubmission.utils.DummyData
//import com.dicoding.jetpacksubmission.utils.enum.ContentType
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertNotNull
//import org.junit.Before
//import org.junit.Test
//import com.google.common.math.DoubleMath.fuzzyEquals
//
//class DetailContentViewModelTest {
//
//    private lateinit var detailContentViewModel: DetailContentViewModel
//
//    private val movie = DummyData.getMovies()[0]
//    private val movieId = movie.id
//
//    private val tvShow = DummyData.getTvShows()[0]
//    private val tvShowId = tvShow.id
//
//    //Start Movie Testing
//    @Before
//    fun setUpMovie() {
//        detailContentViewModel = DetailContentViewModel()
//        detailContentViewModel.setSelectedContent(movieId, ContentType.MOVIE.type)
//    }
//
//    @Test
//    fun getMovieDetail() {
//        detailContentViewModel.setSelectedContent(movie.id, ContentType.MOVIE.type)
//        val movie = detailContentViewModel.getDetailContent()
//        assertNotNull(movie)
//        assertEquals(this.movie.id, movie.id)
//        assertEquals(this.movie.title, movie.title)
//        assertEquals(this.movie.poster, movie.poster)
//        assertEquals(this.movie.overview, movie.overview)
//        assertEquals(this.movie.year, movie.year)
//        fuzzyEquals(this.movie.rating, movie.rating, 0.0)
//        assertEquals(this.movie.genre, movie.genre)
//    }
//    //End Movie Testing
//
//    //Start TV Show Testing
//    @Before
//    fun setupTvShow() {
//        detailContentViewModel = DetailContentViewModel()
//        detailContentViewModel.setSelectedContent(tvShowId, ContentType.TV_SHOW.type)
//    }
//
//    @Test
//    fun getTvShowDetail() {
//        detailContentViewModel.setSelectedContent(tvShow.id, ContentType.TV_SHOW.type)
//        val tvShow = detailContentViewModel.getDetailContent()
//        assertNotNull(tvShow)
//        assertEquals(this.tvShow.id, tvShow.id)
//        assertEquals(this.tvShow.title, tvShow.title)
//        assertEquals(this.tvShow.poster, tvShow.poster)
//        assertEquals(this.tvShow.overview, tvShow.overview)
//        assertEquals(this.tvShow.year, tvShow.year)
//        fuzzyEquals(this.tvShow.rating, tvShow.rating, 0.0)
//        assertEquals(this.tvShow.genre, tvShow.genre)
//    }
//    //End Tv Show Testing
//}