package com.bobobox.poketest.resources.data.source.local.db

import android.app.Application
import androidx.room.Room
import com.bobobox.poketest.resources.data.source.local.db.dao.FavPokemonDao

object DbManager {
    fun provideDatabase(application: Application): PokeDB {
        return Room.databaseBuilder(application, PokeDB::class.java, "pokemon")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideFavPokemonDao(database: PokeDB): FavPokemonDao {
        return  database.favPOkemonDao
    }
}