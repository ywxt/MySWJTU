package ywxt.myswjtu.common.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(private val fragmentList: List<Fragment>, private val fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val ret=super.instantiateItem(container, position)
//        fragmentManager.beginTransaction().show(fragmentList[position]).commit()
//        return ret
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        fragmentManager.beginTransaction().hide(fragmentList[position]).commit()
//    }
}