package vi.learning.hellokotlin.view.footballclubfromdb

import vi.learning.hellokotlin.model.footballclubfromdb.Team

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
interface FootballClubDbView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}