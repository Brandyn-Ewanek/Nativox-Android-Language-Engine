package com.example.nativox.ui

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person // SAFE STANDARD ICON
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativox.data.DrillData
import com.example.nativox.data.DrillType
import com.example.nativox.data.VoiceRegistry
import com.example.nativox.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrillSelectionScreen(
    language: String,
    level: Int,
    onBackClick: () -> Unit,
    onDrillClick: (String, String) -> Unit
) {
    val drills = remember(language) { DrillData.getDrillsFor(language) }

    // 1. PERSISTENCE SETUP
    val context = LocalContext.current
    val sharedPrefs = remember {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    // 2. LOAD SAVED STATE
    var selectedVoice by remember {
        mutableStateOf(sharedPrefs.getString("last_selected_voice", "female") ?: "female")
    }

    // 3. SAVE FUNCTION
    fun saveVoiceSelection(voice: String) {
        selectedVoice = voice
        sharedPrefs.edit().putString("last_selected_voice", voice).apply()
    }

    Scaffold(
        containerColor = Cream,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = language,
                            color = Navy,
                            fontWeight = FontWeight.Black,
                            fontSize = 24.sp
                        )
                        Text(
                            text = "DASHBOARD",
                            color = Teal,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Navy)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Cream)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            // --- THE NEW PERSONA SELECTOR ---
            VoiceSelector(
                language = language,
                currentSelection = selectedVoice,
                onVoiceSelected = { newVoice -> saveVoiceSelection(newVoice) }
            )

            // --- THE DRILL LIST ---
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 32.dp, top = 8.dp)
            ) {
                items(drills) { drill ->
                    DrillCard(
                        drill = drill,
                        onClick = {
                            onDrillClick(drill.id, selectedVoice)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun VoiceSelector(
    language: String,
    currentSelection: String,
    onVoiceSelected: (String) -> Unit
) {
    val maleName = VoiceRegistry.getPersonaName(language, "male")
    val femaleName = VoiceRegistry.getPersonaName(language, "female")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(60.dp)
            .background(Color.White, RoundedCornerShape(30.dp))
            .border(1.dp, Neutral, RoundedCornerShape(30.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // --- MALE OPTION ---
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    if (currentSelection == "male") Navy else Color.Transparent,
                    RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp)
                )
                .clickable { onVoiceSelected("male") },
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // REPLACED: Face6 -> Person (Safe)
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = if (currentSelection == "male") Cream else Navy,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = maleName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = if (currentSelection == "male") Cream else Navy
                    )
                }
            }
        }

        // --- FEMALE OPTION ---
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    if (currentSelection == "female") Navy else Color.Transparent,
                    RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp)
                )
                .clickable { onVoiceSelected("female") },
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // REPLACED: Face3 -> Person (Safe)
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = if (currentSelection == "female") Cream else Navy,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = femaleName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = if (currentSelection == "female") Cream else Navy
                    )
                }
            }
        }
    }
}

@Composable
fun DrillCard(drill: DrillType, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Neutral),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Surface(
                    color = Cream,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(50.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = drill.icon,
                            contentDescription = null,
                            tint = Navy,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = drill.subtitle.uppercase(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Teal,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = drill.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Navy
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = drill.description,
                        fontSize = 14.sp,
                        color = Navy.copy(alpha = 0.7f),
                        lineHeight = 18.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "MASTERY",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Navy.copy(alpha = 0.4f),
                    modifier = Modifier.padding(end = 12.dp)
                )
                repeat(5) { index ->
                    val isFilled = index < drill.masteryLevel
                    val color = if (isFilled) Teal else Neutral.copy(alpha = 0.5f)
                    Box(
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }
        }
    }
}