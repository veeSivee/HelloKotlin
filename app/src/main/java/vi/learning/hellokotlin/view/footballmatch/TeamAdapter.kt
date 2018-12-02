package vi.learning.hellokotlin.view.footballmatch

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by taufiqotulfaidah on 11/30/18.
 */
class TeamAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private var fragmentList : MutableList<Fragment> = mutableListOf()
    private var fragmentTitleList: MutableList<String> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment : Fragment, title: String) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList.get(position)
    }
}