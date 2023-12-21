package com.rhbekti.yobo.ui.navigation

sealed class Screen(val route: String) {
    object SignIn : Screen("signIn")
    object Home : Screen("home")
    object Search : Screen("search")
    object Profile : Screen("profile")
    object DetailBook : Screen("home/{bookId}") {
        fun createRoute(bookId: String) = "home/$bookId"
    }
}