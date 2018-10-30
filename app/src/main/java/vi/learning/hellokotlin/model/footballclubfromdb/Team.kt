package vi.learning.hellokotlin.model.footballclubfromdb

import com.google.gson.annotations.SerializedName

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
data class Team (
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
)