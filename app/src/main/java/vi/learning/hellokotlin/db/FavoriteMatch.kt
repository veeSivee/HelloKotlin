package vi.learning.hellokotlin.db

/**
 * Created by taufiqotulfaidah on 11/6/18.
 */
class FavoriteMatch (val id: Long?, val eventId: String?, val teamHome: String?, val teamHomeScore: String,
                     val teamAway: String?, val teamAwayScore: String, val matchDate: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val TEAM_HOME: String = "TEAM_HOME"
        const val TEAM_HOME_SCORE: String = "TEAM_HOME_SCORE"
        const val TEAM_AWAY: String = "TEAM_AWAY"
        const val TEAM_AWAY_SCORE: String = "TEAM_AWAY_SCORE"
        const val MATCH_DATE: String = "MATCH_DATE"
    }
}