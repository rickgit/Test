package edu.ptu.androidtest.android.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class _03_JobService extends JobService {
    public static final int ELLISONS_JOB_ID = 1;
    public static final long ELLISONS_JOB_OVERDIDE_DEADLINE = 1l;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        System.out.println("onStartJob");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        System.out.println("onStopJob");
        return false;
    }
}
