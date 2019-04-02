package ywxt.myswjtu.ui.main

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.alibaba.android.arouter.facade.annotation.Route
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.managers.TimetableConfiguration
import ywxt.myswjtu.modules.PATH_ROUTE_SETTING

@Route(path = PATH_ROUTE_SETTING)
class SettingFragment: PreferenceFragmentCompat() {
    val kodein by kodein()
    val configuration:TimetableConfiguration by kodein.instance()
    val showAllCourse by lazy<SwitchPreference> {
        findPreference("setting_timetable_showAllCourses")!!
    }
    val notify by lazy<SwitchPreference> { 
        findPreference("setting_time_notify")!!
    }
    val notifyTime by lazy<EditTextPreference> { 
        findPreference("setting_time_notify_time")!!
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preference_setting)
        showAllCourse.isChecked=configuration.showAllCourse.value!!
        notify.isChecked=configuration.notify.value!!
        notifyTime.text=configuration.notifyTime.value.toString()
        showAllCourse.setOnPreferenceChangeListener { _, newValue -> 
            configuration.showAllCourse.value = newValue as Boolean
            true
        }
        notify.setOnPreferenceChangeListener { _, newValue -> 
            configuration.notify.value= newValue as Boolean
            true
        }
        notifyTime.setOnPreferenceChangeListener { _, newValue -> 
            configuration.notifyTime.value=newValue as Int
            true
        } 
    }

  
}