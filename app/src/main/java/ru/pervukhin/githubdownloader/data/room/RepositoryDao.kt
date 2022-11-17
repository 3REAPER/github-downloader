package ru.pervukhin.githubdownloader.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepositoryDao{

    @Query("SELECT * FROM RepositoryDataBase")
    fun getAll(): List<RepositoryDataBase>

    @Query("SELECT * FROM RepositoryDataBase WHERE name=:name AND owner=:owner")
    fun getByNameAndOwner(name: String, owner: String): List<RepositoryDataBase>

    @Insert
    fun insert(repository: RepositoryDataBase)
}