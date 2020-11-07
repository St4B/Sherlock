package com.quadible.appcompose

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionReference
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.quadible.appcompose.ui.SherlockTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SherlockTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SherlockTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    SherlockTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun AnotherPreview() {
    SherlockTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun AnotherPreview2() {
    SherlockTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun AnotherPreview3() {
    SherlockTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun AnotherPreview4() {
    SherlockTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun AnotherPreview5() {
    SherlockTheme {
        Greeting("Android")
    }
}

