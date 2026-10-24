/*
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import com.android.settings.core.BasePreferenceController;

/** ZeroS: the ZeroS release this phone runs, shown in About phone above LineageOS's version. */
// LINT.IfChange
public class ZeroSVersionPreferenceController extends BasePreferenceController {

    static final String ZEROS_VERSION_PROPERTY = "ro.zeros.version";

    public ZeroSVersionPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        return TextUtils.isEmpty(SystemProperties.get(ZEROS_VERSION_PROPERTY))
                ? UNSUPPORTED_ON_DEVICE : AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        return SystemProperties.get(ZEROS_VERSION_PROPERTY);
    }
}
// LINT.ThenChange(ZeroSVersionDetailPreference.kt)
