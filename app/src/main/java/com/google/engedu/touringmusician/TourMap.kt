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

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

class TourMap(context: Context?) : View(context) {
    private val mapImage: Bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.map)
    private val list = CircularLinkedList()
    private var insertMode = "Add"
    private val pointPaint = Paint()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mapImage, 0f, 0f, null)
        pointPaint.color = Color.RED
        var last: Point? = null
        for (p in list) {
            if (p != null) {
                canvas.drawCircle(p.x.toFloat(), p.y.toFloat(), 10f, pointPaint)
                if (last != null) {
                    canvas.drawLine(p.x.toFloat(), p.y.toFloat(), last!!.x.toFloat(), last!!.y.toFloat(), pointPaint)
                }
                last = p
            }
        }
        val head = list.head?.point
        if (head != null) {
            canvas.drawLine(head.x.toFloat(), head.y.toFloat(), last!!.x.toFloat(), last!!.y.toFloat(), pointPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val p = Point(event.x.toInt(), event.y.toInt())
                when (insertMode) {
                    "Closest" -> {
                        list.insertNearest(p)
                    }
                    "Smallest" -> {
                        list.insertSmallest(p)
                    }
                    else -> {
                        list.insertBeginning(p)
                    }
                }
                val message = (context as Activity).findViewById<View>(R.id.game_status) as TextView
                message.text = String.format("Tour length is now %.2f", list.totalDistance())
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    fun reset() {
        list.reset()
        invalidate()
    }

    fun setInsertMode(mode: String) {
        insertMode = mode
    }

}