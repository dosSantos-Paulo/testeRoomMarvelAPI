package com.devdossantos.roomtest.card.repository

import com.devdossantos.roomtest.card.dao.CardDao
import com.devdossantos.roomtest.card.entity.CardEntity

class CardRepository(private val cardDao: CardDao) {

    suspend fun addCard(card: CardEntity) = cardDao.addCard(card)

    suspend fun count () = cardDao.count()

    suspend fun getCard(id: Int) = cardDao.getCard(id)

}
