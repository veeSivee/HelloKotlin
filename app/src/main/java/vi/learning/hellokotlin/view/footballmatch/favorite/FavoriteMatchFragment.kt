package vi.learning.hellokotlin.view.footballmatch.favorite

import android.os.Bundle
import org.jetbrains.anko.support.v4.ctx
import vi.learning.hellokotlin.view.footballmatch.schedule.BaseListMatchFragment

/**
 * Created by taufiqotulfaidah on 11/6/18.
 */
class FavoriteMatchFragment : BaseListMatchFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        hideSpinner()
        super.onActivityCreated(savedInstanceState)
    }
    override fun getData() {
        presenter.getFavoriteList(ctx)
    }
}