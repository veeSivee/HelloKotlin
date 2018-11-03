package vi.learning.hellokotlin.view.footballclubfromapi

import vi.learning.hellokotlin.model.footballclubfromapi.Team

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
interface FootballClubApiView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}