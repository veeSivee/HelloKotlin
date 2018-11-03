package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
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
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventDetail(id)),
                    EventResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events[0])
            }
        }
    }
}