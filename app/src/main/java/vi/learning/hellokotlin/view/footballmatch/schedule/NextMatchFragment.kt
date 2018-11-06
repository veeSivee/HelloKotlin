package vi.learning.hellokotlin.view.footballmatch.schedule

import vi.learning.hellokotlin.R

/**
 * Created by taufiqotulfaidah on 10/31/18.
 */
class NextMatchFragment : BaseListMatchFragment() {

    override fun getData() {
        var league = resources.getStringArray(R.array.league)
        var idLeague : Array<String> = resources.getStringArray(R.array.id_league)
        presenter.getTeamList("4328", league, idLeague)
        presenter.getNextMatchList("4328")
    }
}