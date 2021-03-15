package com.example.noteapplication

import android.graphics.Color

object ColorType {
    val berryRed = "#b8256f"
    var red = "#db4035"
    var orange = "#ff9933"
    var yellow = "#fad000"
    var oliveGreen = "#afb83b"
    var limeGreen = "#7ecc49"
    var green = "#299438"
    var mintGreen = "#6accbc"
    var teal = "#158fad"
    var skyBlue = "#14aaf5"
    var lightBlue = "#96c3eb"
    var blue = "#4073ff"
    var grape = "#884dff"
    var violet = "#af38eb"
    var lavender = "#eb96eb"
    var magenta = "#e05194"
    var salmon = "#ff8d85"
    var charcoal = "#808080"
    var grey = "#b8b8b8"
    var taupe = "#ccac93"

    //Добавить все существующие цвета
    fun getProjectColorType(colorId: Int?): Int {
        val color = when (colorId) {
            30 -> berryRed //berry_red -> berryRed
            31 -> red
            32 -> orange
            33 -> yellow
            34 -> oliveGreen
            35 -> limeGreen
            36 -> green
            37 -> mintGreen
            38 -> teal
            39 -> skyBlue
            40 -> lightBlue
            41 -> blue
            42 -> grape
            43 -> violet
            44 -> lavender
            45 -> magenta
            46 -> salmon
            47 -> charcoal
            48 -> grey
            49 -> taupe
            else -> "#000"
        }
        return Color.parseColor(color)
    }
}