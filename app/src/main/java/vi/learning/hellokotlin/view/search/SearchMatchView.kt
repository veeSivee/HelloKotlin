package vi.learning.hellokotlin.view.search

import vi.learning.hellokotlin.model.footballmatch.Event

/**
 * Created by taufiqotulfaidah on 11/27/18.
 */
interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)
}