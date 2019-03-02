package ywxt.myswjtu.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ywxt.myswjtu.checkers.LoginChecker

const val CHECKER_MODULE_NAME="CHECKER_MODULE_NAME"
val checkerModule=Kodein.Module(CHECKER_MODULE_NAME){
    bind<LoginChecker>() with singleton {
        LoginChecker(instance())
    }
}