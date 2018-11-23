package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.data.TheSportDBApi
import vi.learning.hellokotlin.model.footballclub.TeamResponse
import vi.learning.hellokotlin.view.footballclub.detail.TeamDetailView

/**
 * Created by taufiqotulfaidah on 11/4/18.
 */
class TeamDetailPresenter (private val view: TeamDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getTeamDetail(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {

            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId))
                    .await(),
                    TeamResponse::class.java
            )

            view.hideLoading()
            view.showTeamDetail(data.teams)
        }
    }
}