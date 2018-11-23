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
import vi.learning.hellokotlin.model.footballclub.Team
import vi.learning.hellokotlin.model.footballclub.TeamResponse
import vi.learning.hellokotlin.view.footballclub.FootballClubApiView

/**
 * Created by taufiqotulfaidah on 11/22/18.
 */
class FootballClubApiPresenterTest {
    @Mock
    private lateinit var view: FootballClubApiView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: FootballClubApiPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = FootballClubApiPresenter(view , apiRepository, gson)
    }

    @Test
    fun getTeamList() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)).await(),
                    TeamResponse::class.java
            )).thenReturn(response)

            presenter.getTeamList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}