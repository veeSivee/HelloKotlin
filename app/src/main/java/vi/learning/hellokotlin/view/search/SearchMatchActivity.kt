package vi.learning.hellokotlin.view.search

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.model.footballmatch.Event
import vi.learning.hellokotlin.presenter.SearchMatchPresenter
import vi.learning.hellokotlin.view.footballmatch.EventClickListener
import vi.learning.hellokotlin.view.footballmatch.FootballMatchScheduleAdapter
import vi.learning.hellokotlin.view.footballmatch.detail.FootballMatchDetailActivity

class SearchMatchActivity : AppCompatActivity(), AnkoComponent<Context>, SearchMatchView , EventClickListener {

    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    lateinit var keyword: String
    lateinit var presenter: SearchMatchPresenter

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: FootballMatchScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this)
        initPresenter()
        getData()
    }

    private fun initPresenter() {
        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, request, gson)
    }

    private fun getData() {
        keyword = intent.getStringExtra(ConstantValue.KEYWORD)
        presenter.searchMatch(keyword)

        adapter = FootballMatchScheduleAdapter(events, this)
        listMatch.adapter = adapter
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showMatchList(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun OnClick(event: Event) {
        val awayBadge : String? = ""//presenter.getImageUrl(event.awayTeam)
        val homeBadge : String? = ""//presenter.getImageUrl(event.homeTeam)
        startActivity<FootballMatchDetailActivity>(ConstantValue.ID_EVENT to event.idEvent,
                ConstantValue.AWAY_BADGE to awayBadge,
                ConstantValue.HOME_BADGE to homeBadge)
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        swipeRefreshLayout {
            setColorSchemeResources(R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

            relativeLayout {
                lparams(width = matchParent, height = wrapContent)

                listMatch = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }

                progressBar = progressBar {}
                        .lparams { centerHorizontally() }
            }
        }
    }
}
