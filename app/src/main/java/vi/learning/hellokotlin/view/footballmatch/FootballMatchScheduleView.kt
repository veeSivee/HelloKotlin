package vi.learning.hellokotlin.view.footballmatch

import vi.learning.hellokotlin.db.FavoriteMatch
import vi.learning.hellokotlin.model.footballmatch.Event

/**
 * Created by taufiqotulfaidah on 10/31/18.
 */
interface FootballMatchScheduleView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)
    fun showFavorites(favorites: List<FavoriteMatch>)
}