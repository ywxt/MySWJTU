package ywxt.myswjtu.ui.main.me

import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import ywxt.myswjtu.managers.ConfigurationManager
import ywxt.myswjtu.managers.UserManager
import ywxt.myswjtu.models.UserModel

class MeDataSource(
    private val userManager: UserManager,
    private val configurationManager: ConfigurationManager
) {
    fun getLocalUser(): Flowable<UserModel> = configurationManager.getUser()
        .onErrorResumeNext(Function { 
            Flowable.just(UserModel(
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            ))
        })


    fun getRemoteUser(): Flowable<UserModel> =
        userManager.getUser().observeOn(Schedulers.io()).doOnNext { configurationManager.writeUser(it) }

}