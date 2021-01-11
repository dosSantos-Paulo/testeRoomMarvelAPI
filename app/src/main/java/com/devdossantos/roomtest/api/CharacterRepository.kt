package com.devdossantos.roomtest.api

import com.devdossantos.roomtest.utils.MY_PUBLIC_KEY
import com.devdossantos.roomtest.utils.getHash
import com.devdossantos.roomtest.utils.getTimeStamp

class CharacterRepository {

    private val _client = CharacterEndpoint.Endpoint

    suspend fun getCharacter(id:Int) = _client.getCharacter(
        id,
        getTimeStamp(),
        getHash(),
        MY_PUBLIC_KEY
    )
}