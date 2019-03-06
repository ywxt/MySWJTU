package ywxt.myswjtu.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ywxt.myswjtu.managers.ToastManager
const val NOTIFICATION_MODULE_NAME="NOTIFICATION_MODULE_NAME"
val notificationModule = Kodein.Module(NOTIFICATION_MODULE_NAME) {
    bind<ToastManager>() with singleton { ToastManager(instance()) }
}