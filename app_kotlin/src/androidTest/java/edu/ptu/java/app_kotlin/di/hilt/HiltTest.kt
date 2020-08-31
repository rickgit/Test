package edu.ptu.java.app_kotlin.di.hilt

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import edu.ptu.java.app_kotlin.di.hilt.app.HActivity
import edu.ptu.java.app_kotlin.di.hilt.app.HApplication
import edu.ptu.java.app_kotlin.di.hilt.app.HFragment
import edu.ptu.java.app_kotlin.di.hilt.app.viewmodel.HViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class  HiltTest{
    @Test
    fun test(){
        val hApplication = HApplication()
        hApplication
        val hActivity =startActivityFromInstrumentation(InstrumentationRegistry.getInstrumentation().targetContext,HActivity::class.java)
        val viewModel = (hActivity as HActivity).getViewModel() as HViewModel
        Assert.assertNotNull(viewModel.injectApp!=null)

        val hFragment:HFragment = hActivity.supportFragmentManager.findFragmentByTag("hlitFragment") as HFragment
        val viewModel1 = hFragment.getViewModel()//Hilt Fragments must be attached before creating the component.
        Assert.assertNotNull((viewModel1 as HViewModel).injectApp!=null)
        Assert.assertNotEquals(viewModel1,viewModel)
        println()
    }

    fun startActivityFromInstrumentation(context: Context, newActivity: Class<*>): Activity? {
        val monitor: Instrumentation.ActivityMonitor
        val intent = Intent(context, newActivity)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        monitor = InstrumentationRegistry.getInstrumentation().addMonitor(newActivity.name, null, false)
        val newActivityObj = InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(monitor, 5000)
        Assert.assertNotNull("newActivity is Null", newActivityObj)
        InstrumentationRegistry.getInstrumentation().removeMonitor(monitor)
        return newActivityObj
    }
}