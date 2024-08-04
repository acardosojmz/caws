package edu.tecnm.caws.data

import com.google.firebase.firestore.FirebaseFirestore
import edu.tecnm.caws.domain.model.Participant
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFirestoreService @Inject constructor(private val db : FirebaseFirestore){
    suspend fun addParticipant(participant: Participant) {
        db.collection("participants").add(participant).await()
    }

    suspend fun getParticipants(): List<Participant> {
        val result = db.collection("participants").get().await()
        return result.toObjects(Participant::class.java)
    }
}