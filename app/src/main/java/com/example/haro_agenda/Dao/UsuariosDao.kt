package com.example.haro_agenda.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.haro_agenda.Entidades.User


@Dao
abstract class UsuariosDao {
    @Query ("SELECT EXISTS(SELECT * FROM user WHERE email = :email AND senha = :senha)")
    abstract fun verificarLogin(email: String, senha: String) : Boolean

    @Insert
    abstract fun registrar( user: User)

}