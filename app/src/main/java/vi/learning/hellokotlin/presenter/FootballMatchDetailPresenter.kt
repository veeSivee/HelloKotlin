package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.data.TheSportDBApi
import vi.learning.hellokotlin.model.footballmatch.EventResponse
import vi.learning.hellokotlin.view.footballmatch.detail.FootballMatchDetailView

/**
 * Created by taufiqotulfaidah on 11/2/18.
 */
class FootballMatchDetailPresenter (private val view: FootballMatchDetailView,
                                    private val apiRepository: ApiRepository,
                                    private val gson: Gson){

    fun getEventDetail (id: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventDetail(id)).await(),
                    EventResponse::class.java)

            view.hideLoading()
            view.showMatchList(data.events[0])
        }
    }
}
