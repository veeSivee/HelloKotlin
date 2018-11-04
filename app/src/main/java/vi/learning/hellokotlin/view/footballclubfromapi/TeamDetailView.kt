package vi.learning.hellokotlin.view.footballclubfromapi

import vi.learning.hellokotlin.model.footballclubfromapi.Team

/**
 * Created by taufiqotulfaidah on 11/4/18.
 */
interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}