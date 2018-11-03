package vi.learning.hellokotlin.view.footballmatch.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.model.footballmatch.Event
import vi.learning.hellokotlin.presenter.FootballMatchDetailPresenter

class FootballMatchDetailActivity : AppCompatActivity(), FootballMatchDetailView {

    private lateinit var presenter: FootballMatchDetailPresenter
    private lateinit var tvDate: TextView
    private lateinit var tvHomeScore: TextView
    private lateinit var tvAwayScore: TextView
    private lateinit var tvHomeTeamName: TextView
    private lateinit var tvAwayTeamName: TextView
    private lateinit var tvHomeGoal: TextView
    private lateinit var tvAwayGoal: TextView
    private lateinit var tvHomeShot: TextView
    private lateinit var tvAwayShot: TextView
    private lateinit var tvHomeGoalKeeper: TextView
    private lateinit var tvAwayGoalKeeper: TextView
    private lateinit var tvHomeDefense: TextView
    private lateinit var tvAwayDefense: TextView
    private lateinit var tvHomeMidfield: TextView
    private lateinit var tvAwayMidfield: TextView
    private lateinit var tvHomeForward: TextView
    private lateinit var tvAwayForward: TextView
    private lateinit var tvHomeSubstitutes: TextView
    private lateinit var tvAwaySubstitutes: TextView
    private lateinit var ivHomeTeam: ImageView
    private lateinit var ivAwayTeam: ImageView

    private lateinit var homeBadge: String
    private lateinit var awayBadge: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_match_detail)
        setTitle("Match Detail")
        initView()
        initPresenter()
        getData()
    }

    private fun initView() {
        tvDate = findViewById(R.id.tv_date_match)
        tvHomeScore = findViewById(R.id.tv_home_score)
        tvAwayScore = findViewById(R.id.tv_away_score)
        tvHomeTeamName = findViewById(R.id.tv_home_team_name)
        tvAwayTeamName = findViewById(R.id.tv_away_team_name)
        tvHomeGoal = findViewById(R.id.tv_home_goals)
        tvAwayGoal = findViewById(R.id.tv_away_goals)
        tvHomeShot = findViewById(R.id.tv_home_shots)
        tvAwayShot = findViewById(R.id.tv_away_shots)
        tvHomeGoalKeeper = findViewById(R.id.tv_home_goal_keeper)
        tvAwayGoalKeeper = findViewById(R.id.tv_away_goals_keeper)
        tvHomeDefense = findViewById(R.id.tv_home_defense)
        tvAwayDefense = findViewById(R.id.tv_away_defense)
        tvHomeMidfield = findViewById(R.id.tv_home_midfield)
        tvAwayMidfield = findViewById(R.id.tv_away_midfield)
        tvHomeForward = findViewById(R.id.tv_home_forward)
        tvAwayForward = findViewById(R.id.tv_away_forward)
        tvHomeSubstitutes = findViewById(R.id.tv_home_substitutes)
        tvAwaySubstitutes = findViewById(R.id.tv_away_substitutes)
        ivHomeTeam = findViewById(R.id.iv_home_team)
        ivAwayTeam = findViewById(R.id.iv_away_team)
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
    }

    override fun hideLoading() {
    }

    override fun showMatchList(event: Event) {
        tvDate.text = event.dateEvent
        if (event.homeScore > 0 || event.awayScore > 0) {
            tvHomeScore.text = event.homeScore.toString()
            tvAwayScore.text = event.awayScore.toString()
        }
        tvHomeTeamName.text = event.homeTeam
        tvAwayTeamName.text = event.awayTeam
        if (event.homeGoalDetails != null && event.awayGoalDetails != null ) {
            tvHomeGoal.text = event.homeGoalDetails.toString()
            tvAwayGoal.text = event.awayGoalDetails.toString()
        }
        tvHomeShot.text= event.homeShot
        tvAwayShot.text = event.awayShot
        tvHomeGoalKeeper.text = event.homeGoalKeeper
        tvAwayGoalKeeper.text = event.awayGoalKeeper
        tvHomeDefense.text = event.homeDefense
        tvAwayDefense.text = event.awayDefense
        tvHomeMidfield.text = event.homeMidfield
        tvAwayMidfield.text = event.awayMidfield
        tvHomeForward.text = event.homeForward
        tvAwayForward.text = event.awayForward
        tvHomeSubstitutes.text = event.homeSubstitutes
        tvAwaySubstitutes.text = event.awaySubstitutes

        if (homeBadge.isNotEmpty()) {
            Picasso.get().load(homeBadge).into(ivHomeTeam)
        }

        if (awayBadge.isNotEmpty()) {
            Picasso.get().load(awayBadge).into(ivAwayTeam)
        }
    }

}
