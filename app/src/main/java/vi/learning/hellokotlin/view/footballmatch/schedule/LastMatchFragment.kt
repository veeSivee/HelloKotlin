package vi.learning.hellokotlin.view.footballmatch.schedule

/**
 * Created by taufiqotulfaidah on 10/31/18.
 */
class LastMatchFragment : BaseListMatchFragment() {

    override fun getData() {
        presenter.getTeamList(leagueName)
        presenter.getPastMatchList(idLeagueSelected)
    }
}