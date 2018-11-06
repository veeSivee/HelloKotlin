package vi.learning.hellokotlin.view.footballmatch.favorite

import org.jetbrains.anko.support.v4.ctx
import vi.learning.hellokotlin.view.footballmatch.schedule.BaseListMatchFragment

/**
 * Created by taufiqotulfaidah on 11/6/18.
 */
class FavoriteMatchFragment : BaseListMatchFragment() {

    override fun getData() {
        presenter.getFavoriteList(ctx)
    }
}