package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.data.TheSportDBApi
import vi.learning.hellokotlin.model.footballclub.TeamResponse
import vi.learning.hellokotlin.view.search.SearchTeamView

/**
 * Created by taufiqotulfaidah on 12/3/18.
 */
class SearchTeamPresenter (private val view: SearchTeamView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun searchTeam(keyword: String) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchteam(keyword))
                    .await(),
                    TeamResponse::class.java)
            view.hideLoading()
            if (data != null && data.teams != null) {
                view.showTeamList(data.teams)
            }
        }
    }
}