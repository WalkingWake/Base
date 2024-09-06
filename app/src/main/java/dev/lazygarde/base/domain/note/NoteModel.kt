package dev.lazygarde.base.domain.note

import android.graphics.Color

data class NoteModel(
    var id: Int,
    var token: String,
    var isUpdate: String,
    var title: String,
    var dateCreateNote: Long,
    var isPinned: Boolean,
    var datePinned: Long,
    var modifiedTime: Long,
    var isArchive: Boolean,
    var isDelete: Boolean,
    var isAlarm: Boolean,
    var isLock: Boolean,
    var wallpaperUrl: String = "",
    var noteItems: List<NoteItemType> = listOf()
)

sealed class NoteItemType {
    data object TABLE: NoteItemType()
    data class IMAGE(
        val imageURL: String
    ): NoteItemType()
    data class CHARACTER(
        val char: Char,
        var size: Int = 14,
        var color: Int,
        var background: Int = Color.parseColor("#00FFFFFF"),
        var isItalic: Boolean = false,
        var isBold: Boolean = false,
        var isUnderline: Boolean = false,
    ): NoteItemType()
    data class LINK(
        val content: String,
        val url: String
    ): NoteItemType()
}