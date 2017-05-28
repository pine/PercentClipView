package moe.pine.percent_clip_view.example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle(R.string.app_title)
        this.setContentView(R.layout.activity_main)

        this.linear_layout_2.clipLeft = 0.20f
        this.frame_layout_2.clipTop = 0.50f
        this.relative_layout_2.clipBottom = 0.50f

    }
}
