package com.rhbekti.yobo

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.identity.Identity
import com.rhbekti.yobo.ui.navigation.NavigationItem
import com.rhbekti.yobo.ui.navigation.Screen
import com.rhbekti.yobo.ui.screen.detail.DetailScreen
import com.rhbekti.yobo.ui.screen.home.HomeScreen
import com.rhbekti.yobo.ui.screen.profile.ProfileScreen
import com.rhbekti.yobo.ui.screen.search.SearchScreen
import com.rhbekti.yobo.ui.screen.signIn.GoogleAuthUiClient
import com.rhbekti.yobo.ui.screen.signIn.SignInScreen
import com.rhbekti.yobo.ui.screen.signIn.SignInViewModel
import com.rhbekti.yobo.ui.theme.YoboTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun YoboApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val context = LocalContext.current
    val coroutinesScope: CoroutineScope = rememberCoroutineScope()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val signInViewModel = viewModel<SignInViewModel>()
    val state by signInViewModel.state.collectAsStateWithLifecycle()
    val isLoginUser = remember { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (isLoginUser.value) {
                if (currentRoute != Screen.DetailBook.route) {
                    BottomBar(navController)
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.SignIn.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.SignIn.route) {
                isLoginUser.value = false

                LaunchedEffect(key1 = Unit) {
                    if (googleAuthUiClient.getSignedInUser() !== null) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        if (result.resultCode == ComponentActivity.RESULT_OK) {
                            coroutinesScope.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                signInViewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )

                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if (state.isSignInSuccessful) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                        signInViewModel.resetState()
                    }
                }

                SignInScreen(state = state, onSignInClick = {
                    coroutinesScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                })
            }

            composable(Screen.Home.route) {
                isLoginUser.value = true

                HomeScreen(
                    userData = googleAuthUiClient.getSignedInUser(),
                    navigateToDetail = { bookId ->
                        navController.navigate(Screen.DetailBook.createRoute(bookId))
                    }
                )
            }

            composable(Screen.Search.route) {
                SearchScreen(
                    navigateToDetail = { bookId ->
                        navController.navigate(Screen.DetailBook.createRoute(bookId))
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    userData = googleAuthUiClient.getSignedInUser(),
                    onSignOut = {
                        coroutinesScope.launch {
                            googleAuthUiClient.signOut()
                            navController.popBackStack()
                        }
                    }
                )
            }

            composable(
                route = Screen.DetailBook.route,
                arguments = listOf(navArgument("bookId") { type = NavType.StringType }),
            ) {
                val id = it.arguments?.getString("bookId")
                DetailScreen(
                    bookId = id.toString(),
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.search),
                icon = Icons.Default.Search,
                screen = Screen.Search
            ),
            NavigationItem(
                title = stringResource(id = R.string.profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            )
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun YoboAppPreview() {
    YoboTheme {
        YoboApp()
    }
}
