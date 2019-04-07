package ywxt.myswjtu.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ywxt.myswjtu.managers.ConfigurationManager
import ywxt.myswjtu.managers.CookieManager
import ywxt.myswjtu.managers.StorageManager
import ywxt.myswjtu.managers.TimetableConfiguration

const val STORAGE_MODULE_NAME="STORAGE_MODULE_NAME"

val storageModule=Kodein.Module(STORAGE_MODULE_NAME){
    bind<StorageManager>() with singleton { StorageManager(instance()) }
    bind<ConfigurationManager>() with singleton { ConfigurationManager(instance()) }
    bind<TimetableConfiguration>() with singleton { TimetableConfiguration(instance()) }
    bind<CookieManager>() with singleton { CookieManager(instance(),instance()) }
}