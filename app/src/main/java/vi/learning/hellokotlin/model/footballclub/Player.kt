package vi.learning.hellokotlin.model.footballclub

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by taufiqotulfaidah on 12/1/18.
 */
@SuppressLint("ParcelCreator")
@Parcelize
class Player (
        var idPlayer: String? = null,
        var idTeam: String? = null,
        var idSoccerXML: String? = null,
        var idPlayerManager: String? = null,
        var strNationality: String? = null,
        var strPlayer: String? = null,
        var strTeam: String? = null,
        var strSport: String? = null,
        var intSoccerXMLTeamID: String? = null,
        var dateBorn: String? = null,
        var dateSigned: String? = null,
        var strSigning: String? = null,
        var strWage: String? = null,
        var strBirthLocation: String? = null,
        var strDescriptionEN: String? = null,
        var strGender: String? = null,
        var strPosition: String? = null,
        var strHeight: String? = null,
        var strWeight: String? = null,
        var strThumb: String? = null,
        var strCutout: String? = null,
        var strBanner: String? = null,
        var strFanart1: String? = null,
        var strFanart2: String? = null,
        var strFanart3: String? = null,
        var strFanart4: String? = null
): Parcelable