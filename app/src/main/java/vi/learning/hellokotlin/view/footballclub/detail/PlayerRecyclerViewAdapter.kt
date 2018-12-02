package vi.learning.hellokotlin.view.footballclub.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.model.footballclub.Player
import vi.learning.hellokotlin.view.footballclub.detail.PlayerFragment.OnListFragmentInteractionListener

/**
 * Created by taufiqotulfaidah on 11/29/18.
 */
class PlayerRecyclerViewAdapter(private val players:List<Player>,
                                private val listener:OnListFragmentInteractionListener?)
    :RecyclerView.Adapter<PlayerRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_player, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position:Int) {
        holder.player = players.get(position)
        holder.playerName.setText(players.get(position).strPlayer)
        holder.playerPosition.setText(players.get(position).strPosition)
        Picasso.get().load(players.get(position).strThumb).into(holder.ivPlayer)

        holder.mView.setOnClickListener(object:View.OnClickListener {
            override fun onClick(v:View) {
                if (null != listener)
                {
                    listener!!.onListFragmentInteraction(holder.player!!)
                }
            }
        })
    }

    override fun getItemCount():Int {
        return players.size
    }

    inner class ViewHolder( val mView:View):RecyclerView.ViewHolder(mView) {
        val playerName:TextView = mView.findViewById(R.id.id)
        val playerPosition:TextView = mView.findViewById(R.id.content)
        val ivPlayer: ImageView = mView.findViewById(R.id.iv_player)
        var player:Player? = null

        override fun toString():String {
            return super.toString() + " '" + playerPosition.getText() + "'"
        }
    }
}
