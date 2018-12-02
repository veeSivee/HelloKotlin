package vi.learning.hellokotlin.view.footballclub.team

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_football_match.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.model.footballclub.Team
import vi.learning.hellokotlin.presenter.FootballClubApiPresenter
import vi.learning.hellokotlin.view.footballclub.FootballClubApiView
import vi.learning.hellokotlin.view.footballclub.FootballclubApiAdapter
import vi.learning.hellokotlin.view.footballclub.detail.TeamDetailClubActivity
import vi.learning.hellokotlin.view.search.SearchTeamActivity

/**
 * Created by taufiqotulfaidah on 11/4/18.
 */
class TeamFragment : Fragment(), FootballClubApiView {//, AnkoComponent<Context>

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: FootballClubApiPresenter
    private lateinit var adapter: FootballclubApiAdapter
    private lateinit var leagueName: String

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_team, container, false)
        (activity as AppCompatActivity).setSupportActionBar(rootView.toolbar)
        initView(rootView)
        return rootView
    }

    private fun initView(view: View) {
        progressBar = view.findViewById(R.id.pb_team)
        swipeRefresh = view.findViewById(R.id.swipe_team)
        spinner = view.findViewById(R.id.sp_team)
        listTeam = view.findViewById(R.id.rv_team)
        listTeam.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView?
        searchView?.queryHint = "Search teams"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyword: String?): Boolean {
                val intent = Intent(ctx, SearchTeamActivity::class.java)
                intent.putExtra(ConstantValue.KEYWORD, keyword)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter as SpinnerAdapter?

        initAdapter()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    private fun initAdapter() {
        adapter = FootballclubApiAdapter(teams) {
            ctx.startActivity<TeamDetailClubActivity>("id" to "${it.teamId}", "teamName" to it.teamName)
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = FootballClubApiPresenter(this, request, gson)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}