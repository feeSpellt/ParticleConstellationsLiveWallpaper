/*
 * Copyright (C) 2018 Yaroslav Mytkalyk
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
package com.doctoror.particleswallpaper.userprefs.bgscroll

import android.content.Context
import android.preference.CheckBoxPreference
import android.util.AttributeSet
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.doctoror.particleswallpaper.framework.di.inject
import org.koin.core.parameter.parametersOf

class BackgroundScrollPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @Suppress("UNUSED_PARAMETER") defStyle: Int = 0 // overridden to not be passed
) : CheckBoxPreference(context, attrs),
    BackgroundScrollPreferenceView,
    LifecycleObserver {

    private val presenter: BackgroundScrollPreferencePresenter by inject(
        context = context,
        parameters = { parametersOf(this as BackgroundScrollPreferenceView) }
    )

    init {
        setOnPreferenceChangeListener { _, v ->
            presenter.onPreferenceChange(v as Boolean)
            true
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        presenter.onStart()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        presenter.onStop()
    }
}
