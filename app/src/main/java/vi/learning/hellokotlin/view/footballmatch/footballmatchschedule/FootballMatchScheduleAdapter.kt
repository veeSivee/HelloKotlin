package vi.learning.hellokotlin.view.footballmatch.footballmatchschedule

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
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
        return EventViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)), listener)
    }
}

interface EventClickListener {
    fun OnClick (event: Event)
}

class EventUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                id = R.id.ll_event
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL

                textView {
                    id = R.id.event_time
                    textSize = 16f
                    textColor = Color.BLUE
                    gravity = Gravity.CENTER
                }.lparams{
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(15)
                }

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    bottomPadding = dip(16)

                    textView {
                        id = R.id.team_home
                        textSize = 16f
                    }.lparams{
                        leftMargin = dip(15)
                        rightMargin = dip(5)
                        bottomMargin = dip(10)
                        leftOf(R.id.team_home_score)
                        centerInParent()
                    }

                    textView {
                        id = R.id.team_home_score
                        textSize = 16f
                        setTypeface(null,Typeface.BOLD)
                    }.lparams{
                        leftMargin = dip(5)
                        rightMargin = dip(5)
                        bottomMargin = dip(10)
                        leftOf(R.id.tv_vs)
                        centerVertically()
                    }

                    textView {
                        id = R.id.tv_vs
                        textSize = 16f
                        text = "vs"
                    }.lparams{
                        leftMargin = dip(15)
                        rightMargin = dip (15)
                        bottomMargin = dip(10)
                        centerInParent()
                    }

                    textView {
                        id = R.id.team_away_score
                        textSize = 16f
                        setTypeface(null,Typeface.BOLD)
                    }.lparams{
                        leftMargin = dip(5)
                        rightMargin = dip(5)
                        bottomMargin = dip(10)
                        rightOf(R.id.tv_vs)
                        centerVertically()
                    }

                    textView {
                        id = R.id.team_away
                        textSize = 16f
                    }.lparams{
                        leftMargin = dip(5)
                        rightMargin = dip(15)
                        bottomMargin = dip(10)
                        rightOf(R.id.team_away_score)
                        centerInParent()
                    }

                }

                view {
                    backgroundColor = Color.GRAY
                }.lparams{
                    width = matchParent
                    height = dip(1)
                    leftMargin = dip(15)
                    rightMargin = dip(15)
                }
            }
        }
    }

}

class EventViewHolder(view: View, val listener: EventClickListener) : RecyclerView.ViewHolder(view) {

    private val eventTime: TextView = view.find(R.id.event_time)
    private val teamHome: TextView = view.find(R.id.team_home)
    private val teamHomeScore: TextView = view.find(R.id.team_home_score)
    private val teamAway: TextView = view.find(R.id.team_away)
    private val teamAwayScore: TextView = view.find(R.id.team_away_score)
    private val llEvent: LinearLayout = view.find(R.id.ll_event)

    fun bindItem(event: Event) {
        eventTime.text = event.getDisplayDate()
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