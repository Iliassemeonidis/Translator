package com.example.mytranslator.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun io():Scheduler
    fun ui():Scheduler
}