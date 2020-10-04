package me.phelix.klibrary.database

import java.lang.Exception

class DatabaseInitializationException : Exception {
    constructor(message: String): super(message)
    constructor(message: String, throwable: Throwable): super(message, throwable)
}