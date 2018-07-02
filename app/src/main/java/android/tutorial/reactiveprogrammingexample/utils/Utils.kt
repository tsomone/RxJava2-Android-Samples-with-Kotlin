package android.tutorial.reactiveprogrammingexample.utils

import android.tutorial.reactiveprogrammingexample.model.ApiUser
import android.tutorial.reactiveprogrammingexample.model.User

/**
 * Created by tsomone on 12/06/18.
 */
object Utils {
    fun getUserList(): MutableList<User> {
        val userList = mutableListOf<User>()
        userList.add(User(0, "Linus", "Torvalds"))
        userList.add(User(1, "Larry", "Page"))
        userList.add(User(2, "Sergey", "Brin"))
        return userList
    }

    fun getApiUserList(): ArrayList<ApiUser> {
        val apiUserList = arrayListOf<ApiUser>()
        apiUserList.add(ApiUser(0, "Eric", "Schmidt"))
        apiUserList.add(ApiUser(1, "Jack", "Dorsey"))
        apiUserList.add(ApiUser(2, "Mark", "Zuckerberg"))
        return apiUserList
    }

    fun convertApiUserListToUserList(apiUserList: ArrayList<ApiUser>): ArrayList<User> {
        val userList = arrayListOf<User>()

        for (apiUser in apiUserList) {
            userList.add(User(apiUser.id, apiUser.firstname, apiUser.lastname))
        }

        return userList
    }

    fun getUserListWhoLovesCricket(): ArrayList<User> {
        val userList = arrayListOf<User>()
        userList.add(User(1, "Linus", "Torvalds"))
        userList.add(User(2, "Larry", "Page"))
        return userList
    }

    fun getUserListWhoLovesFootball(): ArrayList<User> {
        val userList = arrayListOf<User>()
        userList.add(User(1, "Linus", "Torvalds"))
        userList.add(User(3, "Mark", "Zuckerberg"))
        return userList
    }

    fun filterUserWhoLovesBoth(cricketFans: ArrayList<User>, footballFans: ArrayList<User>): ArrayList<User> {
        val usersWhoLovesBoth = arrayListOf<User>()
        for (cricketFan in cricketFans) {
            for (footballFan in footballFans) {
                if (cricketFan.id == footballFan.id) {
                    usersWhoLovesBoth.add(cricketFan)
                }
            }
        }
        return usersWhoLovesBoth
    }
}