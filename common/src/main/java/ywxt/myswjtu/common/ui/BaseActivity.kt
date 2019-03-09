package ywxt.myswjtu.common.ui

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    protected val parentKodein by kodein()
}