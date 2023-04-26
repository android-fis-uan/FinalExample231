package co.edu.uan.android.finalexample418.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.edu.uan.android.finalexample418.daos.CatDao
import co.edu.uan.android.finalexample418.entities.CatEntity

@Database(entities = arrayOf(CatEntity::class), version = 1)
abstract class CatsDatabase : RoomDatabase() {

    abstract fun dao(): CatDao

    companion object {
         private lateinit var instance: CatsDatabase

         fun getInstance(context: Context) : CatsDatabase{
             instance = Room.databaseBuilder(context, CatsDatabase::class.java, "catsdatabase.db")
                 .build()
             return instance
         }
    }
}