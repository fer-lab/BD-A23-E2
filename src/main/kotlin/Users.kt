import utilities.Storage
import movieRew.User
import movieRew.UserType
import movieRew.Users

class Users: Users {
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
        return User(id = "0", userName = "", realName = "", userType = UserType.USER, userPasswd = "")
    }

}