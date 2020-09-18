/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.engedu.touringmusician

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.PopupMenu
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var map: TourMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout = findViewById<View>(R.id.top_layout) as LinearLayout
        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 30.0f)
        map = TourMap(this)
        map!!.layoutParams = params
        layout.addView(map, 0)
        val modeButton = findViewById<View>(R.id.mode_selector) as Button
        modeButton.setOnClickListener {
            val popup = PopupMenu(this@MainActivity, modeButton)
            popup.menuInflater
                    .inflate(R.menu.modepopup, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                map!!.setInsertMode(item.title.toString())
                true
            }
            popup.show()
        }
    }

    fun onReset(v: View?) {
        map!!.reset()
        val message = findViewById<View>(R.id.game_status) as TextView
        message.text = "Tap map to add new tour stops."
    }
}