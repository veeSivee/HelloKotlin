package vi.learning.hellokotlin.view.footballclub.detail

import vi.learning.hellokotlin.model.footballclub.Player

/**
 * Created by taufiqotulfaidah on 12/1/18.
 */
interface PlayerView {
    fun showPlayerList(data: List<Player>)
}