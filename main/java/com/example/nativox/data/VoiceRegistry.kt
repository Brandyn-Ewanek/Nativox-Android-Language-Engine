package com.example.nativox.data

object VoiceRegistry {
    fun getPersonaName(language: String, gender: String): String {
        return when (language) {
            "Spanish" -> if (gender == "male") "Mateo" else "Sofia"
            "French" -> if (gender == "male") "Pierre" else "Amélie"
            "Portuguese" -> if (gender == "male") "João" else "Ana"
            "North American English" -> if (gender == "male") "Mike" else "Sarah"
            else -> if (gender == "male") "Male" else "Female"
        }
    }
}