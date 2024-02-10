package com.example.practice_shoulder_pt

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomePage(navController: NavController) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("Welcome") }) {
                Text("Back to Welcome Page", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            NamesLazyColumn(navController)
        }
    }
}

@Composable
fun NamesLazyColumn(navController: NavController) {

    val exerciseList = listOf(
        "Arm Raise-Side"
    )

    val exerciseToRouteMap = mapOf(
        "Arm Raise-Side" to "armRaiseSide"
    )

    @Composable
    fun getImageResourceForExercise(exerciseName: String): Int {
        return when (exerciseName) {
            "Arm Raise-Side" -> R.drawable.armraiseside // Replace with actual image name
            else -> R.drawable.shoulderpain // Default image if no match is found
        }
    }
    @Composable
    fun ExerciseImageCard(navController: NavController) {
        val exerciseName = "YourExerciseName" // Replace with the actual exercise name
        val imageResource = getImageResourceForExercise(exerciseName)
        val route = exerciseToRouteMap[exerciseName] ?: "DefaultRoute"

        Card(
            modifier = Modifier
                .padding(10.dp)
                .clickable { navController.navigate(route) }
        ) {
            Image(
                painter = painterResource(id = imageResource), // Replace with your image resource
                contentDescription = "Exercise Image"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewHomePage() {
    val navController = rememberNavController()
    HomePage(navController)
    NamesLazyColumn(navController)
}

