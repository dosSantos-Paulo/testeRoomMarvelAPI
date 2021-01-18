package com.devdossantos.roomtest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.devdossantos.roomtest.R
import com.devdossantos.roomtest.card.entity.CardEntity
import com.devdossantos.roomtest.card.model.Hero
import com.devdossantos.roomtest.card.repository.CardRepository
import com.devdossantos.roomtest.card.viewmodel.CardViewModel
import com.devdossantos.roomtest.database.AppDataBase
import org.w3c.dom.Text

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        val cardManager: MutableList<Hero> = mutableListOf()

//        getCardFromDB(listOf(1010925))

        getAllCardsFromDB(cardManager)


    }

    private fun getAllCardsFromDB(cardManager: MutableList<Hero>) {
        val databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)

        databaseViewModel.getAllCards().observe(this) { cardlist ->
            val _cardList = cardlist as List<CardEntity>
            _cardList.forEach {
                cardManager.add(
                    Hero(
                        it.id,
                        it.heroName,
                        it.name,
                        it.imageUrl,
                        it.durability,
                        it.energy,
                        it.fightingSkills,
                        it.inteligence,
                        it.speed,
                        it.strength,
                        it.description
                    )
                )
            }
            val list = sort5(cardManager)
            findViewById<TextView>(R.id.txt1).text = list[0].classification.toString()
            findViewById<TextView>(R.id.txt2).text = list[1].classification.toString()
            findViewById<TextView>(R.id.txt3).text = list[2].classification.toString()
            findViewById<TextView>(R.id.txt4).text = list[3].classification.toString()
            findViewById<TextView>(R.id.txt5).text = list[4].classification.toString()
        }

    }

    private fun sort5(cardManager: MutableList<Hero>): MutableList<Hero> {
        val list: MutableList<Hero> = mutableListOf()

        val bronzeCards: MutableList<Hero> = mutableListOf()
        val silverCards: MutableList<Hero> = mutableListOf()
        val goldCards: MutableList<Hero> = mutableListOf()
        val diamondCards: MutableList<Hero> = mutableListOf()

        cardManager.forEach {

            if (it.classification < 3.0) {
                bronzeCards.add(it)
            } else if (it.classification >= 3.0 && it.classification < 4.6) {
                silverCards.add(it)
            } else if (it.classification >= 4.6 && it.classification < 6.0) {
                goldCards.add(it)
            } else {
                diamondCards.add(it)
            }
        }
        for (i in 1..3) {
            list.add(bronzeCards.random())
        }
        for (i in 1..2) {
            list.add(silverCards.random())
        }
        list.add(goldCards.random())

        return list
    }

    private fun sort3(cardManager: MutableList<Hero>) {
        val bronzeCards: MutableList<Hero> = mutableListOf()
        val silverCards: MutableList<Hero> = mutableListOf()
        val goldCards: MutableList<Hero> = mutableListOf()
        val diamondCards: MutableList<Hero> = mutableListOf()

        cardManager.forEach {

            if (it.classification < 3.0) {
                bronzeCards.add(it)
            } else if (it.classification >= 3.0 && it.classification < 4.6) {
                silverCards.add(it)
            } else if (it.classification >= 4.6 && it.classification < 6.0) {
                goldCards.add(it)
            } else {
                diamondCards.add(it)
            }
        }
    }

    private fun getCardFromDB(idList: List<Int>) {

        val databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)

        idList.forEach {
            databaseViewModel.getCard(it).observe(this) { card ->
                val _card = card as CardEntity
                findViewById<TextView>(R.id.txt1).text = _card.name
            }
        }

    }

}