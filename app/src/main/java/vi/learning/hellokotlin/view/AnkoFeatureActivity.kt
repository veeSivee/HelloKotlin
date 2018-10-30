package vi.learning.hellokotlin.view

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk25.coroutines.onClick
import vi.learning.hellokotlin.R
import vi.learning.hellokotlin.view.submission1.DescriptionActivity

/**
 * Created by taufiqotulfaidah on 10/23/18.
 */

class AnkoFeatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AnkoFeatureActivityUI().setContentView(this)
    }

    class AnkoFeatureActivityUI : AnkoComponent<AnkoFeatureActivity> {
        override fun createView(ui: AnkoContext<AnkoFeatureActivity>) = with(ui) {
            verticalLayout{
                padding = dip(16)

                val name = editText {
                    hint = "What's your name?"
                }

                button("Say Hello"){
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE

                    onClick { toast("Hello, ${name.text}!") }

                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                button("Show Alert"){
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE

                    onClick {
                        alert("This is learning app!", "Hello, ${name.text}!") {
                            yesButton { toast("Oh…") }
                            noButton {}
                        }.show()
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                button("Show Selector"){
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE

                    onClick {
                        val club = listOf("Barcelona", "Real Madrid", "Bayern Munchen", "Liverpool")
                        selector("Hello, ${name.text}! What's football club do you love?", club) { _, i ->
                            toast("So you love ${club[i]}, right?")
                        }
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                button("Show Snackbar"){
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE

                    onClick {
                        snackbar(name, "Hello, ${name.text}!")

                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                button("Show Progress Bar"){
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE

                    onClick {
                        indeterminateProgressDialog("Hello, ${name.text}! Please wait...").show()
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }

                button("Go to Second Activity"){
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE

                    onClick {
                        startActivity<DescriptionActivity>("name" to "${name.text}",
                                "description" to "Sample intent. Open from Anko feature activity")
                    }
                }.lparams(width = matchParent){
                    topMargin = dip(5)
                }
            }
        }
    }
}