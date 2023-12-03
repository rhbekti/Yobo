package com.rhbekti.yobo.ui.navigation

sealed class Screen(val route: String) {
    object SignIn: Screen("signIn")
    object Home : Screen("home")
    object Search : Screen("search")
    object Loans : Screen("loans")
    object Profile : Screen("profile")
}