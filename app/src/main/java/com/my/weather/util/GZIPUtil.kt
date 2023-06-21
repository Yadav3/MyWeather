package com.my.weather.util

import java.io.BufferedInputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream
import javax.inject.Inject


class GZIPUtil @Inject constructor() {


    fun getGZIPInputStream(inputStream: InputStream): GZIPInputStream {
        return GZIPInputStream(BufferedInputStream(inputStream))
    }
}