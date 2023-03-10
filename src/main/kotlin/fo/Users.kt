package fo

import fo.utilities.Storage

class Users {
    fun getAll(): Map<Int, User> {

        return Storage.users.associateBy { it.id }
    }

    fun get(id: Int): User {
        val user = getAll().values.firstOrNull{ it.id == id }

        if (user != null) {
            return user
        }

        return getEmptyUser()
    }

    private fun getEmptyUser(): User {
        return User(id = 0, userName = "", realName = "", userType = "", userPasswd = "")
    }

    fun getByUserName(userName: String): User {
        val user = getAll().values.firstOrNull { it.userName.lowercase() == userName.lowercase() }

        if (user != null) {
            return user
        }

        return getEmptyUser()
    }


}