package com.souvik.tactix

sealed class UserAction {
    object PlayAgainButtonClicked : UserAction()
    data class BoardTapped(val cellNo : Int) : UserAction()
}