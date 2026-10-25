package com.example.campuscardlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CampusCardScreen()
                }
            }
        }
    }
}

@Composable
fun CampusCardScreen() {
    // Переменная для переключения режимов кнопок в Упражнении 2
    var useSpaceBetween by remember { mutableStateOf(true) }

    // Главный Column на весь экран
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        // Упражнение 6: Если надо проверить SpaceBetween, меняем Top на Arrangement.SpaceBetween
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        // --- УПРАЖНЕНИЕ 1 ---
        // Отдельный Column для заголовка, чтобы выровнять его по центру,
        // пока всё остальное остаётся слева
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Campus Card", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Abror Akhmedov", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Student ID: 2142", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(24.dp)) // Просто отступ

        // --- УПРАЖНЕНИЕ 5 ---
        // Вызвал отдельно функцию профиля (код функции ниже)
        ProfileHeader(name = "Abror Akhmedov", status = "In class · Building B")

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Today",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Блок с расписанием
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // --- УПРАЖНЕНИЕ 4 ---
            // Специально сделал длинное название, чтобы проверить, как сработает weight
            ScheduleRow(
                time = "09:00",
                title = "CS101 Intro to Computer Science and Mobile App Development",
                room = "Lab 3"
            )
            ScheduleRow(time = "11:00", title = "MATH210 Linear Algebra", room = "A-204")
            ScheduleRow(time = "14:00", title = "Soft Skills", room = "Hall 1")
        }

        // Занимаем всё пустое место до низа, чтобы кнопки ушли в самый конец
        Spacer(modifier = Modifier.weight(1f))

        // Чип-кнопка для удобного переключения режимов прямо на эмуляторе
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FilterChip(
                selected = useSpaceBetween,
                onClick = { useSpaceBetween = !useSpaceBetween },
                label = { Text(if (useSpaceBetween) "Режим: SpaceBetween" else "Режим: Center") }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- УПРАЖНЕНИЕ 2 ---
        // Строка с кнопками, изменяет расстановку в зависимости от нажатия на чип
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (useSpaceBetween) {
                Arrangement.SpaceBetween // Кнопки по краям
            } else {
                Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally) // Кнопки по центру с отступом
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { /* Клик по карте */ }) {
                Text("Map")
            }
            Button(onClick = { /* Клик по QR */ }) {
                Text("Show QR")
            }
        }
    }
}

// --- УПРАЖНЕНИЕ 5 ---
// Функция шапки с аватаркой (вынес отдельно, чтобы не засорять главный экран)
@Composable
fun ProfileHeader(name: String, status: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Квадратик вместо картинки
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Column {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Text(text = status, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// --- УПРАЖНЕНИЕ 5 ---
// Вынесенная функция для одной строчки расписания
@Composable
fun ScheduleRow(time: String, title: String, room: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        // --- УПРАЖНЕНИЕ 3 ---
        // Ставлю Alignment.Top, чтобы при крупном времени текст выравнивался по верхней линии
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Время (сделал шрифт побольше - 20.sp для Упражнения 3)
        Text(
            text = time,
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.width(65.dp)
        )

        // --- УПРАЖНЕНИЕ 4 ---
        // Ставлю weight(1f), чтобы название забирало всё свободное место и переносилось,
        // не сдвигая кабинет за экран
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )

        // Кабинет
        Text(
            text = room,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}