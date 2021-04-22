package com.example.noteapplication.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Project(
        var id: Long? = null,
        var color: Int? = null,
        var name: String? = null,
        @SerializedName("comment_count")
        var commentCount: Int? = null,
        var shared: Boolean? = null,
        var favorite: Boolean? = null,
        @SerializedName("sync_id")
        var syncId: Int? = null,
        @SerializedName("inbox_project")
        var inboxProject: Boolean? = null
) : Serializable

//data - Описываем объект. В конструкторе должен быть хотя бы 1 параметр
//class - Описывает объект
//open class - Описывает объект, можно наследоваться
//object - Синглтон объект
//interface - задает поведение классу/Может выстапать контрактом между классами
//abstract - задает поведение классу и можно делать базовые реализации методов
//enum - Класс перечислений, используются переменные
//sealed - Класс перечислений, перечислениями являются классы
//companion object - Объекты компанионы, создаются внутри класса, притом могут хранить методы и переменные и к ним можно обратиться без создания экземпляара класса






