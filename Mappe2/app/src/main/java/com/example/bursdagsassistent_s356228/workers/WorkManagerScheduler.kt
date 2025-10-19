package com.example.bursdagsassistent_s356228.workers

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class WorkManagerScheduler(context: Context) {
    private val workManager = WorkManager.getInstance(context)

    fun scheduleBirthdayCheck(){
        val workRequest = PeriodicWorkRequestBuilder<BirthdayWorker>(
            repeatInterval = 1,
            repeatIntervalTimeUnit = TimeUnit.DAYS
        ).build()

        workManager.enqueueUniquePeriodicWork(
            uniqueWorkName = "BirthdaySmsWork",
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
            request = workRequest
        )
        Log.d("WorkManagerScheduler", "SMS.tjeneste PÅ. Daglig bursdagssjekk er aktivert")
    }

    fun cancelBirthdayCheck(){
        workManager.cancelUniqueWork(uniqueWorkName = "BirthdaySmsWork")
        Log.d("WorkManagerScheduler", "SMS-tjeneste AV. Daglig bursdagssjekk er deaktivert")
    }
}