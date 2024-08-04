package edu.tecnm.caws.domain.model

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = ""
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name,
            "email" to email
        )
    }
}