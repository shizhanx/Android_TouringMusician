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

class CircularLinkedList : Iterable<Point?> {
    inner class Node {
        var point: Point? = null
        var prev: Node? = null
        var next: Node? = null
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
    }

    var head: Node? = null
    fun insertBeginning(p: Point?) {
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
    }

    private fun distanceBetween(from: Point, to: Point): Float {
        return Math.sqrt(Math.pow(from.y - to.y.toDouble(), 2.0) + Math.pow(from.x - to.x.toDouble(), 2.0)).toFloat()
    }

    fun totalDistance(): Float {
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
        return 0f
    }

    fun insertNearest(p: Point?) {
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
    }

    fun insertSmallest(p: Point?) {
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
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

    override fun iterator(): MutableIterator<Point> {
        return CircularLinkedListIterator()
    }
}