package com.devdossantos.roomtest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.devdossantos.roomtest.R
import com.devdossantos.roomtest.card.entity.CardEntity
import com.devdossantos.roomtest.card.model.Hero
import com.devdossantos.roomtest.card.repository.CardRepository
import com.devdossantos.roomtest.card.viewmodel.CardViewModel
import com.devdossantos.roomtest.database.AppDataBase
import com.devdossantos.roomtest.utils.CardUtils

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        getCardFromDB(listOf(1010925))



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
            databaseViewModel.getCard(it).observe(this) {card ->
                val _card = card as CardEntity
                findViewById<TextView>(R.id.txt1).text = _card.name
            }
        }

    }

}