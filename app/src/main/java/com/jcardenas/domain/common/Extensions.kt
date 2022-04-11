package com.jcardenas.domain.common

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

@SuppressLint("Range")
fun Uri.getFileName(context: Context): String {
    var result: String = ""
    if (scheme.equals("content")) {
        val cursor = context.contentResolver.query(this, null, null, null, null)
        try {
            cursor?.let { c ->
                if(c.moveToFirst()) {
                    result = c.getString(c.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            }
        }finally {
            cursor?.close()
        }

        if(result.isEmpty()) {
            result = path ?: ""
            val cut = result.lastIndexOf('/')
            if (cut != -1){
                result = result.substring(cut + 1)
            }
        }
    }
    return result
}