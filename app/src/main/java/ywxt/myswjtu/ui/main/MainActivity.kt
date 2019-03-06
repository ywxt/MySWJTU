package ywxt.myswjtu.ui.main

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.kodein.di.Kodein
import ywxt.myswjtu.R
import ywxt.myswjtu.adapters.FragmentAdapter
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.ui.main.main.MainFragment
import ywxt.myswjtu.ui.main.notification.NotificationFragment
import ywxt.myswjtu.ui.main.setting.SettingFragment
import ywxt.myswjtu.ui.main.timetable.TimetableFragment

class MainActivity : BaseActivity() {
    
    override val kodein: Kodein = parentKodein

    private val viewPager by lazy { findViewById<ViewPager>(R.id.main_viewPager) }

    private val fragmentList = listOf(
        MainFragment(),
        TimetableFragment(),
        NotificationFragment(),
        SettingFragment()
    )
    
    private val navigation by lazy { findViewById<BottomNavigationView>(R.id.navigation) }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_timetable -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_setting -> {
                viewPager.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter=FragmentAdapter(fragmentList,supportFragmentManager)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> navigation.selectedItemId = R.id.navigation_home
                    1 -> navigation.selectedItemId = R.id.navigation_timetable
                    2 -> navigation.selectedItemId = R.id.navigation_notifications
                    3 -> navigation.selectedItemId = R.id.navigation_setting
                }
            }

        })
    }
}
