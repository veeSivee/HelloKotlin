package vi.learning.hellokotlin.view.footballclub.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_overview.view.*
import vi.learning.hellokotlin.R

class OverviewFragment : Fragment() {

    private var overview: String? = null
    private lateinit var tvOverview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            overview = arguments!!.getString(OVERVIEW)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        tvOverview = view.tv_overview
        tvOverview.text = overview
        return view
    }

    fun onUpdate(overview: String?) {
        tvOverview.text = overview
    }

    companion object {
        private val OVERVIEW = "overview"

        fun newInstance(overview: String): OverviewFragment {
            val fragment = OverviewFragment()
            val args = Bundle()
            args.putString(OVERVIEW, overview)
            fragment.arguments = args
            return fragment
        }
    }
}
