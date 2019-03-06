package ywxt.myswjtu.ui.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.kodein.di.Kodein
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.BaseActivity

class ModuleActivity : BaseActivity() {
    override val kodein: Kodein
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module)
    }
}
