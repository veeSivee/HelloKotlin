package vi.learning.hellokotlin.data

import vi.learning.hellokotlin.BuildConfig

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
object TheSportDBApi {

    fun getTeams(league: String?): String {
        return getUncompleteUrl() + "/search_all_teams.php?l=" + league
    }

    fun getNextMatchSchedule(id: String?): String {
        return getUncompleteUrl() + "/eventsnextleague.php?id=" + id
    }

    fun getPastMatchSchedule(id: String?): String {
        return getUncompleteUrl() + "/eventspastleague.php?id=" + id
    }

    fun getEventDetail(id: String): String {
        return getUncompleteUrl() + "/lookupevent.php?id=" + id
    }

    fun getTeamDetail(teamId: String?): String{
        return getUncompleteUrl() + "/lookupteam.php?id=" + teamId
    }

    private fun getUncompleteUrl() : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}";
    }
}