package com.example.android_dz_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.android_dz_1.ui.theme.Androiddz1Theme
import com.example.android_dz_1.ui.theme.AppColors
import com.example.android_dz_1.ui.theme.Dimens


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreateFigure()
        }
    }
}

@SuppressLint("MutableCollectionMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun CreateFigure() {
    var numbers by rememberSaveable { mutableStateOf(emptyList<Int>()) }

    val configuration = LocalConfiguration.current
    val columns =
        if (configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) 3 else 4

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            contentPadding = PaddingValues(Dimens.Padding),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpaceBy),
            horizontalArrangement = Arrangement.spacedBy(Dimens.SpaceBy),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(numbers) { _, number ->
                NumberCard(number,columns)
            }
        }


        Button(
            onClick = { numbers += numbers.size },
            modifier = Modifier
                .padding(Dimens.Padding)
                .size(Dimens.ButtonSize)


        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }

}

@Composable
fun NumberCard(number: Int,columns: Int) {

    val backgroundColor = if (number % 2 == 0) {
       AppColors.Blue
    } else {
        AppColors.Red
    }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemSize = (screenWidth - Dimens.Padding * (columns + 1)) / columns

    Box(
        modifier = Modifier
            .size(itemSize)
            .clip(RoundedCornerShape(Dimens.BorderRadius))
            .background(backgroundColor)
            .alpha(0.5F),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            fontSize = Dimens.TextSizeLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}


