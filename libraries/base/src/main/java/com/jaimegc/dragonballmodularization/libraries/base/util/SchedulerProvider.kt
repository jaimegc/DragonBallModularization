package com.jaimegc.dragonballmodularization.libraries.base.util

import kotlinx.coroutines.CoroutineDispatcher

interface SchedulerProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}
