package edu.tecnm.caws.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.tecnm.caws.domain.model.Course
import edu.tecnm.caws.domain.usecase.CourseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private  val courseUseCase: CourseUseCase): ViewModel()
{
    private val _uiState = MutableStateFlow(CourseState())
    val uiState: StateFlow<CourseState> = _uiState.asStateFlow()
    init {
        getCourses()
    }
    private fun getCourses(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            val result = courseUseCase.getCourses().first().map { it.toList() }
            if (result.isSuccess) {
                val courses = result.getOrNull()
                _uiState.value = _uiState.value.copy(
                course= courses, loading = false,
                success = true, message = "Retrieve successfully... ")
            } else {
                _uiState.value = _uiState.value.copy(
                    course= null, loading = false,
                    success = false,
                    message = "Could not retrieve course list ")
            }
        }
    }
}

data class CourseState(
    val loading: Boolean = false,
    val course: List<Course>? = null,
    val success: Boolean = false,
    val message: String=""
)