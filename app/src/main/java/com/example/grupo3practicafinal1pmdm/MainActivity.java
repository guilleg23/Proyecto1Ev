package com.example.grupo3practicafinal1pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] tipoPersonaje = {"Bardo", "Bárbaro", "Brujo", "Clérigo", "Druida", "Explorador", "Guerrero", "Hechicero",
            "Mago", "Monje", "Paladín", "Pícaro"};
    int[] imagenPersonaje = {R.drawable.bardo, R.drawable.barbaro, R.drawable.brujo, R.drawable.clerigo, R.drawable.druida, R.drawable.explorador,
            R.drawable.guerrero, R.drawable.hechicero, R.drawable.mago, R.drawable.monje, R.drawable.paladin, R.drawable.picaro};
    Spinner spinner;
    EditText editTextNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        editTextNombre = findViewById(R.id.editTextNombre);


        spinner = findViewById(R.id.spinner);
        TipoPersonajeAdapter adaptador1 = new TipoPersonajeAdapter();
        spinner.setAdapter(adaptador1);


        Button buttonHabilidades = findViewById(R.id.button3);
        buttonHabilidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HabilidadesActivity.class);
                startActivity(intent);
            }
        });


        Intent musicIntent = new Intent(this, MusicService.class);
        startService(musicIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent musicIntent = new Intent(this, MusicService.class);
        stopService(musicIntent);
    }


    public String obtenerNombrePersonaje() {
        return editTextNombre.getText().toString();
    }

    class TipoPersonajeAdapter extends android.widget.BaseAdapter {

        @Override
        public int getCount() {
            return tipoPersonaje.length;
        }

        @Override
        public Object getItem(int position) {
            return tipoPersonaje[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            convertView = inflater.inflate(R.layout.itemspinner, null);
            ImageView iv1 = convertView.findViewById(R.id.imageView);
            TextView tv1 = convertView.findViewById(R.id.tvtipoPersonaje);
            iv1.setImageResource(imagenPersonaje[position]);
            tv1.setText(tipoPersonaje[position]);
            return convertView;
        }
    }
}
