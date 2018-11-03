package vi.learning.hellokotlin.view.footballmatch.footballmatchschedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_football_match_schedule.view.*
import org.jetbrains.anko.support.v4.startActivity
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.model.footballmatch.Event
import vi.learning.hellokotlin.presenter.FootballMatchPresenter
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_football_match_schedule, container, false)
        listTeam = rootView.rv_list_match
        listTeam.layoutManager = LinearLayoutManager (rootView.context)
        pbListMatch = rootView.pb_list_match
        return rootView
    }

    override fun onResume() {
        super.onResume()
        initPresenter()
        getData()
    }

    override fun showLoading() {
        pbListMatch.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbListMatch.visibility = View.GONE
    }

    override fun showMatchList(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initPresenter()
            getData()
        }
    }

    private fun initPresenter() {
        adapter = FootballMatchScheduleAdapter(events, this)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = FootballMatchPresenter(this, request, gson)
    }

    override fun OnClick(event: Event) {
        val awayBadge : String? = presenter.getImageUrl(event.awayTeam)
        val homeBadge : String? = presenter.getImageUrl(event.homeTeam)
        startActivity<FootballMatchDetailActivity>(ConstantValue.ID_EVENT to event.idEvent,
                ConstantValue.AWAY_BADGE to awayBadge,
                ConstantValue.HOME_BADGE to homeBadge)
    }

    abstract fun getData()
}