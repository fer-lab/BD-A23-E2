package t6

data class User(
    val id: String,
    val userName: String,
    val realName: String,
    val userType: UserType = UserType.USER,
    val userPasswd: String = ""
)
