package com.example.noteapplication

import android.graphics.Color

object ColorType  {
    val berryRed = "#b8256f"
    var red = "#db4035"

    //Добавить все существующие цвета
    fun getProjectColorType(colorId: Int?): Int {
        val color =  when (colorId) {
            30 -> berryRed //berry_red -> berryRed
            31 -> red
            else -> "#000"
        }
        return Color.parseColor(color)
    }
}