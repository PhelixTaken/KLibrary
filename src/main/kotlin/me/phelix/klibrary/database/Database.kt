package me.phelix.klibrary.database

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import me.phelix.klibrary.database.DatabaseType.*

class Database(databaseType: DatabaseType, credentials: String) {

    lateinit var mongoClient: MongoClient

    init {
        try {
            when (databaseType) {
                MONGODB -> mongoClient = MongoClient(MongoClientURI(credentials))
                MYSQL -> {

                }
            }
        } catch (e: Exception) {
            throw DatabaseInitializationException("Could not initialize the database!", e)
        }
    }

}