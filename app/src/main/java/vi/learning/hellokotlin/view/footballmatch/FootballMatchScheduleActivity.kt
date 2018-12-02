package vi.learning.hellokotlin.view.footballmatch

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_football_match_schedule.*
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.view.footballclub.team.TeamFragment
import vi.learning.hellokotlin.view.footballmatch.favorite.FootballFavoriteFragment
import vi.learning.hellokotlin.view.footballmatch.schedule.FootballMatchScheduleFragment
import vi.learning.hellokotlin.view.search.SearchMatchActivity

class FootballMatchScheduleActivity : AppCompatActivity() {

    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_match_schedule)
        initView(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        menuItem = menu

        val searchView = menuItem?.findItem(R.id.search)?.actionView as SearchView?
        searchView?.queryHint = "Search matches"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyword: String?): Boolean {
                val intent = Intent(baseContext, SearchMatchActivity::class.java)
                intent.putExtra(ConstantValue.KEYWORD, keyword)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.search -> {

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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