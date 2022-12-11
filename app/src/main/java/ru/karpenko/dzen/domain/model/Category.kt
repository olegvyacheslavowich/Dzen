package ru.karpenko.dzen.domain.model

data class Category(
    val id: Long,
    val name: String,
    val info: String,
    val iconId: Int,
    var isClicked: Boolean = false,
)
