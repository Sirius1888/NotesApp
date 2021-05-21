package com.example.noteapplication.base

import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task


sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class NoteEvent : BaseEvent() {
    object NoteClosed : NoteEvent()
    object NoteCreated : NoteEvent()
    class NoteFetched(val array: MutableList<Task>?) : NoteEvent()
}

sealed class CreatedProjectEvent : BaseEvent() {

}

sealed class ProjectEvent : BaseEvent() {
    class ProjectFetched(val array: MutableList<Project>?) : ProjectEvent()
}

