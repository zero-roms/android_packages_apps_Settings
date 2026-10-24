/*
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.settings.deviceinfo.firmwareversion

import android.content.Context
import android.os.SystemProperties
import androidx.preference.Preference
import com.android.settings.R
import com.android.settingslib.metadata.PreferenceMetadata
import com.android.settingslib.metadata.PreferenceSummaryProvider
import com.android.settingslib.preference.PreferenceBinding

/** ZeroS: the ZeroS release this phone runs, shown above LineageOS's version. */
// LINT.IfChange
class ZeroSVersionDetailPreference :
    PreferenceMetadata, PreferenceSummaryProvider, PreferenceBinding {

    override val key: String
        get() = "zeros_version"

    override val purpose: Int
        get() = R.string.os_firmware_version_purpose

    override val title: Int
        get() = R.string.zeros_version

    override fun bind(preference: Preference, metadata: PreferenceMetadata) {
        super.bind(preference, metadata)
        preference.isSelectable = false
        preference.isCopyingEnabled = true
    }

    override fun getSummary(context: Context): CharSequence? =
        SystemProperties.get(ZEROS_VERSION_PROPERTY, context.getString(R.string.unknown))

    companion object {
        const val ZEROS_VERSION_PROPERTY: String = "ro.zeros.version"
    }
}
// LINT.ThenChange(ZeroSVersionPreferenceController.java)
