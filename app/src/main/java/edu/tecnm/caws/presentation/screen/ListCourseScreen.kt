package edu.tecnm.caws.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import edu.tecnm.caws.presentation.composable.CardCourse
import edu.tecnm.caws.presentation.viewmodel.CourseViewModel


@Composable
fun ListCourseScreen(navController: NavController,
                     viewModel: CourseViewModel = hiltViewModel() ){
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    val courses = state.value.course.orEmpty()

    LazyColumn {
        items(courses) {
            CardCourse(course = it)
        }
    }
}