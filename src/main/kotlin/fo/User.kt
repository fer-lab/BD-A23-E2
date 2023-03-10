package fo

import fo.utilities.Storage

data class User(
    val id: Int,
    val userName: String,
    val realName: String,
    val userType: String,
    private val userPasswd: String
) {
    fun validPasswd(passwd: String): Boolean {
        return this.userPasswd == passwd
    }

    fun isValid(): Boolean {
        return id > 0
    }

    fun isUser(): Boolean {
        return userType == "user"
    }


    fun isAdmin(): Boolean {
        return userType == "admin"
    }

    fun remove(id: Int) {
        Storage.users.removeAll{it.id == id}
    }
}
