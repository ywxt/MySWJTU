package ywxt.myswjtu.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.app_bar_main.*
import ywxt.myswjtu.R
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN

@Route(path = PATH_ROUTE_MAIN)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }
}
