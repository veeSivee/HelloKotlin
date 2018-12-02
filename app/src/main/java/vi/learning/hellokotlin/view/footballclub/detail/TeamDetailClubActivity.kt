package vi.learning.hellokotlin.view.footballclub.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail_club.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.db.Favorite
import vi.learning.hellokotlin.db.database
import vi.learning.hellokotlin.model.footballclub.Player
import vi.learning.hellokotlin.model.footballclub.Team
import vi.learning.hellokotlin.presenter.TeamDetailPresenter
import vi.learning.hellokotlin.view.footballmatch.TeamAdapter

class TeamDetailClubActivity : AppCompatActivity(), PlayerFragment.OnListFragmentInteractionListener, TeamDetailView {

    private lateinit var adapter: TeamAdapter

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team
    private lateinit var id: String
    private lateinit var teamName: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var overviewFragment: OverviewFragment
    private lateinit var playerFragment: PlayerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail_club)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle("")
        id = intent.getStringExtra(ConstantValue.ID)
        teamName = intent.getStringExtra(ConstantValue.TEAM_NAME)

        setupViewPager(container)
        tabs.setupWithViewPager(container)

        initPresenter()
        getData()
        favoriteState()
    }

    private fun setupViewPager(viewPager: ViewPager) {
        overviewFragment = OverviewFragment.newInstance("")
        playerFragment = PlayerFragment.newInstance(3, teamName)
        adapter = TeamAdapter(supportFragmentManager)
        adapter.addFragment(overviewFragment, getString(R.string.tab_overview))
        adapter.addFragment(playerFragment, getString(R.string.tab_player))

        viewPager.adapter = adapter
    }

    private fun initPresenter() {
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
    }

    private fun getData() {
        presenter.getTeamDetail(id)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.TEAM_ID to teams.teamId,
                        Favorite.TEAM_NAME to teams.teamName,
                        Favorite.TEAM_BADGE to teams.teamBadge)
            }
            snackbar(main_content, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(main_content, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})", "id" to id)
            }
            snackbar(main_content, "Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(main_content, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun onListFragmentInteraction(player: Player) {
        startActivity<PlayerDetailActivity>( ConstantValue.PLAYER to player)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].teamId,
                data[0].teamName,
                data[0].teamBadge)

        Picasso.get().load(data[0].teamBadge).into(iv_team)
        tv_name.text = data[0].teamName
        tv_year.text = data[0].teamFormedYear
        tv_stadium.text = data[0].teamStadium

        overviewFragment.onUpdate(data[0].teamDescription)
        playerFragment.setTeamName(data[0].teamName)
    }
}
