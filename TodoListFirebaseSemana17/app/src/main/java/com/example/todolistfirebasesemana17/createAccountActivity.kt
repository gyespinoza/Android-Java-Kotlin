package com.example.todolistfirebasesemana17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.todolistfirebasesemana17.FirebaseUtils.firebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_create_account.*

class createAccountActivity : AppCompatActivity() {
    lateinit var userEmail: String
    lateinit var  userPassword:String
    lateinit var createAccountInputsArray: Array<EditText>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        createAccountInputsArray= arrayOf(etEmail, etPassword, etConfirmPassword)
        btnCreateAccount.setOnClickListener {
            createAccount()
        }

        btnSignIn.setOnClickListener {
            startActivity(Intent(this, loginActivity::class.java))
            Toast.makeText(applicationContext, "Inicie sesion con su cuenta", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    //evaluar si el usuario ha iniciado sesion
    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let{
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(applicationContext, "Bienvenido", Toast.LENGTH_LONG).show()
        }
    }
    //validar si esta vacio el correo y la clave
    private fun notEmpty():Boolean=etEmail.text.toString().trim().isNotEmpty() &&
            etPassword.text.toString().trim().isNotEmpty() &&
            etConfirmPassword.text.toString().trim().isNotEmpty()


    //validar si claves coinciden
    private fun identicalPassword():Boolean{
        var identical=false
        if(notEmpty() &&
            etPassword.text.toString().trim()== etConfirmPassword.text.toString().trim()
        ) {
           identical=true
        }else if(!notEmpty()){
            createAccountInputsArray.forEach { input->
                if(input.text.toString().trim().isEmpty()){
                    input.error = "${input.hint} el campo es requerido"
                }
            }
        }else{
            Toast.makeText(applicationContext, "Las claves no coinciden", Toast.LENGTH_LONG).show()
        }
        return identical
    }
    private  fun createAccount(){
        if(identicalPassword()){
            //identicalPassword() va a retornar true solo cuando los inputs no esten vacios y las
                //claves coincidan
            userEmail = etEmail.text.toString().trim()
            userPassword= etPassword.text.toString().trim()

            //crear usuario
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            Toast.makeText(applicationContext, "La cuenta ha sido creada", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, InicioActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "Fallo la autenticacion", Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }
}