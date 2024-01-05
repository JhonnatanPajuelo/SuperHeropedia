package com.Jhonnatan.superheropedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.Jhonnatan.superheropedia.databinding.ActivitySuperHeropedialistBinding


class SuperHeropediaListActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySuperHeropedialistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySuperHeropedialistBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}