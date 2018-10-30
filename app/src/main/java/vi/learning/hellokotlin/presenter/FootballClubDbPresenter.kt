package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vi.learning.hellokotlin.data.footballclubfromdb.ApiRepository
import vi.learning.hellokotlin.data.footballclubfromdb.TheSportDBApi
import vi.learning.hellokotlin.model.footballclubfromdb.TeamResponse
import vi.learning.hellokotlin.view.footballclubfromdb.FootballClubDbView

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
class FootballClubDbPresenter (private val view: FootballClubDbView,
                               private val apiRepository: ApiRepository,
                               private val gson: Gson) {
    fun getTeamList (league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}