package com.example.FoodIepal.Utils

import android.net.Uri
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URLConnection
import java.util.Random
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import android.util.Base64
import android.util.Log
import java.net.URL
import java.security.NoSuchAlgorithmException
import java.security.InvalidKeyException



class FatSecretGet {

    val APP_METHOD = "GET"
    val APP_KEY = "3e99a15ba8474ae0b565f2743ea1dfb3"
    val APP_SECRET = "4a5585c0f1cd4ade8c8ad4c62e21385f"
    val APP_URL = "http://platform.fatsecret.com/rest/server.api"
    val HMAC_SHA1_ALGORITHM = "HmacSHA1"

    fun getFood(ab: Long): JSONObject? {
        val params: MutableList<String> = ArrayList(generateOuthParams().toList())
        val template = arrayOfNulls<String>(1)
        params.add("method=food.get")
        params.add("food_id=$ab")
        params.add("oauth_signature=" + sign(APP_METHOD, APP_URL, params.toTypedArray()))
        var food: JSONObject? = null
        try {
            val url = URL(APP_URL + "?" + paramify(params.toTypedArray()))
            val api: URLConnection = url.openConnection()
            val reader = BufferedReader(InputStreamReader(api.getInputStream()))
            val builder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                builder.append(line)
            }
            val foodGet = JSONObject(builder.toString())
            food = foodGet.getJSONObject("food")
        } catch (e: Exception) {
            Log.w("Fit", e.toString())
            e.printStackTrace()
        }
        return food
    }


    private fun generateOuthParams() : Array<String> {
        return arrayOf(
            "oauth_consumer_key=$APP_KEY",
            "oauth_signature_method=HMAC-SHA1",
            "oauth_timestamp=${(System.currentTimeMillis() * 2)}",
            "oauth_nonce=${nonce()}",
            "oauth_version=1.0",
            "format=json"
        )
    }

    private fun sign(method: String, uri: String, params: Array<String>): String? {
        val p = arrayOf(method, Uri.encode(uri), Uri.encode(paramify(params)))
        val s = join(p, "&")
        val sk = SecretKeySpec(APP_SECRET.toByteArray(), HMAC_SHA1_ALGORITHM)
        return try {
            val m = Mac.getInstance(HMAC_SHA1_ALGORITHM)
            m.init(sk)
            Uri.encode(String(Base64.encode(m.doFinal(s.toByteArray()), Base64.DEFAULT)).trim())
        } catch (e: NoSuchAlgorithmException) {
            Log.w("FatSecret_TEST FAIL", e.message.toString())
            null
        } catch (e: InvalidKeyException) {
            Log.w("FatSecret_TEST FAIL", e.message.toString())
            null
        }
    }


    private fun paramify(params: Array<String>): String {
        val p = params.copyOf()
        p.sort()
        return join(p, "&")
    }

    private fun join(array: Array<String>, separator: String): String {
        val b = StringBuilder()
        for (i in array.indices) {
            if (i > 0) {
                b.append(separator)
            }
            b.append(array[i])
        }
        return b.toString()
    }



    private fun nonce() : String {
        val r : Random = Random()
        val n : java.lang.StringBuilder = StringBuilder()
        for (i in 0 until r.nextInt(8) + 2) {
            n.append(r.nextInt(26) + 'a'.toInt())
        }
        return n.toString()
    }
}