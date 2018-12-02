package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.data.TheSportDBApi
import vi.learning.hellokotlin.model.search.SearchedMatchResponse
import vi.learning.hellokotlin.view.search.SearchMatchView

/**
 * Created by taufiqotulfaidah on 11/27/18.
 */
class SearchMatchPresenter (private val view: SearchMatchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun searchMatch(keyword: String) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchEvent(keyword))
                    .await(),
                    SearchedMatchResponse::class.java)

            view.hideLoading()
            if (data != null && data.event != null) {
                view.showMatchList(data.event)
            }
        }
    }
}