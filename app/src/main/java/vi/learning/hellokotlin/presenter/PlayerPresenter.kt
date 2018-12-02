package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.data.TheSportDBApi
import vi.learning.hellokotlin.model.footballclub.PlayerResponse
import vi.learning.hellokotlin.view.footballclub.detail.PlayerView

/**
 * Created by taufiqotulfaidah on 12/1/18.
 */
class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson) {

    fun getTeamDetail(teamId: String?) {

        GlobalScope.launch(Dispatchers.Main) {

            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getAllPlayerByTeamId(teamId))
                    .await(),
                    PlayerResponse::class.java
            )
            if (data != null && data.player != null) {
                view.showPlayerList(data.player)
            }
        }
    }
}