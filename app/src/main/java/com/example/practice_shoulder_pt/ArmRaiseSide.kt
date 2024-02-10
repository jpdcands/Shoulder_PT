package com.example.practice_shoulder_pt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable()
fun ArmRaiseSide(navController: NavController) {
    val exerciseViewModel: ExerciseViewModel = viewModel()
//  Get the ViewModel

//    Use the ViewModel's state
    var selectedOption by rememberSaveable { exerciseViewModel.selectedOption }

//    var selectedOption by rememberSaveable { mutableStateOf("0") }
    val options = listOf("Set 1", "Set 2", "Set 3")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp)) // Increased height
        Text("Arm Raise Side", style = MaterialTheme.typography.headlineLarge)

        ImageSection(navController, exerciseViewModel)
        ExerciseDescription(navController, exerciseViewModel)
        NavigationButtons(navController)
    }
}
@Composable
fun ImageSection(navController: NavController, exerciseViewModel: ExerciseViewModel) {
    Spacer(modifier = Modifier.height(2.dp))
    Image(

        painter = painterResource(id = R.drawable.armraiseside), // Replace with your image resource
        contentDescription = "Arm Raise Side",
        modifier = Modifier.size(250.dp)
    )
    //   ExerciseDescription(navController, ExerciseViewModel())
}

//   ExerciseDescription(navController, ExerciseViewModel())
@Composable
fun ExerciseDescription(navController: NavController, exerciseViewModel: ExerciseViewModel) {
    val text = """
            1. This exercise does not use the arm muscles - use your legs and hips to create movement.
            2. Swing arm back and forth like a pendulum then use your hips to make circles
            3. Do this exercise for 5 minutes 4 times a day.
            4. As pain decreases, try bending over further.
            """.trimIndent()
    val modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    val style = MaterialTheme.typography.bodyLarge
    Text(text = text, modifier = modifier, style = style)
    Spacer(modifier = Modifier.height(16.dp))

    // Define the options for RadioButtonSelection
    val options = listOf("Select Set", "Set 1", "Set 2", "Set 3")
    val selectedOption by exerciseViewModel.selectedOption

    // Call RadioButtonSelection with all required parameters
    RadioButtonSelection(
        options = listOf("Select Set", "Set 1", "Set 2", "Set 3"),
        exerciseViewModel = exerciseViewModel
    )
}

@Composable
fun RadioButtonSelection(
    options: List<String>,
    exerciseViewModel: ExerciseViewModel // Add ViewModel as a parameter
) {
    val selectedOption = exerciseViewModel.selectedOption.value

//    Update the elected option using ViewModel
    Column {
        Row(
            horizontalArrangement = Arrangement.Center, // Center the radio buttons in the Row
            modifier = Modifier.fillMaxWidth() // Fill the width of the parent
        ) {
            options.forEach { option ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = option, textAlign = TextAlign.Center)
                    RadioButton(
                        selected = option == selectedOption,
                        onClick = {exerciseViewModel.updateSelectedOption(option) }
                    )
//          Update ViewModel state
                }
                Spacer(modifier = Modifier.width(20.dp)) // Space between each radio button group
            }
        }
    }
}
@Composable()
fun NavigationButtons(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        // First button
        Button(onClick = { navController.navigate("ShoulderFlexorAndExtensor") }) {
            Text("To Next Exercise", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Second button
        Button(onClick = { navController.navigate("HomePage") }) {
            Text("Back to Exercise List", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewArmRaiseSide() {

    val navController = rememberNavController()
    val exerciseViewModel = ExerciseViewModel()

    ArmRaiseSide(navController)
}
