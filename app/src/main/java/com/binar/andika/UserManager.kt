

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.map

class UserManager (context : Context) {

    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")
    private val loginDataStore : DataStore<Preferences> = context.createDataStore(name = "login_prefs")

    companion object{
        val ID = preferencesKey<String>("USER_ID")
        val UMUR = preferencesKey<String>("USER_UMUR")
        val NAME = preferencesKey<String>("USER_NAME")
        val PASSWORD = preferencesKey<String>("USER_PASSWORD")
        val USERNAME = preferencesKey<String>("USER_NAME")
        val ADDRESS = preferencesKey<String>("USER_ADDRESS")
        val LOGIN_STATE = preferencesKey<String>("USER_LOGIN")
        val IMAGE  = preferencesKey<String>("USER_IMAGE")
    }

    suspend fun saveDataUser(id : String, umur:String, password : String, username: String, name: String, address : String, image: String) {
        dataStore.edit {
            it[ID] = id
            it[PASSWORD] = password
            it[USERNAME] = username
            it[NAME] = name
            it[UMUR] = umur
            it[ADDRESS] = address
            it[IMAGE] = image
        }
    }

    suspend fun deleteDataUser() {
        dataStore.edit{
            it.clear()

        }
    }

    suspend fun saveDataLogin(login : String) {
        loginDataStore.edit {
            it[LOGIN_STATE] = login
        }
    }

    suspend fun deleteDataLogin(){
        loginDataStore.edit{
            it.clear()

        }
    }

    val userID : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [ID] ?: ""
    }
    val userUsername : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [USERNAME] ?: ""
    }
    val userName : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [NAME] ?: ""
    }
    val userPass : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [PASSWORD] ?: ""
    }
    val userUmur : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [UMUR] ?: ""
    }

    val userAddress : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [ADDRESS] ?: ""
    }
    val userLogin : kotlinx.coroutines.flow.Flow<String> = loginDataStore.data.map {
        it [LOGIN_STATE] ?: "false"
    }
    val userImage : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [IMAGE] ?: ""
    }

}