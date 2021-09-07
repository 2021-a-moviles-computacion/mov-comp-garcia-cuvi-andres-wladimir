package com.example.firebaseuno

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.firebaseuno.dto.FirestoreUsuarioDto
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val CODIGO_INICIO_SESION = 102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonLogin = findViewById<Button>(R.id.btnLogin)
        botonLogin.setOnClickListener {
            llamarLoginUsuario()
        }

        val botonLogout = findViewById<Button>(R.id.btnLogout)
        botonLogout.setOnClickListener {
            solicitarSalirDelAplicativo()
        }

        val botonProducto = findViewById<Button>(R.id.btnProducto)
        botonProducto.setOnClickListener {
            val intent = Intent(
                this,
                CProducto::class.java
            )
            startActivity(intent)
        }

        val botonRestaurante = findViewById<Button>(R.id.btnRestaurante)
        botonRestaurante.setOnClickListener {
            val intent = Intent(
                this,
                DRestaurante::class.java
            )
            startActivity(intent)
        }


        val botonOrdenes = findViewById<Button>(R.id.btnOrdenes)
        botonOrdenes.setOnClickListener {
            val intent = Intent(
                this,
                EOrdenes::class.java
            )
            startActivity(intent)
        }

    }

    fun llamarLoginUsuario() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                "https://example.com/terms.html",
                "https://example.com/privacy.html"
            )
                .build(),
            CODIGO_INICIO_SESION
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODIGO_INICIO_SESION ->{
                if(resultCode == Activity.RESULT_OK){
                    val usuario = IdpResponse.fromResultIntent(data)
                    if (usuario!=null) {
                        if (usuario.isNewUser == true) {
                            Log.i("firebase-login", "Nuevo usuario")
                            registrarUsuarioPorPrimeraVez(usuario)
                        } else {
                            setearUsuarioFirebase()
                            Log.i("firebase-login", "Usuario antiguo")
                        }
                    }
                }else{
                    Log.i("firebase-login","El usuario canceló")
                }
            }
        }
    }


    fun setearUsuarioFirebase(){
        val instaciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instaciaAuth.currentUser
        if (usuarioLocal != null){
            if (usuarioLocal.email!=null){
                val db = Firebase.firestore

                val referencia = db
                    .collection("usuario")
                    .document(usuarioLocal.email.toString())

                referencia.get().addOnSuccessListener {
                    val usuarioCargado: FirestoreUsuarioDto? =
                    it.toObject(FirestoreUsuarioDto::class.java)
                    if (usuarioCargado !=null){
                        BAuthUsuario.usuario = BUsuarioFirebase(
                            usuarioCargado.uid,
                            usuarioCargado.email,
                            usuarioCargado.roles
                        )
                        setearBienvenida()
                    }

                    Log.i("firebase-firestore", "Usuario Cargado")
                }
                    .addOnFailureListener {
                        Log.i("firebase-firestore","Falló cargar el usuario")
                    }

            }
        }
    }

    fun setearBienvenida(){
        val textViewBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        val botonLogin = findViewById<Button>(R.id.btnLogin)
        val botonLogout = findViewById<Button>(R.id.btnLogout)
        val botonProducto = findViewById<Button>(R.id.btnProducto)
        val botonRestaurante = findViewById<Button>(R.id.btnRestaurante)
        val botonOrdenes  = findViewById<Button>(R.id.btnOrdenes)


        if (BAuthUsuario.usuario != null){
            textViewBienvenida.text = "Bienvenido ${BAuthUsuario.usuario?.email}"
            botonLogin.visibility = View.INVISIBLE
            botonLogout.visibility = View.VISIBLE
            botonProducto.visibility = View.VISIBLE
            botonRestaurante.visibility = View.VISIBLE
            botonOrdenes.visibility  = View.VISIBLE
        }else{
            textViewBienvenida.text = "Ingresa al apliccativo"
            botonLogin.visibility = View.VISIBLE
            botonLogout.visibility = View.INVISIBLE
            botonProducto.visibility = View.INVISIBLE
            botonRestaurante.visibility = View.INVISIBLE
            botonOrdenes.visibility  = View.INVISIBLE
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse){
        val usuarioLoggeado = FirebaseAuth.getInstance().currentUser

        if (usuario.email != null && usuarioLoggeado != null){
//            roles: ["usuario", "admin"]
            val db = Firebase.firestore
            val rolesUsuario = arrayListOf("usuario")
            val identificadorUsuario = usuario.email

            val nuevoUsuario = hashMapOf<String,Any>(
                "roles" to rolesUsuario,
                "uid" to usuarioLoggeado.uid,
                "email" to identificadorUsuario.toString()
            )


            db.collection("usuario")
                .document(identificadorUsuario.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    Log.i("firebase-firestore", "Se creó")
                    setearUsuarioFirebase()
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "Fallo")
                }

        }else{
            Log.i("firebase-login", "ERROR")
        }
    }

    fun solicitarSalirDelAplicativo(){
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnCompleteListener {
                BAuthUsuario.usuario == null
                setearBienvenida()
            }
    }






}