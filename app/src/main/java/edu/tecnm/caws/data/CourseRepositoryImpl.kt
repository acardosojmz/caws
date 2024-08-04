package edu.tecnm.caws.data

import edu.tecnm.caws.domain.CourseRepository
import edu.tecnm.caws.domain.model.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val firebaseFirestoreService: FirebaseFirestoreService
) : CourseRepository {
    override suspend fun addCourse(course: Course) {
        firebaseFirestoreService.addCourse(course)
    }

    override suspend fun getCourses(): Flow<Result<List<Course>>> = flow  {
        emit(firebaseFirestoreService.getCourses())
    }

}