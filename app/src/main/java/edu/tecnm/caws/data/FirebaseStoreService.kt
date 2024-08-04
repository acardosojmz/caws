package edu.tecnm.caws.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import edu.tecnm.caws.domain.model.Course
import edu.tecnm.caws.domain.model.Participant
import edu.tecnm.caws.utils.mapSnapshotToCourse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFirestoreService @Inject constructor(private val db : FirebaseFirestore){
    suspend fun addCourse(course: Course) {
        db.collection("courses").add(course).await()
    }

    suspend fun getCourses(): Result<List<Course>>  {
        try {
            val result = db.collection("courses").get().await()
            val courses = ArrayList<Course>()

            result.toList().forEach {
                courses.add(mapSnapshotToCourse(it))
            }
            return (Result.success(courses))
        }catch (e:Exception){
            Log.e("ERROR: ", e.message.toString())
            return (Result.failure(e))
        }
    }


}