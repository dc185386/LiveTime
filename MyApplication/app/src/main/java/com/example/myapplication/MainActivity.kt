package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Test1()
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
@Composable
fun Test() {
    var dateTimeString = "2021-10-05T10:00:30.0000000"
    var dateTimeString1 = "2024-02-29T11:21:33.672845+05:30"
    var date = LocalDate.parse(dateTimeString1, DateTimeFormatter.ISO_DATE_TIME)
    var time = LocalTime.parse(dateTimeString1, DateTimeFormatter.ISO_DATE_TIME)
    Column {
        Text(text = date.toString())
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = time.toString())
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = LocalDate.now().toString())
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = LocalTime.now().toString())
        Spacer(modifier = Modifier.padding(10.dp))
//        var date2 = LocalDate.now()
//        var time2 = LocalTime.now()
//        val date11 = LocalDateTime.of(2023, 3, 8, 12, 0, 0)
        val date1 = LocalDateTime.of(date.year,date.month,date.dayOfMonth,time.hour,time.minute,time.second)
        val epochTime = date1.toEpochSecond(ZoneOffset.UTC)
//        val date11 = LocalDateTime.of(date2.year,date2.month,date2.dayOfMonth,time2.hour,time2.minute,time2.second)
//        val epochTime2 = date2.toEpochSecond(ZoneOffset.UTC)
        Text(text = epochTime.toString())
        Spacer(modifier = Modifier.padding(10.dp))

        val epochTimeInMillis: Long = epochTime // Epoch time in milliseconds
        val minutes = epochTimeInMillis / 1000 / 60
        val seconds = (epochTimeInMillis / 1000) % 60
        Text(text = "$minutes minutes")
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "$seconds seconds")
        Spacer(modifier = Modifier.padding(10.dp))
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Test1() {
    val dateTimeString = "2024-03-08T22:39:33.672845+05:30"
    val date = LocalDate.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME)
    val time = LocalTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME)

    
    val curDate = remember {
        mutableStateOf(LocalDate.now())
    }
    val curTime = remember {
        mutableStateOf(LocalTime.now())
    }
    val curDate1 = remember {
        mutableStateOf(date)
    }
    val curTime1 = remember {
        mutableStateOf(time)
    }

    curDate.value = LocalDate.parse("${LocalDate.now()}T${LocalTime.now()}", DateTimeFormatter.ISO_DATE_TIME)
    curTime.value = LocalTime.parse("${LocalDate.now()}T${LocalTime.now()}", DateTimeFormatter.ISO_DATE_TIME)


    val t = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = t) {
        while (true)
        {
            t.intValue += 1
            curDate.value = LocalDate.parse("${LocalDate.now()}T${LocalTime.now()}", DateTimeFormatter.ISO_DATE_TIME)
            curTime.value = LocalTime.parse("${LocalDate.now()}T${LocalTime.now()}", DateTimeFormatter.ISO_DATE_TIME)
            delay(1000)
        }
    }

    Column {

        Text(text = t.intValue.toString())
        Spacer(modifier = Modifier.padding(5.dp))
        val curEpoch = remember {
            mutableLongStateOf(0)
        }
        val epoch = remember {
            mutableLongStateOf(0)
        }
        val diffEpoch = remember {
            mutableLongStateOf(0)
        }

        curEpoch.value = Test3(curDate = curDate1.value, curTime = curTime1.value)
        Spacer(modifier = Modifier.padding(5.dp))
        epoch.value = Test3(curDate = curDate.value, curTime = curTime.value)
        diffEpoch.value = epoch.value - curEpoch.value
        Text(text = "diff = ${diffEpoch.value}")
        Spacer(modifier = Modifier.padding(5.dp))

        LiveTime(diffEpoch)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Test3(curDate: LocalDate,curTime: LocalTime): Long {

//    Text(text = curDate.toString())
//    Spacer(modifier = Modifier.padding(10.dp))
//
//    Text(text = curTime.toString())
//    Spacer(modifier = Modifier.padding(10.dp))

    val dt = LocalDateTime.of(curDate.year,curDate.month,curDate.dayOfMonth,curTime.hour,curTime.minute,curTime.second)
    val epochTime = dt.toEpochSecond(ZoneOffset.UTC)
//    Text(text = "${curDate.year} ${curDate.month} ${curDate.dayOfMonth} ${curTime.hour} ${curTime.minute} ${curTime.second}")
    Spacer(modifier = Modifier.padding(10.dp))
    Text(text = epochTime.toString())
    Spacer(modifier = Modifier.padding(10.dp))
    return epochTime
//    val epochTimeInMillis: Long = epochTime // Epoch time in milliseconds
//    val minutes = epochTimeInMillis / 1000 / 60
//    val seconds = (epochTimeInMillis / 1000) % 60
//
//    Text(text = "$minutes : $seconds")
//    Spacer(modifier = Modifier.padding(10.dp))

//    Text(text = "$seconds seconds")
//    Spacer(modifier = Modifier.padding(10.dp))

}

@Composable
fun LiveTime(epochTime: MutableState<Long>) {
    val epochTimeInMillis: Double = (epochTime.value).toDouble() // Epoch time in milliseconds

    val minutes = remember {
        mutableDoubleStateOf(0.0)
    }
    val seconds = remember {
        mutableDoubleStateOf(0.0)
    }
    minutes.doubleValue = epochTimeInMillis / 60
    seconds.doubleValue = epochTimeInMillis%60
    Spacer(modifier = Modifier.padding(10.dp))
    Text(text = "${minutes.doubleValue.toInt()} : ${seconds.doubleValue.toInt()}")
    Spacer(modifier = Modifier.padding(10.dp))
}