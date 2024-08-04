package edu.tecnm.caws.domain

import edu.tecnm.caws.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun getCourses(): Flow<Result<List<Course>>>
    suspend fun addCourse(course: Course)
}