package ywxt.myswjtu.common.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.kodein

abstract class BaseViewModel(application: Application) : AndroidViewModel(application), KodeinAware {
    protected val parentKodein by kodein()
}