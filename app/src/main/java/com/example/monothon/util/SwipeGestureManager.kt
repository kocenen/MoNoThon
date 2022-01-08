package com.example.monothon.util

import android.view.MotionEvent
import android.view.View


class SwipeGestureManager(private val targetView: View) {
    var isSwiping = false
    var isTouchContainInArea = false

    val targetViewY get() = targetView.y
    val targetViewHeight get() = targetView.height

    private var beginTouchY = 0f
    private var endTouchY = 0f

    private var beginTargetY = 0f

    sealed class SwipeGestureEvent {
        object None : SwipeGestureEvent()
        object OnSwipeBeginEvent : SwipeGestureEvent()
        data class OnSwipeUpEvent(val changeTargetY: Float) : SwipeGestureEvent()
        data class OnSwipeUpEndEvent(val touchMoveDistance: Float) : SwipeGestureEvent()
        data class OnSwipeDownEvent(val changeTargetY: Float) : SwipeGestureEvent()
        data class OnSwipeDownEndEvent(val touchMoveDistance: Float) : SwipeGestureEvent()
        data class OnSwipeEndEvent(val swipeGestureDirection: SwipeGestureDirection, val touchMoveDistance: Float) : SwipeGestureEvent()
    }

    enum class SwipeGestureDirection {
        None,
        SwipeUp,
        SwipeDown
    }

    fun onSwipeGestureEvent(event: MotionEvent?, swipeDistance: Float): SwipeGestureEvent {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                isTouchContainInArea = event.rawY > targetViewY && event.rawY < targetViewY + targetViewHeight

                if(isTouchContainInArea) {
                    beginTouchY = event.rawY
                    beginTargetY = targetViewY
                }
                SwipeGestureEvent.None
            }
            MotionEvent.ACTION_MOVE -> {
                when (isTouchContainInArea) {
                    true -> {
                        val touchMoveDistance = beginTouchY - event.rawY
                        val changeTargetY = beginTargetY - touchMoveDistance

                        when (touchMoveDistance != 0f) {
                            true -> when (isSwiping) {
                                true -> {
                                    when (touchMoveDistance > 0) {
                                        true -> SwipeGestureEvent.OnSwipeUpEvent(changeTargetY)
                                        false -> SwipeGestureEvent.OnSwipeDownEvent(changeTargetY)
                                    }
                                }
                                false -> {
                                    isSwiping = true
                                    SwipeGestureEvent.OnSwipeBeginEvent
                                }
                            }
                            false -> SwipeGestureEvent.None
                        }
                    }
                    false -> SwipeGestureEvent.None
                }
            }
            MotionEvent.ACTION_UP -> {
                if(isSwiping) {
                    isSwiping = false
                    val touchMoveDistance = event.rawY - beginTouchY
                    val swipeDirection = when {
                        touchMoveDistance < -swipeDistance -> SwipeGestureDirection.SwipeUp
                        touchMoveDistance > swipeDistance -> SwipeGestureDirection.SwipeDown
                        else -> SwipeGestureDirection.None
                    }

                    when (swipeDirection) {
                        SwipeGestureDirection.None -> SwipeGestureEvent.OnSwipeEndEvent(swipeDirection, event.rawY - beginTouchY)
                        SwipeGestureDirection.SwipeUp -> SwipeGestureEvent.OnSwipeUpEndEvent(touchMoveDistance)
                        SwipeGestureDirection.SwipeDown -> SwipeGestureEvent.OnSwipeDownEndEvent(touchMoveDistance)
                    }
                }
                else
                    SwipeGestureEvent.None
            }
            else -> SwipeGestureEvent.None
        }
    }
}

