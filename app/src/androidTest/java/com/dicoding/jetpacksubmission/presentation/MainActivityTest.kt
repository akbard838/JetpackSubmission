package com.dicoding.jetpacksubmission.presentation.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.utils.ContextProvider
import com.dicoding.jetpacksubmission.utils.DummyData
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val movies = DummyData.getMovies()
    private val tvShows = DummyData.getTvShows()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.imgPosterDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.imgPosterDetail)).check(matches(withTagValue(CoreMatchers.equalTo(movies[0].poster))))
        onView(withId(R.id.imgPosterDetailBackground)).check(matches(isDisplayed()))
        onView(withId(R.id.imgPosterDetailBackground)).check(matches(withTagValue(CoreMatchers.equalTo(movies[0].poster))))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(movies[0].title)))
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(matches(withText(movies[0].year)))
        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText(movies[0].genre)))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(withText("${movies[0].rating}/10")))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(movies[0].overview)))
    }

    @Test
    fun loadTvShows() {
        onView(withText(ContextProvider.get().getString(R.string.label_tv_shows).toUpperCase()))
            .perform(ViewActions.click())
        onView(withId(R.id.rvTvShow))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShows.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(ContextProvider.get().getString(R.string.label_tv_shows).toUpperCase()))
            .perform(ViewActions.click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.imgPosterDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.imgPosterDetail)).check(matches(withTagValue(CoreMatchers.equalTo(tvShows[0].poster))))
        onView(withId(R.id.imgPosterDetailBackground)).check(matches(isDisplayed()))
        onView(withId(R.id.imgPosterDetailBackground)).check(matches(withTagValue(CoreMatchers.equalTo(tvShows[0].poster))))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(tvShows[0].title)))
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(matches(withText(tvShows[0].year)))
        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText(tvShows[0].genre)))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(withText("${tvShows[0].rating}/10")))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(tvShows[0].overview)))
    }
}