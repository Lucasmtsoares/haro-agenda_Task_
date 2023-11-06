package com.example.haro_agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.haro_agenda.Dao.UsuariosDao
import com.example.haro_agenda.Entidades.User
import com.example.register.MIGRATION_1_2


@Database(
    entities = [User::class],
    version = 6,
    exportSchema = true
)

abstract class LoginDatabaseHelper : RoomDatabase() {
    abstract fun userDao(): UsuariosDao

    companion object {
        fun pegarInstancia(context: Context) : LoginDatabaseHelper {

            return Room.databaseBuilder(context, LoginDatabaseHelper::class.java, "usuarios.db").allowMainThreadQueries().addMigrations(
                MIGRATION_1_2).build()
        }
    }
}