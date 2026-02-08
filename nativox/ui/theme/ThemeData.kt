package com.example.nativox.ui.theme

import androidx.compose.ui.graphics.Color

data class ThemeType(
    val id: String,
    val title: String,
    val emoji: String,
    val description: String,
    val vibeColor: Color
)

object ThemeData {

    fun getThemesFor(language: String): List<ThemeType> {
        return when (language) {
            "Spanish" -> spanishThemes
            "French" -> frenchThemes
            "Portuguese" -> portugueseThemes
            else -> englishThemes
        }
    }

    // --- ENGLISH ---
    private val englishThemes = listOf(
        ThemeType("date", "The Disastrous Date", "💔", "Awkward, cringey, and dramatic romance.", CardPurpleTint),
        ThemeType("tourist", "The Honest Tourist", "✈️", "Tired, hungry, and confused travel survival.", CardTealTint),
        ThemeType("office", "The Office Zombie", "🧟", "Bored, cynical work life.", CardGreyTint),
        ThemeType("health", "The Hypochondriac", "🤒", "Dramatic and urgent health problems.", CardGreenTint),
        ThemeType("gossip", "The Gossiping Neighbor", "👀", "Nosy, judgmental, and whispered secrets.", CardPeachTint),
        ThemeType("toddler", "Toddler Logic", "👶", "Simple, direct, and demanding basic needs.", CardYellowTint),
        ThemeType("chef", "The Bad Chef", "🍳", "Culinary disasters and picky eating.", CardOrangeTint),
        ThemeType("gym", "The Gym Bro", "💪", "Intense, repetitive self-improvement.", CardBlueTint)
    )

    // --- SPANISH ---
    private val spanishThemes = listOf(
        ThemeType("date", "Cita Desastrosa", "💔", "Romance incómodo, vergonzoso y dramático.", CardPurpleTint),
        ThemeType("tourist", "Turista Honesto", "✈️", "Cansado, hambriento y confundido.", CardTealTint),
        ThemeType("office", "Zombi de Oficina", "🧟", "Vida laboral aburrida y cínica.", CardGreyTint),
        ThemeType("health", "El Hipocondríaco", "🤒", "Problemas de salud dramáticos y urgentes.", CardGreenTint),
        ThemeType("gossip", "Vecino Chismoso", "👀", "Secretos susurrados y juiciosos.", CardPeachTint),
        ThemeType("toddler", "Lógica de Niño", "👶", "Necesidades básicas simples y exigentes.", CardYellowTint),
        ThemeType("chef", "El Mal Cocinero", "🍳", "Desastres culinarios y comida rara.", CardOrangeTint),
        ThemeType("gym", "El Fanático del Gym", "💪", "Auto-mejora intensa y repetitiva.", CardBlueTint)
    )

    // --- FRENCH ---
    private val frenchThemes = listOf(
        ThemeType("date", "Rendez-vous Désastreux", "💔", "Romance gênante et dramatique.", CardPurpleTint),
        ThemeType("tourist", "Touriste Honnête", "✈️", "Fatigué, affamé et confus.", CardTealTint),
        ThemeType("office", "Zombie de Bureau", "🧟", "Vie professionnelle ennuyeuse et cynique.", CardGreyTint),
        ThemeType("health", "L'Hypocondriaque", "🤒", "Problèmes de santé dramatiques.", CardGreenTint),
        ThemeType("gossip", "Voisin Commère", "👀", "Secrets chuchotés et jugements.", CardPeachTint),
        ThemeType("toddler", "Logique de Bambin", "👶", "Besoins fondamentaux simples et exigeants.", CardYellowTint),
        ThemeType("chef", "Le Mauvais Chef", "🍳", "Désastres culinaires.", CardOrangeTint),
        ThemeType("gym", "Le Go-Muscu", "💪", "Amélioration de soi intense.", CardBlueTint)
    )

    // --- PORTUGUESE ---
    private val portugueseThemes = listOf(
        ThemeType("date", "Encontro Desastroso", "💔", "Romance estranho e dramático.", CardPurpleTint),
        ThemeType("tourist", "Turista Honesto", "✈️", "Cansado, com fome e confuso.", CardTealTint),
        ThemeType("office", "Zumbi do Escritório", "🧟", "Vida de trabalho entediada e cínica.", CardGreyTint),
        ThemeType("health", "O Hipocondríaco", "🤒", "Problemas de saúde dramáticos.", CardGreenTint),
        ThemeType("gossip", "Vizinho Fofoqueiro", "👀", "Segredos sussurrados ejulgamentos.", CardPeachTint),
        ThemeType("toddler", "Lógica de Criança", "👶", "Necessidades básicas simples e exigentes.", CardYellowTint),
        ThemeType("chef", "O Mau Cozinheiro", "🍳", "Desastres culinários.", CardOrangeTint),
        ThemeType("gym", "Rato de Academia", "💪", "Auto-aperfeiçoamento intenso.", CardBlueTint)
    )
}