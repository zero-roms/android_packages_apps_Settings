/*
 * Copyright (C) 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.safetycenter.ui

import android.content.Context
import com.android.settings.R
import com.android.settings.flags.Flags
import com.android.settings.search.BaseSearchIndexProvider
import com.android.settingslib.search.SearchIndexable
import com.android.settingslib.search.SearchIndexableRaw

// LINT.IfChange
/** Fragment for displaying device unlock subpage within the Safety Center in Settings. */
@SearchIndexable
class DeviceUnlockSubpageFragment : SafetyCenterSubpageFragment() {

    override val subpageKey = SafetyCenterSubpageRegistry.DEVICE_UNLOCK_SUBPAGE_KEY

    override fun getLogTag(): String {
        return TAG
    }

    companion object {
        private const val TAG = "DeviceUnlockSubpage"
        // Key of the screen lock SafetySourcePreference in safety_center_device_unlock_subpage.xml
        private const val LOCK_SCREEN_KEY = "android_lock_screen"

        @JvmField
        val SEARCH_INDEX_DATA_PROVIDER: BaseSearchIndexProvider =
            object : BaseSearchIndexProvider(R.xml.safety_center_device_unlock_subpage) {
                override fun isPageSearchEnabled(context: Context?): Boolean {
                    return Flags.enableSafetyCenterNewUi()
                }

                // ZeroS: the screen lock entry as static data. Its title otherwise comes only
                // from live Safety Center data through the dynamic path below, which the search
                // index never reads, so "screen lock" and "password" found nothing.
                override fun getRawDataToIndex(
                    context: Context,
                    enabled: Boolean,
                ): List<SearchIndexableRaw> {
                    val rawData = super.getRawDataToIndex(context, enabled).toMutableList()
                    val raw = SearchIndexableRaw(context)
                    raw.key = LOCK_SCREEN_KEY
                    raw.title = context.getString(R.string.unlock_set_unlock_launch_picker_title)
                    raw.keywords =
                        context.getString(R.string.safety_center_android_lock_screen_search_terms)
                    raw.screenTitle = context.getString(R.string.device_unlock_subpage_title)
                    rawData.add(raw)
                    return rawData
                }

                override fun getDynamicRawDataToIndex(
                    context: Context,
                    enabled: Boolean,
                ): List<SearchIndexableRaw> {
                    val rawData = super.getDynamicRawDataToIndex(context, enabled).toMutableList()
                    rawData.addAll(
                        SafetyCenterSearchIndexUtils.getDynamicRawDataForIndexingSubpage(
                            context,
                            SafetyCenterSubpageRegistry.DEVICE_UNLOCK_SUBPAGE_KEY,
                        )
                    )
                    return rawData
                }
            }
    }
}
// LINT.ThenChange(DeviceUnlockApiScreen.kt)
