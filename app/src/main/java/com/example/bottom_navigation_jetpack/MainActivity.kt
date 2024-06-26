package com.example.bottom_navigation_jetpack

import android.annotation.SuppressLint
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottom_navigation_jetpack.ui.theme.BottomnavigationJetpackTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomnavigationJetpackTheme {
                val navController = rememberNavController()


                Scaffold(
                    bottomBar = {
                        BottomNavMenu(
                            navController=navController,
                            items = listOf(
                                BottomMenuContent(
                                    title="Home",
                                    route = "home",
                                    iconId = ImageVector.vectorResource(id = R.drawable.ic_home)
                                ),
                                BottomMenuContent(
                                    title="Notification",
                                    route = "notification",
                                    iconId = ImageVector.vectorResource(id = R.drawable.baseline_notifications_none_24),
                                    badgeCount = 13),

                                BottomMenuContent(
                                    title="Settings",
                                    route = "settings",
                                    iconId = ImageVector.vectorResource(id = R.drawable.baseline_settings_24))
                            ),
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    },
                    containerColor = Color.White
                ) {
                    Navigation(navController = navController)
                }


            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "home"){
        composable("home") { HomeScreen() }
        composable("notification") { ProfileScreen() }
        composable("settings") { SettingsScreen() }
    }
}


@Composable
fun BottomNavMenu(
    items:List<BottomMenuContent>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomMenuContent) -> Unit
) {


    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier
            .fillMaxWidth(),
        containerColor = Color.LightGray,
        contentColor = Color.Black
    ){
        items.forEach {item->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.Green,
                    selectedTextColor = Color.White,
                    selectedIndicatorColor = Color.Gray,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Black,
                    disabledIconColor = Color.Black,
                    disabledTextColor = Color.Black,
                ),
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 15.dp),
                    ) {
                        if(item.badgeCount > 0){
                            BadgedBox(
                                badge = {
                                    Badge(
                                    modifier = Modifier,
                                    containerColor  = BadgeDefaults.containerColor,
                                    contentColor = contentColorFor(containerColor),
                                    content = {
                                        Text(text = item.badgeCount.toString())
                                    })
                                }
                            ) {
                                Icon(
                                    imageVector = item.iconId,
                                    contentDescription = item.title,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        } else {
                            Icon(
                                imageVector = item.iconId,
                                contentDescription = item.title,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                        if(selected){
                            Text(
                                text = item.title,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }

                    }
                }
            )
        }

    }
}


@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Home",
        )
    }
}

@Composable
fun ProfileScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Text(text = "Profile")
    }
}

@Composable
fun SettingsScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Settings")
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    Navigation(navController = navController)
}