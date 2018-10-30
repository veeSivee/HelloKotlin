package vi.learning.hellokotlin.view.footballclubfromdb

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import vi.learning.hellokotlin.presenter.FootballClubDbPresenter
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.footballclubfromdb.ApiRepository
import vi.learning.hellokotlin.model.footballclubfromdb.Team

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
class FootballclubFromDbActivity : AppCompatActivity(), FootballClubDbView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: FootballClubDbPresenter
    private lateinit var adapter: FootballclubDbAdapter
    private lateinit var leagueName: String

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "List of Football From DB"

        initLayout()
    }

    private fun initLayout() {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip (16)
            rightPadding = dip (16)

            spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {}
                            .lparams{centerHorizontally()}
                }

            }
        }

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
        adapter = FootballclubDbAdapter(teams)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = FootballClubDbPresenter(this, request, gson)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}