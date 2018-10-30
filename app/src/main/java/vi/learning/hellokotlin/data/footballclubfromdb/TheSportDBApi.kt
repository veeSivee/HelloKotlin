package vi.learning.hellokotlin.data.footballclubfromdb

import android.net.Uri
import vi.learning.hellokotlin.BuildConfig

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
object TheSportDBApi {

    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }
}