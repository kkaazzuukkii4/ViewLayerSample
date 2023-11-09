package jp.co.recruit.r.kazuki_kinoshita.viewlayersample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.co.recruit.r.kazuki_kinoshita.viewlayersample.ui.theme.ViewLayerSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewLayerSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Buttons()
                }
            }
        }
    }
}

@Composable
fun Buttons(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val selectedItem = remember { mutableStateOf(layerList[0]) }

    Column(
        modifier = modifier.padding(all = 8.dp)
    ) {
        Row {
            Button(
                onClick = {
                    Toast.makeText(context, selectedItem.value, Toast.LENGTH_SHORT).show()
                },
            ) {
                Text("AddView")
            }
            DropDownMenu(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically),
                layerList,
                selectedItem
            )
        }
        Button(
            onClick = {
                Toast.makeText(context, "This is toast.", Toast.LENGTH_LONG).show()
            },
        ) {
            Text("ShowToast")
        }
        Button(
            onClick = { /* Do something */ },
        ) {
            Text("ClearAll")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    ViewLayerSampleTheme {
        Buttons()
    }
}

@Composable
fun DropDownMenu(
    modifier: Modifier,
    items: List<String>,
    selectedItem: MutableState<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Row(Modifier.clickable {
            expanded = !expanded
        }) {
            Text(
                text = selectedItem.value,
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown, contentDescription = null
            )
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(text = { Text(item) }, onClick = {
                    expanded = false
                    selectedItem.value = item
                })
            }
        }
    }
}

val layerList = listOf(
    "hoge", "fuga"
)