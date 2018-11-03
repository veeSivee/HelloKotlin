package vi.learning.hellokotlin.view.footballmatch.detail

import vi.learning.hellokotlin.model.footballmatch.Event

/**
 * Created by taufiqotulfaidah on 11/2/18.
 */
interface FootballMatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(event: Event)
}