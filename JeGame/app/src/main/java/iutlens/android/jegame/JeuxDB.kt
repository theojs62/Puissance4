package iutlens.android.jegame

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Jeu::class], version = 1)
abstract class JeuxDB : RoomDatabase() {
    abstract fun jeuDao(): JeuDAO
    companion object {
        private var INSTANCE_DB: JeuxDB? = null
        fun getDatabase(context: Context): JeuxDB {
            if (INSTANCE_DB == null) {
                synchronized(this) {
                    INSTANCE_DB =
                        Room.databaseBuilder(context,JeuxDB::class.java, "jeux_database")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE_DB!!
        }
    }
}

