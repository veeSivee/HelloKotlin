package vi.learning.hellokotlin.presenter

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import vi.learning.hellokotlin.data.ApiRepository
import vi.learning.hellokotlin.data.TheSportDBApi
import vi.learning.hellokotlin.db.FavoriteMatch
import vi.learning.hellokotlin.db.database
import vi.learning.hellokotlin.model.footballclub.Team
import vi.learning.hellokotlin.model.footballclub.TeamResponse
import vi.learning.hellokotlin.model.footballmatch.EventResponse
import vi.learning.hellokotlin.view.footballmatch.FootballMatchScheduleView

/**
 * Created by taufiqotulfaidah on 10/31/18.
 */
class FootballMatchPresenter (private val view: FootballMatchScheduleView,
                              private val apiRepository: ApiRepository,
                              private val gson: Gson){

    private var data: List<Team> = ArrayList()

    fun getNextMatchList(id: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatchSchedule(id)).await(),
                    EventResponse::class.java)

            view.hideLoading()
            view.showMatchList(data.events)
        }
    }

    fun getPastMatchList(id: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPastMatchSchedule(id)).await(),
                    EventResponse::class.java)

            view.hideLoading()
            view.showMatchList(data.events)
        }
    }

    fun getTeamList (id: String?, strLeague: Array<String>, idLeague: Array<String>) {
        if (data.size > 0) return

        var index : Int = idLeague.indexOf(id)
        var league = strLeague[index]

        GlobalScope.launch(Dispatchers.Main) {
            val dataTeam = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(league)).await(),
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

    fun getFavoriteList(context: Context?) {
        view.showLoading()
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            view.showFavorites(favorite)
        }
        view.hideLoading()
    }
}