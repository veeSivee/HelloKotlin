package vi.learning.hellokotlin.view.footballmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_football_match_schedule.*
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.view.footballclub.team.TeamFragment
import vi.learning.hellokotlin.view.footballmatch.favorite.FootballFavoriteFragment
import vi.learning.hellokotlin.view.footballmatch.schedule.FootballMatchScheduleFragment

class FootballMatchScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_match_schedule)
        initView(savedInstanceState)
    }

    private fun initView(savedInstanceState: Bundle?) {
        bottom_navigation_match.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.match -> {
                    loadMatchFragment(savedInstanceState)
                }
                R.id.teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                R.id.favorites_match -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation_match.selectedItemId = R.id.match
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.match_container, FootballMatchScheduleFragment(), FootballMatchScheduleFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.match_container, FootballFavoriteFragment(), FootballFavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.match_container, TeamFragment(), TeamFragment::class.java.simpleName)
                    .commit()
        }
    }
}