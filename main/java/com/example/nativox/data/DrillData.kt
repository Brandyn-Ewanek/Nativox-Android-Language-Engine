package com.example.nativox.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.* import androidx.compose.ui.graphics.vector.ImageVector

data class DrillType(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val icon: ImageVector,
    val masteryLevel: Int
)

object DrillData {

    fun getDrillsFor(language: String): List<DrillType> {
        return when (language) {
            "Spanish" -> spanishDrills
            "French" -> frenchDrills
            "Portuguese" -> portugueseDrills
            else -> englishDrills
        }
    }

    // --- ENGLISH (Default) ---
    private val englishDrills = listOf(
        // Ladder -> List (Safe)
        DrillType("ladder", "The Pronoun Ladder", "Conjugation Drill", "Teaching how the verb changes based on who is doing it.", Icons.Default.List, 3),
        // Swap -> Refresh (Safe, implies cycling/swapping)
        DrillType("swap", "The Item Swap", "Vocabulary Drill", "Drilling the sentence structure while rotating nouns.", Icons.Default.Refresh, 5),
        // Pyramid -> Add (Safe, implies building up)
        DrillType("pyramid", "The Pyramid", "Expansion Drill", "Building long sentences from short ones step-by-step.", Icons.Default.Add, 2),
        // Transformer -> Edit (Safe, implies changing/modifying)
        DrillType("transformer", "The Transformer", "Negation & Questions", "Take one concept and flip it through its four modes.", Icons.Default.Edit, 1),
        // Time Traveler -> ArrowForward (Safe, implies moving time)
        DrillType("timetraveler", "The Time Traveler", "Tense Shift", "Agility in moving between Past, Present, and Future.", Icons.Default.ArrowForward, 0),
        // Contrarian -> Close (Safe, implies 'No' or 'Wrong')
        DrillType("contrarian", "The Contrarian", "Correction Drill", "The app makes a false statement, you must correct it.", Icons.Default.Close, 0),
        // Interrogator -> Info (Safe, asking for info)
        DrillType("interrogator", "The Interrogator", "The 5 W's", "Asking for information: Who, What, Where, When, Why.", Icons.Default.Info, 0),
        // Crisis -> Warning (Safe)
        DrillType("crisis", "The Crisis Manager", "Escalation Drill", "Urgent needs and emotions. A story that gets progressively worse.", Icons.Default.Warning, 0)
    )

    // --- SPANISH ---
    private val spanishDrills = listOf(
        DrillType("ladder", "Escalera de Pronombres", "Conjugación", "Aprende cómo cambia el verbo según quién lo hace.", Icons.Default.List, 3),
        DrillType("swap", "Intercambio de Ítems", "Vocabulario", "Practica la estructura mientras rotas los sustantivos.", Icons.Default.Refresh, 5),
        DrillType("pyramid", "La Pirámide", "Expansión", "Construye oraciones largas paso a paso.", Icons.Default.Add, 2),
        DrillType("transformer", "El Transformador", "Negación y Preguntas", "Cambia un concepto a sus cuatro modos.", Icons.Default.Edit, 1),
        DrillType("timetraveler", "Viajero del Tiempo", "Tiempos Verbales", "Agilidad para moverte entre Pasado, Presente y Futuro.", Icons.Default.ArrowForward, 0),
        DrillType("contrarian", "El Contrario", "Corrección", "La app miente, tú corriges la afirmación.", Icons.Default.Close, 0),
        DrillType("interrogator", "El Interrogador", "Las 5 Preguntas", "Quién, Qué, Dónde, Cuándo, Por qué.", Icons.Default.Info, 0),
        DrillType("crisis", "Gestor de Crisis", "Escalada", "Necesidades urgentes. Una historia que empeora.", Icons.Default.Warning, 0)
    )

    // --- FRENCH ---
    private val frenchDrills = listOf(
        DrillType("ladder", "L'Échelle des Pronoms", "Conjugaison", "Apprenez comment le verbe change selon le sujet.", Icons.Default.List, 3),
        DrillType("swap", "L'Échange d'Objets", "Vocabulaire", "Pratiquez la structure en changeant les noms.", Icons.Default.Refresh, 5),
        DrillType("pyramid", "La Pyramide", "Expansion", "Construisez de longues phrases étape par étape.", Icons.Default.Add, 2),
        DrillType("transformer", "Le Transformateur", "Négation et Questions", "Changez un concept à travers ses quatre modes.", Icons.Default.Edit, 1),
        DrillType("timetraveler", "Voyageur Temporel", "Temps Verbaux", "Agilité entre le Passé, le Présent et le Futur.", Icons.Default.ArrowForward, 0),
        DrillType("contrarian", "Le Contradicteur", "Correction", "L'appli ment, vous devez corriger l'affirmation.", Icons.Default.Close, 0),
        DrillType("interrogator", "L'Interrogateur", "Les 5 Questions", "Qui, Quoi, Où, Quand, Pourquoi.", Icons.Default.Info, 0),
        DrillType("crisis", "Gestion de Crise", "Escalade", "Besoins urgents. Une histoire qui empire.", Icons.Default.Warning, 0)
    )

    // --- PORTUGUESE ---
    private val portugueseDrills = listOf(
        DrillType("ladder", "Escada de Pronomes", "Conjugação", "Aprenda como o verbo muda dependendo de quem faz.", Icons.Default.List, 3),
        DrillType("swap", "Troca de Itens", "Vocabulário", "Pratique a estrutura enquanto troca os substantivos.", Icons.Default.Refresh, 5),
        DrillType("pyramid", "A Pirâmide", "Expansão", "Construa frases longas passo a passo.", Icons.Default.Add, 2),
        DrillType("transformer", "O Transformador", "Negação e Perguntas", "Mude um conceito através de seus quatro modos.", Icons.Default.Edit, 1),
        DrillType("timetraveler", "Viajante do Tempo", "Tempos Verbais", "Agilidade entre Passado, Presente e Futuro.", Icons.Default.ArrowForward, 0),
        DrillType("contrarian", "O Contrário", "Correção", "O app mente, você deve corrigir a afirmação.", Icons.Default.Close, 0),
        DrillType("interrogator", "O Interrogador", "As 5 Perguntas", "Quem, O Que, Onde, Quando, Por Que.", Icons.Default.Info, 0),
        DrillType("crisis", "Gestor de Crise", "Escalada", "Necessidades urgentes. Uma história que piora.", Icons.Default.Warning, 0)
    )
}