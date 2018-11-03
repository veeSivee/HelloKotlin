package vi.learning.hellokotlin.view.footballmatch.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_football_match_detail.*
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.model.footballmatch.Event
import vi.learning.hellokotlin.presenter.FootballMatchDetailPresenter

class FootballMatchDetailActivity : AppCompatActivity(), FootballMatchDetailView {

    private lateinit var presenter: FootballMatchDetailPresenter

    private lateinit var homeBadge: String
    private lateinit var awayBadge: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_match_detail)
        setTitle(getString(R.string.match_detail))
        initPresenter()
        getData()
    }

    private fun initPresenter() {
        val request = ApiRepository()
        val gson = Gson()
        presenter = FootballMatchDetailPresenter(this, request, gson)
    }

    private fun getData() {
        var idEvent :String = intent.getStringExtra(ConstantValue.ID_EVENT)
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
        tv_date_match.text = event.getDisplayDate()
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

}
