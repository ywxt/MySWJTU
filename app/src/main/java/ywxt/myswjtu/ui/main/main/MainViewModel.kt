package ywxt.myswjtu.ui.main.main

import android.app.Application
import org.kodein.di.Kodein
import ywxt.myswjtu.common.viewmodels.BaseViewModel

class MainViewModel(application: Application):BaseViewModel(application) {
    override val kodein: Kodein=parentKodein
    
}