package movieRew

interface Users
{
    fun get(id: String): User
    fun getByUserName(userName: String): User

    fun getAll(): Map<String, User>
}