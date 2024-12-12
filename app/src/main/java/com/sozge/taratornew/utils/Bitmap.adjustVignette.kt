package com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.runtime.Composable



fun Bitmap.adjustVignette(intensity: Float): Bitmap {
    val width = this.width
    val height = this.height
    val resultBitmap = Bitmap.createBitmap(width, height, this.config!!)
    val canvas = Canvas(resultBitmap)

    // Orijinal bitmap'i çiz
    canvas.drawBitmap(this, 0f, 0f, null)

    // Vinyet efekti için Paint oluştur
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val radius = (width.coerceAtLeast(height) / 1.5f) // Çerçeve etkisi için yarıçap
    val centerX = width / 2f
    val centerY = height / 2f

    // RadialGradient ile dıştan içe doğru çerçeve oluştur
    val shader = android.graphics.RadialGradient(
        centerX, centerY, radius,
        intArrayOf(0x00000000, 0xFF000000.toInt()), // Şeffaf merkez, siyah kenar
        floatArrayOf(1f - intensity, 1f), // Yoğunluk ayarı
        android.graphics.Shader.TileMode.CLAMP
    )
    paint.shader = shader

    // Çerçeve şeklinde gölge oluştur
    canvas.drawCircle(centerX, centerY, radius, paint)

    return resultBitmap
}



