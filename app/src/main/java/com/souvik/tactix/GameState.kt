package com.souvik.tactix

data class GameState(
    val playerCircleCount : Int = 0,
    val playerCrossCount : Int = 0,
    val drawCount : Int = 0,
    val hintText : String = " PLAYER 'O' Turn ",
    val currentTurn : BoardCellValues = BoardCellValues.CIRCLE,
    val hasWon : Boolean = false,
    val victoryType : VictoryType = VictoryType.NONE
)

enum class BoardCellValues {
    CIRCLE,
    CROSS,
    NONE
}

enum class VictoryType {
    NONE,
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAGONAL1,
    DIAGONAL2
}