package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.result.Result
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost


class HHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hhttp)
        metodoGet()
        metodoPost()
    }

    fun metodoGet() {
        //import com.github,kittunun.result.Result
        "https://jsonplaceholder.typicode.com/posts/1"
            .httpGet()
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val getString = result.get()
                        Log.i("http-klaxon", "${getString}")

                        val post = Klaxon()
                            .parse<IPostHttp>(getString)
                        // .parseArray<IPostHttp>(getString)
                        Log.i("http-klaxon", "${post?.body}")
                    }

                }

            }
    }

    fun metodoPost() {
        val parametros: List<Pair<String, *>> = listOf(
            "title" to "Titulo moviles",
            "body" to "descripcion moviles",
            "userId" to 1
        )

        "https://jsonplaceholder.typicode.com/posts"
            .httpPost(parametros)
            .responseString{req,res,result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("http-klaxon", "${postString}")


                    }

                }
            }
    }
}