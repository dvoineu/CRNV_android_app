package com.dvoineu.crnvirus.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Country::class), version = 2)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile
        private var instance: CountryDatabase? = null
        private val LOCK = Any()

        // if database is created then return instance of it
        // if not then synchronized lock is created in order to only one thread to access DB
        // and then new DB is created with function buildDatabase() assigned to instance and returned
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // check if instance already created by some thread
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
//                        "PRIMARY KEY(`id`))")
//            }
//        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java,
            "countryDB")
//            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration() // old DB is dropped and new created, user loses all data
            .build()
    }
}