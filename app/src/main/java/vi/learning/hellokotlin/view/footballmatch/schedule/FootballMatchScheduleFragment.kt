package vi.learning.hellokotlin.view.footballmatch.schedule

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.view.footballmatch.TeamAdapter
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.support.v7.widget.SearchView
import kotlinx.android.synthetic.main.fragment_football_match.view.*
import org.jetbrains.anko.support.v4.ctx
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.view.search.SearchMatchActivity


/**
 * Created by taufiqotulfaidah on 11/30/18.
 */
class FootballMatchScheduleFragment: Fragment() {

    private lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_football_match, container, false)
        (activity as AppCompatActivity).setSupportActionBar(rootView.toolbar)

        var viewPager : ViewPager = rootView.findViewById(R.id.match_viewpager);
        setupViewPager(viewPager)

        var tabs : TabLayout =rootView.findViewById(R.id.match_tabs)
        tabs.setupWithViewPager(viewPager)
        return rootView
    }

    private fun setupViewPager(viewPager: ViewPager) {
        adapter = TeamAdapter(childFragmentManager)
        adapter.addFragment(NextMatchFragment(), getString(R.string.tab_next))
        adapter.addFragment(LastMatchFragment(), getString(R.string.tab_last))

        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView?
        searchView?.queryHint = "Search matches"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyword: String?): Boolean {
                val intent = Intent(ctx, SearchMatchActivity::class.java)
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
}