package com.souvik.tactix

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    var state by mutableStateOf(GameState())

    val boardItems: MutableMap<Int, BoardCellValues> = mutableMapOf(
        1 to BoardCellValues.NONE,
        2 to BoardCellValues.NONE,
        3 to BoardCellValues.NONE,
        4 to BoardCellValues.NONE,
        5 to BoardCellValues.NONE,
        6 to BoardCellValues.NONE,
        7 to BoardCellValues.NONE,
        8 to BoardCellValues.NONE,
        9 to BoardCellValues.NONE
    )

    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.BoardTapped -> {
                addValuesToBoard(action.cellNo)
            }

            UserAction.PlayAgainButtonClicked -> {
                gameReset()
            }
        }
    }

    private fun gameReset() {
        boardItems.forEach { (i, _) ->
            boardItems[i] = BoardCellValues.NONE
            state = state.copy(
                victoryType = VictoryType.NONE
            )
            
        }
        state = state.copy(
            hintText = "PLAYER 'O' Turn",
            currentTurn = BoardCellValues.CIRCLE
        )
    }

    private fun addValuesToBoard(cellNo: Int) {
        if (boardItems[cellNo] != BoardCellValues.NONE) {
            return
        }
        if (state.currentTurn == BoardCellValues.CIRCLE) {
            boardItems[cellNo] = BoardCellValues.CIRCLE
            if (victoryCheck(BoardCellValues.CIRCLE)) {
                state = state.copy(
                    hintText = "PLAYER 'O' Won!!",
                    playerCircleCount = state.playerCircleCount + 1,
                    currentTurn = BoardCellValues.NONE,
                    hasWon = true
                )
            } else if (isBoardFull()) {
                state = state.copy(
                    hintText = "GAME DRAW",
                    drawCount = state.drawCount + 1
                )
            } else {
                state = state.copy(
                    hintText = "PLAYER 'X' Turn",
                    currentTurn = BoardCellValues.CROSS
                )
            }
        } else if (state.currentTurn == BoardCellValues.CROSS) {
            boardItems[cellNo] = BoardCellValues.CROSS
            if (victoryCheck(BoardCellValues.CROSS)) {
                state = state.copy(
                    hintText = "PLAYER 'X' Won!!",
                    playerCrossCount = state.playerCrossCount + 1,
                    currentTurn = BoardCellValues.NONE,
                    hasWon = true
                )
            } else {
                state = state.copy(
                    hintText = "PLAYER 'O' Turn",
                    currentTurn = BoardCellValues.CIRCLE
                )
            }
        }

    }

    private fun victoryCheck(boardValue: BoardCellValues): Boolean {
        when {
            boardItems[1] == boardValue && boardItems[5] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(victoryType = VictoryType.DIAGONAL1)
                return true
            }

            boardItems[3] == boardValue && boardItems[5] == boardValue && boardItems[7] == boardValue -> {
                state = state.copy(victoryType = VictoryType.DIAGONAL2)
                return true
            }

            boardItems[1] == boardValue && boardItems[2] == boardValue && boardItems[3] == boardValue -> {
                state = state.copy(victoryType = VictoryType.HORIZONTAL1)
                return true
            }

            boardItems[4] == boardValue && boardItems[5] == boardValue && boardItems[6] == boardValue -> {
                state = state.copy(victoryType = VictoryType.HORIZONTAL2)
                return true
            }

            boardItems[7] == boardValue && boardItems[8] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(victoryType = VictoryType.HORIZONTAL3)
                return true
            }

            boardItems[1] == boardValue && boardItems[4] == boardValue && boardItems[7] == boardValue -> {
                state = state.copy(victoryType = VictoryType.VERTICAL1)
                return true
            }

            boardItems[2] == boardValue && boardItems[5] == boardValue && boardItems[8] == boardValue -> {
                state = state.copy(victoryType = VictoryType.VERTICAL2)
                return true
            }

            boardItems[3] == boardValue && boardItems[6] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(victoryType = VictoryType.VERTICAL3)
                return true
            }

            else ->
                return false
        }
    }

    private fun isBoardFull(): Boolean {
        if (boardItems.containsValue(BoardCellValues.NONE)) return false else return true
    }
}