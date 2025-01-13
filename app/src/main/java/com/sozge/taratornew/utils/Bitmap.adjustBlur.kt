package com.sozge.taratornew.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.S)
@Suppress("DEPRECATION")
fun Bitmap.adjustBlur(context: Context, radius: Float): Bitmap {
    val rs = RenderScript.create(context)
    val input = Allocation.createFromBitmap(rs, this)
    val output = Allocation.createTyped(rs, input.type)
    val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
    script.setRadius(radius)
    script.setInput(input)
    script.forEach(output)
    output.copyTo(this)
    rs.destroy()
    return this
}

