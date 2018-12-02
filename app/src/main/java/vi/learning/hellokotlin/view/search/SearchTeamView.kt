package vi.learning.hellokotlin.view.search

import vi.learning.hellokotlin.model.footballclub.Team

/**
 * Created by taufiqotulfaidah on 12/3/18.
 */
interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}