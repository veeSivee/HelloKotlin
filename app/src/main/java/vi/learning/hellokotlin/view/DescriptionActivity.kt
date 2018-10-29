package vi.learning.hellokotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import vi.learning.hellokotlin.R

/**
 * Created by taufiqotulfaidah on 10/23/18.
 */
class DescriptionActivity : AppCompatActivity() {

    private var name: String = ""
    private var description: String = ""
    private var imageRes: Int = 0
    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var image: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout{
            padding = dip(16)
            image = imageView().lparams(width = matchParent, height = dip(100))
            tvName = textView().lparams(width = wrapContent, height = wrapContent) {
                gravity = R.id.center
            }
            tvDescription = textView().lparams(width = wrapContent, height = wrapContent) {
                gravity = R.id.center
            }
        }
        setData()
    }

    private fun setData() {
        name = intent.getStringExtra("name")
        description = intent.getStringExtra("description")
        imageRes = intent.getIntExtra("image", 0)
        tvName.text = name
        tvDescription.text = description
        Glide.with(ctx).load(imageRes).into(image)
    }

}
