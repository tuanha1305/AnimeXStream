package net.xblacky.animexstream.ui.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.android.synthetic.main.recycler_anime_common.*
import net.xblacky.animexstream.MainActivity
import net.xblacky.animexstream.R
import net.xblacky.animexstream.utils.preference.Preference
import net.xblacky.animexstream.utils.preference.PreferenceHelper

class Settings : Fragment(), View.OnClickListener {

    lateinit var rootView: View
    lateinit var sharesPreference: Preference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharesPreference = PreferenceHelper.sharedPreference
        setRadioButtons()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setRadioButtons() {
        rootView.nightModeRadioButton.isChecked = sharesPreference.getNightMode()
        rootView.nightModeRadioButton.setOnCheckedChangeListener { _, isChecked ->
            sharesPreference.setNightMode(isChecked)
            (activity as MainActivity).toggleDayNight()
        }

        rootView.pipRadioButton.isChecked = sharesPreference.getPIPMode()

        rootView.pipRadioButton.setOnCheckedChangeListener { _, isChecked ->
            sharesPreference.setPIPMode(isChecked)
        }
    }

    private fun toggleNightMode() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.nightModeRadioButton -> {
                toggleNightMode()
            }
        }
    }
}