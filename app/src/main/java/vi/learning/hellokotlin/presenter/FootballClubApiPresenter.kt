package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.data.TheSportDBApi
import vi.learning.hellokotlin.model.footballclub.TeamResponse
import vi.learning.hellokotlin.view.footballclub.FootballClubApiView

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
class FootballClubApiPresenter(private val view: FootballClubApiView,
                               private val apiRepository: ApiRepository,
                               private val gson: Gson) {
    fun getTeamList (league: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(league))
                    .await(),
                    TeamResponse::class.java)

            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }
}