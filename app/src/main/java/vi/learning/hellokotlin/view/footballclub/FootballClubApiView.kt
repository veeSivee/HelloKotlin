package vi.learning.hellokotlin.view.footballclub

import vi.learning.hellokotlin.model.footballclub.Team

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
interface FootballClubApiView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}