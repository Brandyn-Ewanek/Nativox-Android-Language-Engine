package com.example.nativox.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativox.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.sin
import kotlin.random.Random

data class Token(
    val word: String,
    val startTime: Long,
    val endTime: Long
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PracticeScreen(
    language: String,
    drillId: String,
    themeId: String,
    voiceId: String,
    onBackClick: () -> Unit
) {
    // --- MOCK DATA ---
    val targetSentence = "El bebé vende la casa."
    val tokens = listOf(
        Token("El", 0, 400),
        Token("bebé", 400, 1100),
        Token("vende", 1100, 1800),
        Token("la", 1800, 2100),
        Token("casa.", 2100, 2800)
    )
    val totalDuration = 2800L

    // --- STATE ---
    var isPlaying by remember { mutableStateOf(false) }
    var currentPlaybackTime by remember { mutableLongStateOf(0L) }

    // THE MAGIC VARIABLE: Tracks how "loud" the voice is (0.0 to 1.0)
    var currentLoudness by remember { mutableFloatStateOf(0f) }

    val scope = rememberCoroutineScope()

    // --- AUDIO LOGIC ---
    fun playAudio() {
        if (isPlaying) {
            isPlaying = false
            currentLoudness = 0f
            return
        }

        scope.launch {
            isPlaying = true
            currentPlaybackTime = 0L
            val startTime = System.currentTimeMillis()

            while (currentPlaybackTime < totalDuration && isPlaying) {
                currentPlaybackTime = System.currentTimeMillis() - startTime

                // SIMULATION: Generate random loudness
                // This makes the line vibrate and the text pulse!
                currentLoudness = Random.nextFloat() * 0.8f + 0.2f

                delay(50)
            }

            // Reset
            isPlaying = false
            currentPlaybackTime = 0L
            currentLoudness = 0f
        }
    }

    Scaffold(
        containerColor = Cream,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("VERB: SELL", color = Teal, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
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
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // 1. THE HYBRID VISUALIZER
            // It has BOTH the "Tracer" gradient AND the "Loudness" vibration
            AudioVisualizer(
                isPlaying = isPlaying,
                loudness = if (isPlaying) currentLoudness else 0f,
                progress = if (totalDuration > 0) currentPlaybackTime.toFloat() / totalDuration else 0f,
                modifier = Modifier.padding(vertical = 20.dp).fillMaxWidth().height(80.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 2. THE KARAOKE DISPLAY
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center
            ) {
                tokens.forEach { token ->
                    val isActive = isPlaying &&
                            currentPlaybackTime >= token.startTime &&
                            currentPlaybackTime < token.endTime

                    // ANIMATED TEXT COLOR logic from your old file
                    // If active, it pulses between Purple and Orange based on loudness
                    val targetColor = if (isActive) {
                        lerp(BrandPurple, BrandOrange, currentLoudness)
                    } else {
                        Navy
                    }

                    val animatedColor by animateColorAsState(
                        targetValue = targetColor,
                        animationSpec = tween(150),
                        label = "color"
                    )

                    Text(
                        text = token.word + " ",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = animatedColor,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // 3. THE PLAY BUTTON
            Button(
                onClick = { playAudio() },
                colors = ButtonDefaults.buttonColors(containerColor = BrandOrange),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(60.dp)
                    .width(200.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Close else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isPlaying) "Stop" else "Listen",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 4. METADATA CARD
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Neutral),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("GRAMMAR BREAKDOWN", color = Navy, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("SUBJECT", fontSize = 10.sp, color = Teal, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            SuggestionChip(onClick = {}, label = { Text("The Baby") })
                        }
                        Column {
                            Text("NOUNS", fontSize = 10.sp, color = Teal, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            SuggestionChip(onClick = {}, label = { Text("House") })
                        }
                    }
                }
            }

            // FEEDBACK ICONS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Warning, contentDescription = "Hard", tint = Neutral)
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Check, contentDescription = "Easy", tint = Navy)
                }
            }
        }
    }
}

// --- THE ULTIMATE VISUALIZER ---
// Combines "Tracer Gradient" + "Loudness Vibration"
@Composable
fun AudioVisualizer(
    isPlaying: Boolean,
    loudness: Float,
    progress: Float,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val centerY = height / 2f

        val path = Path()
        val points = 60
        val stepX = width / points

        path.moveTo(0f, centerY)

        for (i in 0..points) {
            val x = i * stepX

            // 1. LOUDNESS CONTROL:
            // The wave height is controlled by 'loudness' (from your old file)
            // This makes it vibrate/jump realistically.
            val baseAmplitude = if (isPlaying) 25.dp.toPx() * loudness else 2.dp.toPx()

            val yOffset = sin(i * 0.8f) * baseAmplitude +
                    sin(i * 3f) * (baseAmplitude * 0.5f)

            path.lineTo(x, centerY + yOffset)
        }

        // 2. TRACER CONTROL:
        // The color is controlled by 'progress' (from the new design)
        val brush = if (!isPlaying) {
            Brush.linearGradient(listOf(BrandPurple, BrandPurple))
        } else {
            val start = (progress - 0.1f).coerceAtLeast(0.0f)
            val end = (progress + 0.1f).coerceAtMost(1.0f)

            Brush.horizontalGradient(
                0.0f to BrandPurple,
                start to BrandPurple,
                progress to BrandOrange, // The bright center follows the audio
                end to BrandPurple,
                1.0f to BrandPurple
            )
        }

        drawPath(
            path = path,
            brush = brush,
            style = Stroke(
                width = 8.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}