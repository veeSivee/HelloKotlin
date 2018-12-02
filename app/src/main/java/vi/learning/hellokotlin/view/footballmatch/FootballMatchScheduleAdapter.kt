package vi.learning.hellokotlin.view.footballmatch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.find
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.model.footballmatch.Event

/**
 * Created by taufiqotulfaidah on 10/31/18.
 */
class FootballMatchScheduleAdapter (private val events: List<Event>,
                                    private val listener: EventClickListener)
    : RecyclerView.Adapter<EventViewHolder>() {

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position])
    }

    override fun getItemCount(): Int = events.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): EventViewHolder {
        val itemView : View = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_football_match, parent, false);
        return EventViewHolder(itemView, listener)
    }
}

interface EventClickListener {
    fun OnClick (event: Event)
}

class EventViewHolder(view: View, val listener: EventClickListener) : RecyclerView.ViewHolder(view) {

    private val eventTime: TextView = view.find(R.id.event_time)
    private val eventHour: TextView = view.find(R.id.event_hour)
    private val teamHome: TextView = view.find(R.id.team_home)
    private val teamHomeScore: TextView = view.find(R.id.team_home_score)
    private val teamAway: TextView = view.find(R.id.team_away)
    private val teamAwayScore: TextView = view.find(R.id.team_away_score)
    private val llEvent: LinearLayout = view.find(R.id.ll_event)

    fun bindItem(event: Event) {
        eventTime.text = event.getDisplayDate()
        eventHour.text = event.getDisplayHour()
        teamHome.text = event.homeTeam
        teamAway.text = event.awayTeam

        if (event.homeScore > 0 || event.awayScore > 0) {
            teamHomeScore.text = event.homeScore.toString()
            teamAwayScore.text = event.awayScore.toString()
        }

        llEvent.setOnClickListener {
            view -> listener.OnClick(event)
        }
    }
}