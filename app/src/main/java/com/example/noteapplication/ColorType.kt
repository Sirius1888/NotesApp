package com.example.noteapplication

import android.graphics.Color
import com.example.noteapplication.data.model.PrimaryColor

enum class Colors(val colorName: String, val hex: String, val id: Int){
    BERRY_RED("Я - Ягодка!", "#b8256f", 30),
    RED("Я - Красненький!", "#db4035", 31),
    ORANGE("Я - Апельсинчик!","#ff9933", 32),
    YELLOW("Я - Цыпленок!","#fad000", 33),
    OLIVE_GREEN("Я - Оливочка!","#afb83b", 34),
    LIME_GREEN("Я - Просто Лайм","#7ecc49", 35),
    GREEN("Я - Зеленый!","#299438", 36),
    MINT_GREEN("Я - Ментос!","#6accbc", 37),
    TEAL("Я - Бирюзовый!","#158fad", 38),
    SKY_BLUE("Я - Небесный Голубой!","#14aaf5", 39),
    LIGHT_BLUE("Я хоть и голубой, но светый","#96c3eb", 40),
    BLUE("Я - Голубой!","#4073ff", 41),
    GRAPE("Я - Виноградный!","#884dff", 42),
    VIOLET("Я - Фиолетовый!","#af38eb", 43),
    LAVENDER("Я - Нежная Лаванада :)","#eb96eb", 44),
    MAGENTA("Я - Пурпурный :(","#e05194", 45),
    SALMON("Я - Семга..","#ff8d85", 46),
    CHARCOAL("Я - Древесный УГОЛЬ","#808080", 47),
    GREY("Я - Серый.","#b8b8b8", 48),
    TAUPE("Я - АфроСерый","#ccac93", 49),
}

object ColorType {

    fun getNotesPallette(): MutableList<PrimaryColor> {
        return mutableListOf<PrimaryColor>().apply {
            add(PrimaryColor(Colors.BERRY_RED.colorName, Colors.BERRY_RED.hex, Colors.BERRY_RED.id))
            add(PrimaryColor(Colors.RED.colorName, Colors.RED.hex, Colors.RED.id))
            add(PrimaryColor(Colors.ORANGE.colorName, Colors.ORANGE.hex, Colors.ORANGE.id))
            add(PrimaryColor(Colors.YELLOW.colorName, Colors.YELLOW.hex, Colors.YELLOW.id))
            add(PrimaryColor(Colors.OLIVE_GREEN.colorName, Colors.OLIVE_GREEN.hex, Colors.OLIVE_GREEN.id))
            add(PrimaryColor(Colors.LIME_GREEN.colorName, Colors.LIME_GREEN.hex, Colors.LIME_GREEN.id))
            add(PrimaryColor(Colors.GREEN.colorName, Colors.GREEN.hex, Colors.GREEN.id))
            add(PrimaryColor(Colors.MINT_GREEN.colorName, Colors.MINT_GREEN.hex, Colors.MINT_GREEN.id))
            add(PrimaryColor(Colors.TEAL.colorName, Colors.TEAL.hex, Colors.TEAL.id))
            add(PrimaryColor(Colors.SKY_BLUE.colorName, Colors.SKY_BLUE.hex, Colors.SKY_BLUE.id))
            add(PrimaryColor(Colors.LIGHT_BLUE.colorName, Colors.LIGHT_BLUE.hex, Colors.LIGHT_BLUE.id))
            add(PrimaryColor(Colors.BLUE.colorName, Colors.BLUE.hex, Colors.BLUE.id))
            add(PrimaryColor(Colors.GRAPE.colorName, Colors.GRAPE.hex, Colors.GRAPE.id))
            add(PrimaryColor(Colors.VIOLET.colorName, Colors.VIOLET.hex, Colors.VIOLET.id))
            add(PrimaryColor(Colors.LAVENDER.colorName, Colors.LAVENDER.hex, Colors.LAVENDER.id))
            add(PrimaryColor(Colors.MAGENTA.colorName, Colors.MAGENTA.hex, Colors.MAGENTA.id))
            add(PrimaryColor(Colors.SALMON.colorName, Colors.SALMON.hex, Colors.SALMON.id))
            add(PrimaryColor(Colors.CHARCOAL.colorName, Colors.CHARCOAL.hex, Colors.CHARCOAL.id))
            add(PrimaryColor(Colors.GREY.colorName, Colors.GREY.hex, Colors.GREY.id))
            add(PrimaryColor(Colors.TAUPE.colorName, Colors.TAUPE.hex, Colors.TAUPE.id))
        }
    }

    fun getProjectColorType(colorId: Int?): Int {
        val color = when (colorId) {
            Colors.BERRY_RED.id -> Colors.BERRY_RED.hex
            Colors.RED.id -> Colors.RED.hex
            Colors.ORANGE.id -> Colors.ORANGE.hex
            Colors.YELLOW.id -> Colors.YELLOW.hex
            Colors.OLIVE_GREEN.id -> Colors.OLIVE_GREEN.hex
            Colors.LIME_GREEN.id -> Colors.LIME_GREEN.hex
            Colors.GREEN.id -> Colors.GREEN.hex
            Colors.MINT_GREEN.id -> Colors.MINT_GREEN.hex
            Colors.TEAL.id -> Colors.TEAL.hex
            Colors.SKY_BLUE.id -> Colors.SKY_BLUE.hex
            Colors.LIGHT_BLUE.id -> Colors.LIGHT_BLUE.hex
            Colors.BLUE.id -> Colors.BLUE.hex
            Colors.GRAPE.id -> Colors.GRAPE.hex
            Colors.VIOLET.id -> Colors.VIOLET.hex
            Colors.LAVENDER.id -> Colors.LAVENDER.hex
            Colors.MAGENTA.id -> Colors.MAGENTA.hex
            Colors.SALMON.id -> Colors.SALMON.hex
            Colors.CHARCOAL.id -> Colors.CHARCOAL.hex
            Colors.GREY.id -> Colors.GREY.hex
            Colors.TAUPE.id -> Colors.TAUPE.hex
            else -> "#000"
        }
        return Color.parseColor(color)
    }
}