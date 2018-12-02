package vi.learning.hellokotlin.view.search

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.model.footballclub.Team
import vi.learning.hellokotlin.presenter.SearchTeamPresenter
import vi.learning.hellokotlin.view.footballclub.FootballclubApiAdapter
import vi.learning.hellokotlin.view.footballclub.detail.TeamDetailClubActivity

class SearchTeamActivity : AppCompatActivity(), AnkoComponent<Context> , SearchTeamView {

    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var keyword: String
    private lateinit var presenter: SearchTeamPresenter

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: FootballclubApiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(this)
        initPresenter()
        getData()
    }

    private fun initPresenter() {
        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchTeamPresenter(this, request, gson)
    }

    private fun getData() {
        keyword = intent.getStringExtra(ConstantValue.KEYWORD)

        adapter = FootballclubApiAdapter(teams){
            ctx.startActivity<TeamDetailClubActivity>(ConstantValue.ID to it.teamId,
                    ConstantValue.TEAM_NAME to it.teamName)
        }
        listMatch.adapter = adapter

        if (keyword.isNotBlank()) {
            presenter.searchTeam(keyword)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView?
        searchView?.queryHint = "Search teams"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(key: String): Boolean {
                keyword = key
                presenter.searchTeam(key)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
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
