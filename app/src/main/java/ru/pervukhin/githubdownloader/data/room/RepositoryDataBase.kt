package ru.pervukhin.githubdownloader.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RepositoryDataBase(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val owner: String) {

}