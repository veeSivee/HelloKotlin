package vi.learning.hellokotlin.view.footballclub.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*
import vi.learning.hellokotlin.ConstantValue
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.model.footballclub.Player

/**
 * Created by taufiqotulfaidah on 12/1/18.
 */
class PlayerDetailActivity : AppCompatActivity() {

    lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        player = intent.getParcelableExtra(ConstantValue.PLAYER)

        setTitle(player.strPlayer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setDataIntoView()
    }

    private fun setDataIntoView() {
        Picasso.get().load(player.strThumb).into(iv_player_photo)
        tv_player_weight.text = player.strWeight
        tv_player_height.text = player.strHeight
        tv_player_position.text = player.strPosition
        tv_player_description.text = player.strDescriptionEN
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
