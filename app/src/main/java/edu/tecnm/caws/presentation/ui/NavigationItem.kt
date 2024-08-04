package edu.tecnm.caws.presentation.ui

sealed class NavigationItem(val route: String) {
    data object Home: NavigationItem(Screen.HOME.name)
    data object UserLogin: NavigationItem(Screen.USER_LOGIN.name)
    data object UserRegister: NavigationItem(Screen.USER_REGISTER.name)
    data object CourseAdd: NavigationItem(Screen.COURSE_ADD.name)
    data object CourseList: NavigationItem(Screen.COURSE_LIST.name)
}