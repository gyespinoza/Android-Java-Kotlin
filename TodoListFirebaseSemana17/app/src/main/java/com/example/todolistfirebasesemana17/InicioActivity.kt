package com.example.todolistfirebasesemana17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_inicio.*

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)


        textView.text = FirebaseAuth.getInstance().currentUser?.email.toString()

        btnSignOUt.setOnClickListener {
            FirebaseUtils.firebaseAuth.signOut()
            startActivity(Intent(this, createAccountActivity::class.java))
            Toast.makeText(applicationContext, "Ha finalizado la sesion", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}