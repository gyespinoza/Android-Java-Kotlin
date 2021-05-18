package com.example.todolistfirebasesemana17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.todolistfirebasesemana17.FirebaseUtils.firebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class loginActivity : AppCompatActivity() {
    lateinit var signInEmail: String
    lateinit var signInPassword: String
    lateinit var signInInputsArray: Array<EditText>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInInputsArray = arrayOf(etSignInEmail, etSignInPassword)
        btnSignIn.setOnClickListener {
            signInUser()
        }
        btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, createAccountActivity::class.java))
            finish()
        }
    }

    //validar si esta vacio el correo y la clave
    private fun notEmpty():Boolean=signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

    private fun signInUser() {
        signInEmail = etSignInEmail.text.toString().trim()
        signInPassword = etSignInPassword.text.toString().trim()

        if(notEmpty()){
            firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener{signIn->
                    if(signIn.isSuccessful){
                        startActivity(Intent(this, InicioActivity::class.java))
                        Toast.makeText(applicationContext, "Ha iniciado sesion correctamente", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(applicationContext, "No se ha podido iniciar sesion correctamente", Toast.LENGTH_LONG).show()
                    }
                }
        }else{
            signInInputsArray.forEach { input ->
                if(input.text.toString().trim().isEmpty()){
                    input.error= "${input.hint} no debe quedar vacío"
                }
            }
        }

    }
}