package com.jaimegc.dragonballmodularize.base.util

import kotlinx.coroutines.Dispatchers

class ApplicationDispatchersProvider : SchedulerProvider {
    override fun io() = Dispatchers.IO
    override fun ui() = Dispatchers.Main
    override fun default() = Dispatchers.Default
}
