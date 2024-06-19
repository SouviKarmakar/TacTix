package com.souvik.tactix

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.souvik.tactix.ui.theme.Aqua
import com.souvik.tactix.ui.theme.BlueCustom
import com.souvik.tactix.ui.theme.CanvasBackground
import com.souvik.tactix.ui.theme.GrayBackground
import com.souvik.tactix.ui.theme.PinkButton
import com.souvik.tactix.ui.theme.TacTixTheme

@Composable
fun GameScreen(
    viewModel: GameViewModel
) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("PLAYER [O]: ${state.playerCircleCount} ", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("DRAW: ${state.drawCount} ", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("PLAYER [X]: ${state.playerCrossCount} ", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Text(
            text = "TAC TIX",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = BlueCustom
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(GrayBackground)
                .background(color = CanvasBackground),
            contentAlignment = Alignment.Center,

        ) {
            BoardBase()
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .aspectRatio(1f),

                ){
                    viewModel.boardItems.forEach{ (cellNo , boardCellValue) ->
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    ) {
                                        viewModel.onAction(
                                            UserAction.BoardTapped(cellNo)
                                        )
                                    },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AnimatedVisibility(
                                    visible = viewModel.boardItems[cellNo] != BoardCellValues.NONE,
                                    enter = scaleIn(tween(500)),
                                    exit = scaleOut(tween(500))
                                ) {
                                    if (boardCellValue == BoardCellValues.CIRCLE){
                                        Circle()
                                    }else if (boardCellValue == BoardCellValues.CROSS){
                                        Cross()
                                    }
                                }
                            }
                        }
                    }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = state.hasWon,
                    enter = fadeIn(tween(1500))
                ) {
                    DrawVictoryLines(state = state)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state.hintText,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold

            )
            Button(
                onClick = {
                          viewModel.onAction(
                              UserAction.PlayAgainButtonClicked
                          )
                },
                shape = RoundedCornerShape(7.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp),
                colors = ButtonDefaults.buttonColors(PinkButton)
            ) {
                Text("PLAY AGAIN", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}


@Composable
fun DrawVictoryLines(state : GameState){
    when (state.victoryType){

        VictoryType.NONE -> {}
        VictoryType.HORIZONTAL1 -> WinningLine6()
        VictoryType.HORIZONTAL2 -> WinningLine7()
        VictoryType.HORIZONTAL3 -> WinningLine8()
        VictoryType.VERTICAL1 -> WinningLine3()
        VictoryType.VERTICAL2 -> WinningLine4()
        VictoryType.VERTICAL3 -> WinningLine5()
        VictoryType.DIAGONAL1 -> WinningLine1()
        VictoryType.DIAGONAL2 -> WinningLine2()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TacTixTheme {
        GameScreen(viewModel = GameViewModel())
    }
}




