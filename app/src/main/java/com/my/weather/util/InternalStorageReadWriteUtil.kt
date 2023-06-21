package com.my.weather.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject


class InternalStorageReadWriteUtil @Inject constructor(@ApplicationContext private val context: Context) {


    fun writeFileToInternalStorage(inputStream: InputStream): String {

        var fileOutputStream: FileOutputStream? = null
        var filePath = ""

        try {
            val buffer = ByteArray(1024)
            var length: Int

            val file = File(context.filesDir.path + File.separator + "WeatherData.json")

            filePath = file.path

            fileOutputStream = FileOutputStream(file)

            while (inputStream.read(buffer).also { length = it } != -1) {
                fileOutputStream.write(buffer, 0, length)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream.close()
            fileOutputStream?.close()
        }

        return filePath
    }

    fun readFileFromInternalStorage(filePath: String): File {
        return File(filePath)
    }
}