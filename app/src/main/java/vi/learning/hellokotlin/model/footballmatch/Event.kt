package vi.learning.hellokotlin.model.footballmatch

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

/**
 * Created by taufiqotulfaidah on 10/31/18.
 */
class Event (
        @SerializedName("idEvent")
        var idEvent: String? = null,

        var dateEvent: String? = null,

        @SerializedName("strEvent")
        var eventMatch: String? = null,

        @SerializedName("strHomeTeam")
        var  homeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: Int = 0,

        @SerializedName("intAwayScore")
        var awayScore: Int = 0,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String? = null,

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String? = null,

        @SerializedName("intHomeShots")
        var homeShot: String? = null,

        @SerializedName("intAwayShots")
        var awayShot: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeGoalKeeper: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var awayGoalKeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var homeDefense: String? = null,

        @SerializedName("strAwayLineupDefense")
        var awayDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var homeMidfield: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var awayMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var homeForward: String? = null,

        @SerializedName("strAwayLineupForward")
        var awayForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var homeSubstitutes: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var awaySubstitutes: String? = null) {

    fun getDisplayDate() : String {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(dateEvent)
        val format = SimpleDateFormat("EEE, dd MMM yyyy")
        return format.format(date)
    }
}