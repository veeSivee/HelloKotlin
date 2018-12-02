package vi.learning.hellokotlin.view.footballmatch.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_football_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.db.FavoriteMatch
import vi.learning.hellokotlin.db.database
import vi.learning.hellokotlin.model.footballmatch.Event
import vi.learning.hellokotlin.presenter.FootballMatchDetailPresenter

class FootballMatchDetailActivity : AppCompatActivity(), FootballMatchDetailView {

    private lateinit var presenter: FootballMatchDetailPresenter
    private lateinit var event: Event

    private lateinit var homeBadge: String
    private lateinit var awayBadge: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var idEvent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_match_detail)
        setTitle(getString(R.string.match_detail))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initPresenter()
        getData()
        favoriteState()
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

    private fun initPresenter() {
        val request = ApiRepository()
        val gson = Gson()
        presenter = FootballMatchDetailPresenter(this, request, gson)
    }

    private fun getData() {
        idEvent = intent.getStringExtra(ConstantValue.ID_EVENT)
        homeBadge = intent.getStringExtra(ConstantValue.HOME_BADGE)
        awayBadge = intent.getStringExtra(ConstantValue.AWAY_BADGE)
        presenter.getEventDetail(idEvent)
    }

    override fun showLoading() {
        pb_match_detail.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_match_detail.visibility = View.GONE
    }

    override fun showMatchList(event: Event) {
        this.event = event

        tv_date_match.text = event.getDisplayDate()
        tv_hour_match.text = event.getDisplayHour()
        if (event.homeScore > 0 || event.awayScore > 0) {
            tv_home_score.text = event.homeScore.toString()
            tv_away_score.text = event.awayScore.toString()
        }
        tv_home_team_name.text = event.homeTeam
        tv_away_team_name.text = event.awayTeam
        if (event.homeGoalDetails != null && event.awayGoalDetails != null ) {
            tv_home_goals.text = event.homeGoalDetails.toString()
            tv_away_goals.text = event.awayGoalDetails.toString()
        }
        tv_home_shots.text= event.homeShot
        tv_away_shots.text = event.awayShot
        tv_home_goal_keeper.text = event.homeGoalKeeper
        tv_away_goals_keeper.text = event.awayGoalKeeper
        tv_home_defense.text = event.homeDefense
        tv_away_defense.text = event.awayDefense
        tv_home_midfield.text = event.homeMidfield
        tv_away_midfield.text = event.awayMidfield
        tv_home_forward.text = event.homeForward
        tv_away_forward.text = event.awayForward
        tv_home_substitutes.text = event.homeSubstitutes
        tv_away_substitutes.text = event.awaySubstitutes

        if (homeBadge.isNotEmpty()) {
            Picasso.get().load(homeBadge).into(iv_home_team)
        }

        if (awayBadge.isNotEmpty()) {
            Picasso.get().load(awayBadge).into(iv_away_team)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.EVENT_ID to idEvent,
                        FavoriteMatch.TEAM_HOME to event.homeTeam,
                        FavoriteMatch.TEAM_HOME_SCORE to event.homeScore,
                        FavoriteMatch.TEAM_AWAY to event.awayTeam,
                        FavoriteMatch.TEAM_AWAY_SCORE to event.awayScore,
                        FavoriteMatch.MATCH_DATE to event.getDisplayDate(),
                        FavoriteMatch.MATCH_HOUR to event.strTime)
            }
            snackbar(ll_match, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(ll_match, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        "(EVENT_ID = {id})", "id" to idEvent)
            }
            snackbar(ll_match, "Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(ll_match, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
