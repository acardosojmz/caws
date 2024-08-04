package edu.tecnm.caws.utils

import com.google.firebase.firestore.QueryDocumentSnapshot
import edu.tecnm.caws.domain.model.Course
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun mapSnapshotToCourse(snapshot: QueryDocumentSnapshot): Course {
    val title = snapshot.getString("title") ?: ""
    val description = snapshot.getString("description") ?: ""
    val place = snapshot.getString("place") ?: ""

    val initialDateString = snapshot.getString("initialdate") ?: ""
    val finalDateString = snapshot.getString("finaldate") ?: ""

    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val initialdate = try {
        LocalDate.parse(initialDateString, dateFormatter)
    } catch (e: Exception) {
        null
    }

    val finaldate = try {
        LocalDate.parse(finalDateString, dateFormatter)
    } catch (e: Exception) {
        null
    }

    return Course(title, description, initialdate, finaldate, place)
}