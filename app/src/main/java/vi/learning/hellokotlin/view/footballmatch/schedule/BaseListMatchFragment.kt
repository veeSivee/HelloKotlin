package vi.learning.hellokotlin.view.footballmatch.schedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_football_match_schedule.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.db.FavoriteMatch
import vi.learning.hellokotlin.model.footballclub.Team
import vi.learning.hellokotlin.model.footballmatch.Event
import vi.learning.hellokotlin.presenter.FootballMatchPresenter
import vi.learning.hellokotlin.view.footballmatch.EventClickListener
import vi.learning.hellokotlin.view.footballmatch.FootballMatchScheduleAdapter
import vi.learning.hellokotlin.view.footballmatch.FootballMatchScheduleView
import vi.learning.hellokotlin.view.footballmatch.detail.FootballMatchDetailActivity

/**
 * Created by taufiqotulfaidah on 11/3/18.
 */
abstract class BaseListMatchFragment : Fragment(), FootballMatchScheduleView, EventClickListener {

    private var events: MutableList<Event> = mutableListOf()
    protected lateinit var presenter: FootballMatchPresenter
    private lateinit var adapter: FootballMatchScheduleAdapter

    private lateinit var listTeam: RecyclerView
    private lateinit var pbListMatch: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    var leagueName = "English Premier League"
    var idLeagueSelected = "4328"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_football_match_schedule, container, false)
        listTeam = rootView.rv_list_match
        listTeam.layoutManager = LinearLayoutManager (rootView.context)
        pbListMatch = rootView.pb_list_match
        swipeRefresh = rootView.refresh_match
        spinner = rootView.spinner_match
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPresenter()
        swipeRefresh.onRefresh {
            getData()
        }

        if (spinner.visibility == View.GONE) {
            getData()
        }
    }

    override fun showLoading() {
        pbListMatch.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbListMatch.visibility = View.GONE
    }

    override fun showMatchList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showFavorites(favorites: List<FavoriteMatch>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        for (favorite: FavoriteMatch in favorites) {
            var event = Event(idEvent = favorite.eventId, homeTeam = favorite.teamHome,
                    homeScore = favorite.teamHomeScore.toInt(), awayTeam = favorite.teamAway,
                    awayScore = favorite.teamAwayScore.toInt(), eventDateDisplay = favorite.matchDate,
                    strTime = favorite.matchHour)
            events.add(event)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initPresenter()
            getData()
        }
    }

    private fun initSpinner() {
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter as SpinnerAdapter?

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()

                var leagues = resources.getStringArray(R.array.league)
                var idLeague : Array<String> = resources.getStringArray(R.array.id_league)

                var index : Int = leagues.indexOf(leagueName)
                idLeagueSelected = idLeague[index]

                getData()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun initPresenter() {
        adapter = FootballMatchScheduleAdapter(events, this)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = FootballMatchPresenter(this, request, gson)

        initSpinner()
    }

    override fun OnClick(event: Event) {
        val awayBadge : String? = presenter.getImageUrl(event.awayTeam)
        val homeBadge : String? = presenter.getImageUrl(event.homeTeam)
        startActivity<FootballMatchDetailActivity>(ConstantValue.ID_EVENT to event.idEvent,
                ConstantValue.AWAY_BADGE to awayBadge,
                ConstantValue.HOME_BADGE to homeBadge)
    }

    fun hideSpinner() {
        spinner.visibility = View.GONE
    }

    abstract fun getData()
}