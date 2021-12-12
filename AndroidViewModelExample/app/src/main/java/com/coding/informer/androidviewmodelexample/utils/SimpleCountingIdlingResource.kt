package com.coding.informer.androidviewmodelexample.utils

import java.util.concurrent.atomic.AtomicInteger


/**
 * As simple counter implementation of [IdlingResource] that determines idleness by
 * maintaining an internal counter. When the counter os 0 - it is consider to be idle, when it is
 * non-zero it is not idle. This is very similar to the way a [java.util.concurrent.Semaphore]
 *
 * This class can then be used to wrap up operations that while in progress should block tests from
 * accessing the UI
 */
class SimpleCountingIdlingResource(private val resourceName: String): IdlingResource {

    private val counter = AtomicInteger(0)


}