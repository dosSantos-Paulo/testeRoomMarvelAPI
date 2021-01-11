package com.devdossantos.roomtest.card.dao

import androidx.room.Dao
import androidx.room.Insert
import com.devdossantos.roomtest.card.entity.CardEntity

@Dao
interface CardDao {

    @Insert
    suspend fun addCard(card: CardEntity)

}