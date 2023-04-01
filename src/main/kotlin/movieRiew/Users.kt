package movieRiew

import movieRiew.utilities.Storage

class Users: UsersInterface {
    override fun get(id: String): User {
        return getAll().values.find { it.id == id } ?: getEmptyUser()
    }
    override fun getByUserName(userName: String): User {
        return getAll().values.find { it.userName == userName } ?: getEmptyUser()
    }

    override fun getAll(): Map<String, User> {

        return Storage.users.associateBy { it.id }
    }

    private fun getEmptyUser(): User {
        return User(id = "", userName = "", realName = "", userType = UserType.USER, userPasswd = "")
    }

}