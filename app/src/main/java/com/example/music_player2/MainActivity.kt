package com.example.music_player2

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.music_player2.ui.theme.Music_player2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Music_player2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }

                Scaffold(
                    // in scaffold we are specifying top bar.
                    topBar = {
                        // inside top bar we are specifying background color.
                        TopAppBar(backgroundColor = Color.Blue,
                            // along with that we are specifying title for our top bar.
                            title = {
                                // in the top bar we are specifying tile as a text
                                Text(
                                    // on below line we are specifying
                                    // text to display in top app bar.
                                    text = "Music Streaming Application",

                                    // on below line we are specifying
                                    // modifier to fill max width.
                                    modifier = Modifier.fillMaxWidth(),
                                    //Nice ra Ballu
//                                    tinnava ra
                                    
                                    // on below line we are specifying text alignment.
                                    textAlign = TextAlign.Center,

                                    // on below line we are specifying color for our text.
                                    color = Color.White
                                )
                            }
                        )
                    }
                ) {
                    // on below line we are calling grid
                    // view method to load our grid view.
                        mainApp()

                }

            }
        }
    }
}
@Composable
fun mainApp(){
    Column(
        modifier = Modifier
            .padding(2.dp)
            .background(Color.Green, shape = RectangleShape)
            .fillMaxSize()
    ){
        gridView(LocalContext.current)
    }
}
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class)
@Composable
fun gridView(context: Context) {
//    intialization of variables
    val mMediaPlayer = MediaPlayer.create(context, R.raw.madhu)
    var a by remember {
        mutableStateOf(mMediaPlayer)
    }
    var is_selected by remember {
        mutableStateOf(false)
    }
    var song by remember {
        mutableStateOf(0)
    }
//    songs in the app code
    lateinit var songsList: List<GridModal>
    songsList = ArrayList()

    songsList = songsList + GridModal(
        "Yenno ratrulu vasthai",
        R.drawable.ami,
        MediaPlayer.create(context, R.raw.amigo)
    )
    songsList = songsList + GridModal(
        "chimiki chimiki",
        R.drawable.chim,
        MediaPlayer.create(context, R.raw.chim)
    )
    songsList = songsList + GridModal(
        "Gudellona Gudellona",
        R.drawable.gude,
        MediaPlayer.create(context, R.raw.ori)
    )
    songsList = songsList + GridModal(
        "O Madhu O Madhu",
        R.drawable.jul,
        MediaPlayer.create(context, R.raw.madhu)
    )
    songsList = songsList + GridModal(
        "nuvvu Vasthanu ante ",
        R.drawable.nv,
        MediaPlayer.create(context, R.raw.nv)
    )
    songsList = songsList + GridModal(
        " Dhosthi ",
        R.drawable.rrr,
        MediaPlayer.create(context, R.raw.dt)
    )
    songsList = songsList + GridModal(
        "Masteraru masteraru",
        R.drawable.sirs,
        MediaPlayer.create(context, R.raw.sir)
    )
    songsList = songsList + GridModal(
        "   Nachavule  ",
        R.drawable.vir,
        MediaPlayer.create(context, R.raw.viru)
    )
    songsList = songsList + GridModal(
        " Chodaga  ",
        R.drawable.cho,
        MediaPlayer.create(context, R.raw.choda)
    )
    songsList = songsList + GridModal(
        "Deva Deva ",
        R.drawable.dev,
        MediaPlayer.create(context, R.raw.devaa)
    )
    songsList = songsList + GridModal(
        "Maan meri Jaan",
        R.drawable.man,
        MediaPlayer.create(context, R.raw.jaanss)
    )
    songsList = songsList + GridModal(
        "Kesariya tera",
        R.drawable.kes,
        MediaPlayer.create(context, R.raw.kesa)
    )
//    Icon Symbol to stop
    Row(
        modifier = Modifier.padding(1.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {
            song--
            if (song <= 0) {
                song = songsList.size-1
            }
            a.reset()
            a = songsList[song].song
            a.start()
        }) {
            Icon(
                painterResource(id = R.drawable.ic_outline_fast_rewind_24),
                contentDescription = "Backward"
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = {
            if (!is_selected) {
                is_selected = true
                Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
            } else {
                is_selected = false
                Toast.makeText(context, "pause", Toast.LENGTH_SHORT).show()
            }
        }) {
            if (is_selected) {
                a.start()
                Icon(
                    painterResource(id = R.drawable.ic_baseline_pause_24),
                    contentDescription = "Pause",
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color.Yellow)
                )
            } else {
                stop(a)
                Icon(
                    painterResource(id = R.drawable.ic_baseline_play_arrow_24),
                    contentDescription = "Play",
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color.Yellow)
                )
            }

        }
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = {

            song++
            if (song >= songsList.size) {
                song = 0
            }
            a.reset()
            a = songsList[song].song
            a.start()
        }) {
            Icon(
                painterResource(id = R.drawable.ic_outline_fast_forward_24),
                contentDescription = "Forward"
            )
        }
    }
    Spacer(modifier =  Modifier.height(10.dp))

//     it checks whether the song is Completed or not


    a.setOnCompletionListener { mp->
            song++
        if (song>=songsList.size){
            song=0
        }
        a.reset()
        a=songsList[song].song
        a.start()
    }

        LazyVerticalGrid(
            cells = GridCells.Fixed(2), modifier = Modifier
                .padding(10.dp)
                .background(color = Color.Cyan)
        ) {
            items(songsList.size) {
                Card(
                    onClick = {
                        Toast.makeText(
                            context,
                            songsList[it].songName + "Playing",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        stop(a)
                        songsList[it].song.start()
                        a = songsList[it].song
                        is_selected=true
                        song= songsList.indexOf(songsList[it])
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.Yellow),
                    elevation = 12.dp
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .background(Color.Yellow)
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.center
                    ) {
                        Image(
                            painter = painterResource(id = songsList[it].songImg),
                            contentDescription = "Song section",
                            modifier = Modifier
                                .height(100.dp)
                                .width(160.dp)
                                .padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(9.dp))
                        Text(
                            text = songsList[it].songName,
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black
                        )

                    }

                }
            }

        }
        }
private fun stop(am:MediaPlayer) {
    am.pause()
}

@Preview(showBackground = true, showSystemUi = true )
@Composable
fun DefaultPreview() {
    Music_player2Theme {
        mainApp()
    }
}
