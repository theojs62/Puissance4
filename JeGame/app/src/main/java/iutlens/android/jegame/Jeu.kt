package iutlens.android.jegame

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "jeu")
data class Jeu(
    @PrimaryKey
    @ColumnInfo(name = "id_jeu")
    val id : Int,
    @ColumnInfo(name = "nom_jeu")
    val nom : String,
    @ColumnInfo(name = "annee_sortie_jeu")
    val annee_sortie : Int,
    @ColumnInfo(name = "nb_joueurs_min")
    val nb_joueurs_min : Int,
    @ColumnInfo(name = "nb_joueurs_max")
    val nb_joueurs_max : Int,
    @ColumnInfo(name = "duree_jeu")
    val duree : Int
)
