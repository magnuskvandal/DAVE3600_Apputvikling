package com.example.bursdagsassistent_s356228.workers

import android.content.Context
import android.telephony.SmsManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bursdagsassistent_s356228.BirthdayAssistantApplication
import kotlinx.coroutines.flow.first
import java.time.LocalDate

class BirthdayWorker(context: Context, workerParams: WorkerParameters): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val application = applicationContext as BirthdayAssistantApplication
        val friendRepository = application.friendRepository
        val preferencesRepository = application.preferencesRepository

        return try{
            val today = LocalDate.now()
            val allFriends = friendRepository.allFriends.first()
            val birthdayFriends = allFriends.filter{ friend ->
                friend.dateOfBirth.month == today.month && friend.dateOfBirth.dayOfMonth == today.dayOfMonth
            }
            if(birthdayFriends.isNotEmpty()){
                val smsManager: SmsManager = SmsManager.getDefault()
                val message = preferencesRepository.getDefaultSmsMessage()

                birthdayFriends.forEach { friend ->
                    try {
                        smsManager.sendTextMessage(friend.phoneNumber, null, message, null, null)
                        Log.d("BirthdayWorker", "SMS sendt til ${friend.firstName} ${friend.lastName}")
                    } catch(e: Exception){
                        Log.e("BirthdayWorker", "Kunne ikke sende SMS til ${friend.firstName} ${friend.lastName}", e)
                    }
                }
            }
            Log.d("BirthdayWorker", "doWork() fullført. Antall bursdager i dag: ${birthdayFriends.size}")
            Result.success()
        }catch(e: Exception){
            Log.e("BirthdayWorker", "doWork() feilet.", e)
            Result.failure()
        }
    }
}