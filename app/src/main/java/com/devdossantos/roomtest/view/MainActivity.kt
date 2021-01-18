package com.devdossantos.roomtest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.devdossantos.roomtest.R
import com.devdossantos.roomtest.api.CharacterRepository
import com.devdossantos.roomtest.card.entity.CardEntity
import com.devdossantos.roomtest.card.repository.CardRepository
import com.devdossantos.roomtest.card.viewmodel.CardViewModel
import com.devdossantos.roomtest.database.AppDataBase
import com.devdossantos.roomtest.utils.CardUtils
import com.devdossantos.roomtest.viewmodel.CharacterViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allCharId = CardUtils().getIdsList()

        val cardUtils = CardUtils()

       dbCount(allCharId, cardUtils)


    }

    private fun dbCount(allCharId: List<Int>, cardUtils: CardUtils) {
        val databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)

        databaseViewModel.count().observe(this) {
            val count = it.toString().toInt()
            if (count == allCharId.size) {
                val intent = Intent(this@MainActivity, CardActivity::class.java)
                startActivity(intent)
            } else {
                charViewModel(allCharId, cardUtils)
            }
        }
    }

    private fun charViewModel(allCharId: List<Int>, utils: CardUtils) {

        val characterViewModel = ViewModelProvider(
            this,
            CharacterViewModel.CharacterViewModelFactory(CharacterRepository())
        ).get(CharacterViewModel::class.java)

        characterViewModel.getCharacter(allCharId).observe(this) {
            utils.addCardsOnManager(it)
            dbViewModel(utils.getCardList())
        }

    }

    private fun dbViewModel(cardList: List<CardEntity>) {
        val databaseViewModel = ViewModelProvider(
            this,
            CardViewModel.CardViewModelFactory(
                CardRepository(
                    AppDataBase.getDatabase(this).cardDao()
                )
            )
        ).get(CardViewModel::class.java)

        cardList.forEach {
            databaseViewModel.addCard(it).observe(this) {
                Log.i("DB_INSERT", "$it")
            }
        }


    }

}