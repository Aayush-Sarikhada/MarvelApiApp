package com.example.marvelheroes.common

import java.math.BigInteger
import java.security.MessageDigest
import java.security.Timestamp

object Constants {

    object MarvelApiConstants {
        const val MARVEL_API_BASE_URL = "https://gateway.marvel.com"
        const val MARVEL_KEY_PUBLIC = "6001acf9006e45851f7641aec6f15a5f"
        const val MARVEL_KEY_PRIVATE = "993f6eb891388bda5e70ed139ffb4790eccf1892"
        val timeStamp = java.sql.Timestamp(System.currentTimeMillis()).time.toString()
        fun hash(): String {
            val input = "$timeStamp${MARVEL_KEY_PRIVATE}${MARVEL_KEY_PUBLIC}"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }

    object Keys {
    }
}