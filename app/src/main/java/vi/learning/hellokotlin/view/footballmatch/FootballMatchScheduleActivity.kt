package vi.learning.hellokotlin.view.footballmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_football_match_schedule.*
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.view.footballmatch.favorite.FavoriteMatchFragment
import vi.learning.hellokotlin.view.footballmatch.schedule.LastMatchFragment
import vi.learning.hellokotlin.view.footballmatch.schedule.NextMatchFragment

class FootballMatchScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_match_schedule)
        initView(savedInstanceState)
    }


    private fun initView(savedInstanceState: Bundle?) {
        bottom_navigation_match.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.prev_match -> {
                    loadLastMatchFragment(savedInstanceState)
                }
                R.id.next_match -> {
                    loadNextMatchFragment(savedInstanceState)
                }
                R.id.favorites_match -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation_match.selectedItemId = R.id.prev_match
    }

    private fun loadLastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.match_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.match_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.match_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
}