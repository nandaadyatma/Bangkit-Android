package com.example.jjkcharacterrecyclerview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class CharacterDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val imgDetailCharacter = findViewById<ImageView>(R.id.img_detail_character)
        val tvDetailName = findViewById<TextView>(R.id.tv_detail_name)
        val tvDetailDescription = findViewById<TextView>(R.id.tv_detail_description)
        val tvDetailGender = findViewById<TextView>(R.id.tv_detail_gender)
        val tvDetailBirthday = findViewById<TextView>(R.id.tv_detail_birthday)
        val tvDetailAbility = findViewById<TextView>(R.id.tv_detail_ability)
        val btnShare = findViewById<Button>(R.id.action_share)



        val dataCharacter = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Character>(EXTRA_PERSON,Character::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Character>(EXTRA_PERSON)
        }

        if(dataCharacter != null) {
            imgDetailCharacter.setImageResource(dataCharacter.photo)
            tvDetailName.text = dataCharacter.name
            tvDetailDescription.text = dataCharacter.description
            tvDetailGender.text = "Jenis Kelamin: ${dataCharacter.gender}"
            tvDetailBirthday.text = "Kelahiran: ${dataCharacter.birthday}"
            tvDetailAbility.text = dataCharacter.ability

            btnShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, "${dataCharacter.name}\n")
                    putExtra(Intent.EXTRA_TEXT, "${dataCharacter.description}")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}