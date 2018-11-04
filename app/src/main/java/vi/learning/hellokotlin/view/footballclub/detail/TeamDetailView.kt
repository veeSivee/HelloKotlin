package vi.learning.hellokotlin.view.footballclub.detail

import vi.learning.hellokotlin.model.footballclub.Team

/**
 * Created by taufiqotulfaidah on 11/4/18.
 */
interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}