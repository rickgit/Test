package edu.ptu.java.app_kotlin

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkManagerTest {
    class UploadWorker(appContext: Context, workerParams: WorkerParameters):
            Worker(appContext, workerParams) {
        override fun doWork(): Result {

            // Do the work here--in this case, upload the images.
//            uploadImages()

            // Indicate whether the work finished successfully with the Result
            return Result.success()
        }
    }
    fun testWm(context: Context?) {
        WorkManager.getInstance(context!!).enqueue(OneTimeWorkRequestBuilder<UploadWorker>().build())
    }
}