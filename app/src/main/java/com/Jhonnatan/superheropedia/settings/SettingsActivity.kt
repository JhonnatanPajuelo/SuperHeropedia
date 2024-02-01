package com.Jhonnatan.superheropedia.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.Jhonnatan.superheropedia.R
import com.Jhonnatan.superheropedia.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Singlenton  Patron de Design // Desing patterns
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingsActivity : AppCompatActivity() {

    companion object{
        const val VOLUME_LVL="volume"
    }
    lateinit var binding:ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.RsVolume.addOnChangeListener { _, value, _ ->
            Log.i("J","$value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
        }

        }
    }

    private suspend fun saveVolume(value:Int){
        dataStore.edit {
             preferences->preferences[intPreferencesKey(VOLUME_LVL)]=value
        }
    }

    private suspend fun saveChecks(key:String,value:Boolean){
        dataStore.edit {
            checks->checks[booleanPreferencesKey(key)]=value
        }
    }
}