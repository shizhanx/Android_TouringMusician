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

import android.graphics.Point
import kotlin.math.pow
import kotlin.math.sqrt

class CircularLinkedList : Iterable<Point?> {
    inner class Node(p: Point, pre: Node?, n: Node?) {
        var point: Point = p
        var prev: Node? = pre
        var next: Node? = n
    }

    var head: Node? = null
    fun insertBeginning(p: Point) {
        if (head != null) {
            val node = Node(p, head!!.prev, head)
            head!!.prev?.next = node
            head!!.prev = node
            head = node
        } else {
            insertEmpty(p)
        }
    }

    private fun distanceBetween(from: Point, to: Point): Float {
        return sqrt((from.y - to.y.toDouble()).pow(2.0) + (from.x - to.x.toDouble()).pow(2.0)).toFloat()
    }

    fun totalDistance(): Float {
        var ans = 0f
        if (head == null || head!!.next == head) return ans
        if (head!!.next?.next == head) return distanceBetween(head!!.point, head!!.next!!.point)
        var current = head
        while(current!!.next != head){
            ans += distanceBetween(current.point, current.next!!.point)
            current = current.next
        }
        ans += distanceBetween(current.point, head!!.point)
        return ans
    }

    fun insertNearest(p: Point) {
        if(head == null) insertEmpty(p)
        else {
            var minDist = distanceBetween(p, head!!.point)
            var nearest = head
            var current = head
            while(current!!.next != head){
                current = current!!.next
                if(minDist > distanceBetween(current!!.point, p)) {
                    minDist = distanceBetween(current!!.point, p)
                    nearest = current
                }
            }
            val node = Node(p, nearest, nearest!!.next)
            nearest!!.next!!.prev = node
            nearest!!.next = node
        }
    }

    fun insertSmallest(p: Point) {
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
    }

    private fun insertEmpty(p: Point) {
        head = Node(p, null, null)
        head!!.prev = head
        head!!.next = head
    }

    fun reset() {
        head = null
    }

    private inner class CircularLinkedListIterator : MutableIterator<Point?> {
        var current: Node?
        override fun hasNext(): Boolean {
            return current != null
        }

        override fun next(): Point? {
            val toReturn = current!!.point
            current = current!!.next
            if (current === head) {
                current = null
            }
            return toReturn
        }

        override fun remove() {
            throw UnsupportedOperationException()
        }

        init {
            current = head
        }
    }

    override fun iterator(): MutableIterator<Point?> {
        return CircularLinkedListIterator()
    }
}