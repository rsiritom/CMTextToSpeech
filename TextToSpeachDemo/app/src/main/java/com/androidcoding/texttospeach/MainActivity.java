package com.androidcoding.texttospeach;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Inicialize variables
    EditText etInput;
    Button btConvert,btClear;
    TextToSpeech textToSpeach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variables
        etInput = findViewById(R.id.et_input);
        btConvert = findViewById(R.id.bt_convert);
        btClear = findViewById(R.id.bt_clear);

        textToSpeach = new TextToSpeech(getApplicationContext()
            ,new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int i){
                if (i == TextToSpeech.SUCCESS){
                    //select language
                    int lagng = textToSpeach.setLanguage(Locale.getDefault());
                }
            }
        });

        btConvert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //get edittext value
                String s = etInput.getText().toString();

                // Text convert to speach

                AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
                am.setStreamVolume(am.STREAM_MUSIC, amStreamMusicMaxVol, 0);

                int speech = textToSpeach.speak(s, TextToSpeech.QUEUE_FLUSH, null);
            }

                                     }
        );

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clear edit text
                etInput.setText("");
            }
        });
    }


}
