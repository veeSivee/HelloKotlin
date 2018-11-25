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
import vi.learning.hellokotlin.view.footballmatch.FootballMatchScheduleView

/**
 * Created by taufiqotulfaidah on 11/22/18.
 */
class FootballMatchPresenterTest {
    @Mock
    private lateinit var view: FootballMatchScheduleView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter : FootballMatchPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = FootballMatchPresenter(view, apiRepository, gson)
    }

    @Test
    fun getNextMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val id = "3345"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatchSchedule(id)).await(),
                    EventResponse::class.java
            )).thenReturn(response)

            presenter.getNextMatchList(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getPastMatchList() {

        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val id = "3345"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPastMatchSchedule(id)).await(),
                    EventResponse::class.java
            )).thenReturn(response)

            presenter.getPastMatchList(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(events)
            Mockito.verify(view).hideLoading()
        }
    }

}