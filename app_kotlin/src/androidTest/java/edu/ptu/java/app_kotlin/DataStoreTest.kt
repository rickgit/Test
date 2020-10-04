package edu.ptu.java.app_kotlin

import android.content.Context
import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import androidx.test.platform.app.InstrumentationRegistry
import com.example.application.Settings
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.InputStream
import java.io.OutputStream


class DataStoreTest {
    object SettingsSerializer : Serializer<Settings> {
        override fun readFrom(input: InputStream): Settings {
            try {
                return Settings.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

        override fun writeTo(
                t: Settings,
                output: OutputStream) = t.writeTo(output)
    }
    @Test
    fun testDs(){
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        runBlocking {
            test(targetContext)
        }

    }
    suspend fun test(context: Context?) {///data/data/edu.ptu.java.app_kotlin/files/datastore/sp.pb
        val settingDs = context?.createDataStore("sp.pb",SettingsSerializer)
        //写

        settingDs!!.updateData {
            it.toBuilder().setExampleCounter(it.exampleCounter+1).build()
        }
        //读取
        val map = settingDs!!.data.map { sp -> sp.exampleCounter }
    }
}