package vi.learning.hellokotlin.view.submission1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import vi.learning.hellokotlin.model.Item
import vi.learning.hellokotlin.R

/**
 * Created by taufiqotulfaidah on 10/23/18.
 */
class FootballClubActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        val adapter = RecyclerViewAdapter(this, items) {
            startActivity<DescriptionActivity>("name" to it.name.toString(),
                    "image" to it.image,
                    "description" to it.description)
        }

        FootBallClubActivityUI(adapter).setContentView(this)
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val description = resources.getStringArray(R.array.club_description)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i], image.getResourceId(i, 0), description[i]))
        }
        image.recycle()
    }

    class FootBallClubActivityUI (val listAdapter: RecyclerViewAdapter): AnkoComponent<FootballClubActivity> {
        override fun createView(ui: AnkoContext<FootballClubActivity>) = with(ui) {
            verticalLayout {
                recyclerView{
                    lparams (width = matchParent, height = wrapContent) {
                        layoutManager = LinearLayoutManager(ctx)
                        adapter = listAdapter
                    }
                }
            }
        }

    }
}