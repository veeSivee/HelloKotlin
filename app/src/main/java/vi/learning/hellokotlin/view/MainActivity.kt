package vi.learning.hellokotlin.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.view.footballclubfromapi.FootballclubFromApiActivity
import vi.learning.hellokotlin.view.footballclublist.FootballClubActivity
import vi.learning.hellokotlin.view.footballmatch.footballmatchschedule.FootballMatchScheduleActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                textView(){
                    text = "Taufiqotul Faidah (Vi) - Kotlin learning app"
                    textColor = ContextCompat.getColor(context, R.color.black)
                    textSize = 16f
                }

                imageView(R.drawable.fresh_idea).lparams(width = matchParent)
                        .lparams(height = dip(100)){
                    padding = dip(20)
                    margin = dip(15)
                }

                val name = editText() {
                    hint = "Hey, what's your name?"
                }

                button("Anko Feature") {
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    onClick {
                        startActivity<AnkoFeatureActivity>()
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                button("List Of Football Club") {
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    onClick {
                        startActivity<FootballClubActivity>()
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                button("Football Club from API") {
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    onClick {
                        startActivity<FootballclubFromApiActivity>()
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                button("Football Match (Submission)") {
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    onClick {
                        startActivity<FootballMatchScheduleActivity>()
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }
            }
        }

    }
}
