package edu.ptu.androidtest.android.service;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

public class JobScheduleHelper {
    public void schedule(Context context) {
        JobScheduler scheduler = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                scheduler = context.getSystemService(JobScheduler.class);
            }

            final JobInfo.Builder builder = new JobInfo.Builder(_03_JobService.ELLISONS_JOB_ID,
                    new ComponentName(context, _03_JobService.class));

            builder.setOverrideDeadline(_03_JobService.ELLISONS_JOB_OVERDIDE_DEADLINE);
            builder.setPeriodic(3);//3秒执行一次
            scheduler.schedule(builder.build());
        }
    }
}
