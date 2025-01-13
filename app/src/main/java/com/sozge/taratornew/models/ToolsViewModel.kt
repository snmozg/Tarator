package com.sozge.taratornew.utils.com.sozge.taratornew.models

import ToolType
import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.ViewModel


class ToolsViewModel : ViewModel() {
    private val _brightness = mutableStateOf(1f)
    val brightness: State<Float> = _brightness

    private val _contrast = mutableStateOf(1f)
    val contrast: State<Float> = _contrast

    private val _shadow = mutableStateOf(0f)
    val shadow: State<Float> = _shadow

    private val _rotationAngle = mutableStateOf(0f)
    val rotationAngle: State<Float> = _rotationAngle

    private val _vignetteIntensity = mutableStateOf(0f)
    val vignetteIntensity: State<Float> = _vignetteIntensity

    private val _detail = mutableStateOf(0f)
    val detail: State<Float> = _detail

    private val _bitmap = mutableStateOf<Bitmap?>(null)
    val bitmap: State<Bitmap?> = _bitmap

    private val _blurRadius = mutableStateOf(0f)
    val blurRadius: State<Float> get() = _blurRadius

    fun updateBlurRadius(newRadius: Float) {
        _blurRadius.value = newRadius
    }


    fun updateBrightness(value: Float) {
        _brightness.value = value
    }

    fun updateContrast(value: Float) {
        _contrast.value = value
    }

    fun updateShadow(value: Float) {
        _shadow.value = value
    }

    fun updateRotationAngle(value: Float) {
        _rotationAngle.value = value
    }

    fun updateVignetteIntensity(value: Float) {
        _vignetteIntensity.value = value
    }

    fun updateDetail(value: Float) {
        _detail.value = value
    }


    /*
    ////////////////////////////////////////////////////////////////////////

    private val _tool = mutableStateOf<ToolType?>(null)
    val tool: State<ToolType?> = _tool


    private val _brightness = mutableStateOf(1f)
    val brightness: State<Float> = _brightness

    private val _contrast = mutableStateOf(1f)
    val contrast: State<Float> = _contrast

    private val _shadow = mutableStateOf(0f)
    val shadow: State<Float> = _shadow

    private val _rotationAngle = mutableStateOf(0f)
    val rotationAngle: State<Float> = _rotationAngle

    private val _vignetteIntensity = mutableStateOf(0f)
    val vignetteIntensity: State<Float> = _vignetteIntensity

    private val _detail = mutableStateOf(0f)
    val detail: State<Float> = _detail


    fun updateBrightness(value: Float) {
        _brightness.value = value
    }

    fun updateContrast(value: Float) {
        _contrast.value = value
    }

    fun updateShadow(value: Float) {
        _shadow.value = value
    }

    fun updateRotationAngle(value: Float) {
        _rotationAngle.value = value
    }

    fun updateVignetteIntensity(value: Float) {
        _vignetteIntensity.value = value
    }

    fun updateDetail(value: Float) {
        _detail.value = value
    }

     */
}

