package vi.learning.hellokotlin.view.footballmatch.schedule

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.view.footballmatch.TeamAdapter

/**
 * Created by taufiqotulfaidah on 11/30/18.
 */
class FootballMatchScheduleFragment: Fragment() {

    private lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_football_match, container, false)
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
}