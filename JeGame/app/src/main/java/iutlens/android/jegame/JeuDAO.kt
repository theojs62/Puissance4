package iutlens.android.jegame

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JeuDAO {

    @Insert
    fun insert(jeu: Jeu)

    @Query("DELETE FROM jeu")
    fun delete()

    @Query("select id_jeu FROM jeu")
    fun select() :List<Int>

    @Query("SELECT * FROM jeu ORDER BY id_jeu")
    fun listeJeux(): List<Jeu>
}
