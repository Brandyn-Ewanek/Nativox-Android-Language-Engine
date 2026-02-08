package com.example.nativox.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star // SAFE STANDARD ICON
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativox.R
import com.example.nativox.ui.theme.*

data class LanguageOption(val name: String, val code: String)

@Composable
fun HomeScreen(
    onLanguageSelected: (String) -> Unit,
    onLibraryClick: () -> Unit
) {
    val languages = listOf(
        LanguageOption("Spanish", "ES"),
        LanguageOption("French", "FR"),
        LanguageOption("Portuguese", "PT"),
        LanguageOption("North American English", "US")
    )

    Scaffold(containerColor = Cream) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. BRAND HEADER
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color.White,
                            shadowElevation = 8.dp,
                            modifier = Modifier.size(100.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Image(
                                    painter = painterResource(id = R.drawable.nativox_logo),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(70.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("Nativox", color = Navy, fontSize = 36.sp, fontWeight = FontWeight.Black, textAlign = TextAlign.Center, letterSpacing = (-1).sp)
                        Text("Master sentence structures and vocabulary with pattern-based drills.", color = Navy.copy(alpha = 0.7f), fontSize = 16.sp, textAlign = TextAlign.Center, lineHeight = 22.sp, modifier = Modifier.padding(top = 12.dp, bottom = 24.dp))
                    }
                }

                // 2. AD PLACEHOLDER
                item { AdPlaceholder() }

                // 3. TITLE
                item {
                    Text("Select Language", color = Navy, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.fillMaxWidth().padding(top = 8.dp), textAlign = TextAlign.Start)
                }

                // 4. LIST
                items(languages) { language ->
                    LanguageCard(language, onClick = { onLanguageSelected(language.name) })
                }
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }

            // FLOATING BUTTON
            ExtendedFloatingActionButton(
                onClick = onLibraryClick,
                containerColor = Navy,
                contentColor = Teal,
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                modifier = Modifier.align(BiasAlignment(0f, 0.85f))
            ) {
                // REPLACED: AutoAwesome -> Star
                Icon(Icons.Default.Star, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "WORD LIBRARY", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun AdPlaceholder() {
    Box(
        modifier = Modifier.fillMaxWidth().height(80.dp).padding(vertical = 8.dp).background(Color.Transparent)
            .drawBehind {
                val stroke = Stroke(width = 4f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f))
                drawRoundRect(color = Color.Gray.copy(alpha = 0.5f), style = stroke, cornerRadius = CornerRadius(16.dp.toPx()))
            },
        contentAlignment = Alignment.Center
    ) {
        Text("AD SPACE RESERVED", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 1.sp)
    }
}

@Composable
fun LanguageCard(language: LanguageOption, onClick: () -> Unit) {
    Card(
        onClick = onClick, colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Neutral), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp), modifier = Modifier.height(90.dp).fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = language.code, fontSize = 28.sp, fontWeight = FontWeight.Black, color = Navy.copy(alpha = 0.8f))
            Spacer(modifier = Modifier.width(24.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = language.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Navy)
            }
            Text(text = "START", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Teal, letterSpacing = 1.sp)
        }
    }
}