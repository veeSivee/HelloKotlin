package vi.learning.hellokotlin.view.footballclub.detail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.model.footballclub.Player
import vi.learning.hellokotlin.presenter.PlayerPresenter

/**
 * Created by taufiqotulfaidah on 11/29/18.
 */
class PlayerFragment : Fragment(), PlayerView {

    private var columnCount = 1
    private lateinit var teamName : String
    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerRecyclerViewAdapter

    private var players: MutableList<Player> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            columnCount = arguments!!.getInt(COLUMN_COUNT)
            teamName = arguments!!.getString(TEAM_NAME)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_player_list, container, false)

        if (view is RecyclerView) {
            val context = view.getContext()
            if (columnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, columnCount)
            }
            adapter = PlayerRecyclerViewAdapter(players, listener)
            view.adapter = adapter
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)

        getTeamDetail()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            getTeamDetail()
        }
    }

    private fun getTeamDetail() {
        if (teamName.isNotEmpty()) {
            presenter.getTeamDetail(teamName)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun setTeamName(team: String?) {
        if (team != null) {
            teamName = team
        }
    }

    override fun showPlayerList(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Player)
    }

    companion object {

        private val COLUMN_COUNT = "column-count"
        private val TEAM_NAME = ConstantValue.TEAM_NAME

        fun newInstance(columnCount: Int, teamName: String): PlayerFragment {
            val fragment = PlayerFragment()
            val args = Bundle()
            args.putInt(COLUMN_COUNT, columnCount)
            args.putString(TEAM_NAME, teamName)
            fragment.arguments = args
            return fragment
        }
    }
}
