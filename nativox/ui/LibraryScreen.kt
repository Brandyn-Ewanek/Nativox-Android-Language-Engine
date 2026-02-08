package com.example.nativox.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.nativox.R
import com.example.nativox.ui.theme.*

// Simple Data Model
data class LibraryWord(val word: String, val definition: String, val dateSeen: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(onBackClick: () -> Unit) {
    // 1. State for opening books
    var openBookType by remember { mutableStateOf<String?>(null) }

    // 2. Simple Mock Data (The "Simple" part)
    val nouns = listOf(
        LibraryWord("Pão", "Bread", "Dec 20"),
        LibraryWord("Casa", "House", "Dec 21")
    )
    val verbs = listOf(
        LibraryWord("Comer", "To Eat", "Dec 22"),
        LibraryWord("Falar", "To Speak", "Dec 23")
    )
    val objects = listOf(
        LibraryWord("Mesa", "Table", "Dec 24"),
        LibraryWord("Carro", "Car", "Dec 25")
    )

    Scaffold(
        containerColor = Cream,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("MY LIBRARY", fontWeight = FontWeight.Black, color = Navy) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Navy)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Cream)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // BOOK 1: NOUNS
            LibraryBookItem("NOUNS", BrandOrange) { openBookType = "Nouns" }
            Spacer(modifier = Modifier.height(16.dp))

            // BOOK 2: VERBS
            LibraryBookItem("VERBS", BrandPurple) { openBookType = "Verbs" }
            Spacer(modifier = Modifier.height(16.dp))

            // BOOK 3: OBJECTS
            LibraryBookItem("OBJECTS", Teal) { openBookType = "Objects" }
        }
    }

    // 3. The Popup List
    if (openBookType != null) {
        val words = when(openBookType) { "Nouns" -> nouns; "Verbs" -> verbs; else -> objects }
        BookContentDialog(openBookType!!, words) { openBookType = null }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryBookItem(title: String, color: Color, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth().height(120.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, fontSize = 28.sp, color = Color.White, fontWeight = FontWeight.Black)
            // Icon Placeholder
            Icon(painter = painterResource(id = R.drawable.nativox_logo), contentDescription = null, tint = Color.White.copy(0.5f), modifier = Modifier.size(50.dp))
        }
    }
}

@Composable
fun BookContentDialog(title: String, words: List<LibraryWord>, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Cream),
            modifier = Modifier.fillMaxWidth().height(500.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Navy)
                    IconButton(onClick = onDismiss) { Icon(Icons.Default.Close, "Close", tint = Navy) }
                }
                HorizontalDivider(color = Navy, thickness = 2.dp)
                LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
                    items(words) { item ->
                        Column(modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(item.word, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Navy)
                            Text(item.definition, fontSize = 14.sp, color = Navy.copy(0.7f))
                            HorizontalDivider(color = Navy.copy(0.1f), modifier = Modifier.padding(top = 8.dp))
                        }
                    }
                }
            }
        }
    }
}