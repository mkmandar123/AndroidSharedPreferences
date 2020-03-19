package com.example.sharedpreferencesex

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var editTextCity: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCity=findViewById(R.id.editTextCity)
        editTextName=findViewById(R.id.editTextName)
        findViewById<Button>(R.id.saveButton).setOnClickListener{

            saveData(editTextName.text.toString(),editTextCity.text.toString())
        }

        findViewById<Button>(R.id.retrieveButton).setOnClickListener{
            retrieveData()
        }
    }

    private fun retrieveData() {
        val mypref=getSharedPreferences("data", Context.MODE_PRIVATE)
        editTextName.setText(mypref.getString("name","OLD DATA NOT PRESENT"))
        editTextCity.setText(mypref.getString("city","OLD DATA NOT PRESENT"))
    }

    private fun saveData(name:String,city:String) {

        if(name.isEmpty())
        { editTextName.error ="Enter Name"
            return
        }

        if(city.isEmpty()) {
            editTextCity.error = "Enter City"
            return
        }
        val pref=getSharedPreferences("data", Context.MODE_PRIVATE)

        val editor=pref.edit()
        editor.putString("name",name)
        editor.putString("city",city)
        editor.apply()

        Toast.makeText(this,"DATA SAVED",Toast.LENGTH_LONG).show()
    }
}
