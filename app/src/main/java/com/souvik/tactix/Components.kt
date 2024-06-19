package com.souvik.tactix

import android.view.Surface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.souvik.tactix.ui.theme.Aqua
import com.souvik.tactix.ui.theme.GreenishYellow
import com.souvik.tactix.ui.theme.PinkButton

@Composable
fun BoardBase() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp),

    ){
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(size.width/3 , 0f),
            end = Offset(size.width/3, size.height)
        )

        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(size.width * 2/3 , 0f),
            end = Offset(size.width * 2/3, size.height)
        )

        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height/3),
            end = Offset(size.width, size.height / 3)
        )

        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(0f , size.height * 2/3),
            end = Offset(size.width, size.height * 2/3)
        )
    }
}

@Composable
fun Circle(){
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp),
    ){
        drawCircle(
            color = Aqua,
            style = Stroke(width = 20f)
        )
    }
}

@Composable
fun Cross(){
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp)
    ){
        drawLine(
            color = GreenishYellow,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(0f ,0f),
            end = Offset(size.width, size.height)
        )

        drawLine(
            color = GreenishYellow,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(0f ,size.height),
            end = Offset(size.width, 0f)
        )
    }
}

@Composable
fun WinningLine1(){
    Canvas(modifier = Modifier
        .size(300.dp)
    ){
        // #1
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            start = Offset(0f ,0f),
            end = Offset(size.width, size.height)
        )
    }
}

@Composable
fun WinningLine2(){
    Canvas(modifier = Modifier
        .size(300.dp)
    ){

        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            start = Offset(0f ,size.height),
            end = Offset(size.width, 0f)
        )
    }
}

@Composable
fun WinningLine3(){
    Canvas(modifier = Modifier
        .size(300.dp)
    ){

        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            start = Offset(size.width * 1/6 ,0f),
            end = Offset(size.width * 1/6 , size.height)
        )
    }
}

@Composable
fun WinningLine4(){
    Canvas(modifier = Modifier
        .size(300.dp)
    ){

        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            start = Offset(size.width * 1/2 ,0f),
            end = Offset(size.width * 1/2 , size.height)
        )
    }
}
@Composable
fun WinningLine5(){
    Canvas(modifier = Modifier
        .size(300.dp)
    ){

        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            start = Offset(size.width * 5/6 ,0f),
            end = Offset(size.width * 5/6 , size.height)
        )
    }
}
@Composable
fun WinningLine6(){
    Canvas(modifier = Modifier
        .size(300.dp)
    ){

        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            start = Offset(0f,size.height * 1/6),
            end = Offset(size.width, size.height * 1/6)
        )
    }
}
@Composable
fun WinningLine7(){
    Canvas(modifier = Modifier
        .size(300.dp)
    ){

        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            start = Offset(0f,size.height * 1/2),
            end = Offset(size.width, size.height * 1/2)
        )
    }
}
@Composable
fun WinningLine8(){
    Canvas(modifier = Modifier
        .size(300.dp)
    ){

        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            start = Offset(0f,size.height * 5/6),
            end = Offset(size.width, size.height * 5/6)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Prevs() {
    WinningLine1()
    WinningLine2()
    WinningLine3()
    WinningLine4()
    WinningLine5()
    WinningLine6()
    WinningLine7()
    WinningLine8()
    Circle()
    Cross()
}



