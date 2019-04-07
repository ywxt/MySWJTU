package ywxt.myswjtu.ui.main.setting

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.alibaba.android.arouter.facade.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.managers.TimetableConfiguration
import ywxt.myswjtu.modules.PATH_ROUTE_ME_SETTING

@Route(path = PATH_ROUTE_ME_SETTING)
class SettingFragment : PreferenceFragmentCompat(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        val parentKodein = (parentFragment!! as KodeinAware).kodein
        extend(parentKodein)
    }
    private val configuration: TimetableConfiguration by instance()
    private val showAllCourse by lazy<SwitchPreference> {
        findPreference("setting_timetable_showAllCourses")!!
    }
    private val notify by lazy<SwitchPreference> {
        findPreference("setting_time_notify")!!
    }
    private val notifyTime by lazy<EditTextPreference> {
        findPreference("setting_time_notify_time")!!
    }
    private val showWeekend by lazy<SwitchPreference> {
        findPreference("setting_timetable_showWeekend")!!
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preference_setting)

    }


    override fun onStart() {
        super.onStart()
        configuration.showAllCourse.observe(this, Observer {
            showAllCourse.isChecked = it
        })
        configuration.notify.observe(this, Observer {
            notify.isChecked = it
        })
        configuration.notifyTime.observe(this, Observer {
            notifyTime.text = it.toString()
        })
        configuration.showWeekend.observe(this, Observer {
            showWeekend.isChecked = it
        })
        showAllCourse.setOnPreferenceChangeListener { _, newValue ->
            configuration.showAllCourse.value = newValue as Boolean
            true
        }
        notify.setOnPreferenceChangeListener { _, newValue ->
            configuration.notify.value = newValue as Boolean
            true
        }
        notifyTime.setOnPreferenceChangeListener { _, newValue ->
            configuration.notifyTime.value = newValue as Int
            true
        }
        showWeekend.setOnPreferenceChangeListener { _, newValue ->
            configuration.showWeekend.value = newValue as Boolean
            true
        }
    }

}