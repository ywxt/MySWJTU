package ywxt.myswjtu.common.viewmodels

import androidx.lifecycle.ViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind

abstract class BaseViewModel : ViewModel()

inline fun <reified T : BaseViewModel> Kodein.Builder.bindViewModel(overrides: Boolean? = null): Kodein.Builder.TypeBinder<T> {
    return bind<T>(T::class.java.name, overrides)
}