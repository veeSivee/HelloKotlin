package vi.learning.hellokotlin.presenter

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.data.TheSportDBApi
import vi.learning.hellokotlin.model.footballclubfromapi.Team
import vi.learning.hellokotlin.model.footballclubfromapi.TeamResponse
import vi.learning.hellokotlin.model.footballmatch.EventResponse
import vi.learning.hellokotlin.view.footballmatch.footballmatchschedule.FootballMatchScheduleView

/**
 * Created by taufiqotulfaidah on 10/31/18.
 */
class FootballMatchPresenter (private val view: FootballMatchScheduleView,
                              private val apiRepository: ApiRepository,
                              private val gson: Gson){

    private var data: List<Team> = ArrayList()

    fun getNextMatchList(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatchSchedule(id)),
                    EventResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

    fun getPastMatchList(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPastMatchSchedule(id)),
                    EventResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

    fun getTeamList (id: String?, strLeague: Array<String>, idLeague: Array<String>) {
        if (data.size > 0) return

        var index : Int = idLeague.indexOf(id)
        var league = strLeague[index]

        doAsync {
            val dataTeam = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java)
            data = dataTeam.teams
        }
    }

    fun getImageUrl (teamName: String?) : String? {

        for (team : Team in data) {
            if (team.teamName.equals(teamName, true)) {
                return team.teamBadge
            }
        }
        return ""
    }
}