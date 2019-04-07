package ywxt.myswjtu.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.adapters.FragmentAdapter
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN_ME
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN_TIMETABLE

@Route(path = PATH_ROUTE_MAIN)
class MainActivity : BaseActivity() {

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
    }

    private val viewPager by lazy { findViewById<ViewPager>(R.id.main_viewPager) }

    private val router: ARouter by instance()
    

    private val fragmentList by lazy {
        listOf(
            router.build(PATH_ROUTE_MAIN_TIMETABLE).navigation() as Fragment,
            router.build(PATH_ROUTE_MAIN_ME).navigation() as Fragment
        )
    }

    private val navigation by lazy { findViewById<BottomNavigationView>(R.id.navigation) }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_timetable -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = FragmentAdapter(fragmentList, supportFragmentManager)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> navigation.selectedItemId = R.id.navigation_timetable
                    1 -> navigation.selectedItemId = R.id.navigation_me
                }
            }
        })
    }

  
}
