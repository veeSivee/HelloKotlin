package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.data.TheSportDBApi
import vi.learning.hellokotlin.model.footballmatch.Event
import vi.learning.hellokotlin.model.footballmatch.EventResponse
import vi.learning.hellokotlin.view.footballmatch.detail.FootballMatchDetailView

/**
 * Created by taufiqotulfaidah on 11/22/18.
 */
class FootballMatchDetailPresenterTest {
    @Mock
    private lateinit var view: FootballMatchDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter : FootballMatchDetailPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = FootballMatchDetailPresenter(view, apiRepository, gson)
    }

    @Test
    fun getEventDetail() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val id = "3345"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getEventDetail(id)).await(),
                    EventResponse::class.java
            )).thenReturn(response)

            presenter.getEventDetail(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(events[0])
            Mockito.verify(view).hideLoading()
        }
    }
}