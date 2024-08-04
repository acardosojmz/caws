package edu.tecnm.caws.domain.usecase

import edu.tecnm.caws.domain.CourseRepository
import edu.tecnm.caws.domain.model.Course
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository )
{
    suspend fun addCourse(course: Course) {
        courseRepository.addCourse(course)
    }

    suspend fun getCourses(): Flow<Result<List<Course>>> {
        return courseRepository.getCourses()
    }
}