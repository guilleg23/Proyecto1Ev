package com.example.grupo3practicafinal1pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class EstadisticasActivity extends AppCompatActivity {
    private ImageView dadoImage1, dadoImage2, dadoImage3;
    private Random random = new Random();
    private int generated = 0 ;
    private Bundle bundle;
    ArrayList <String> nombresEst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estadisticas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.carismaLbl), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        botonAndEditText(R.id.fuerzaButton, R.id.fuerzaEditText, R.id.fuerzaLbl);
        botonAndEditText(R.id.destrezaButton, R.id.destrezaEditText,R.id.destrezaLbl);
        botonAndEditText(R.id.constitucionButton, R.id.constitucionEditText,R.id.constitucionLbl);
        botonAndEditText(R.id.inteligenciaButton, R.id.inteligenciaEditText,R.id.inteligenciaLbl);
        botonAndEditText(R.id.sabiduriaButton, R.id.sabiduriaEditText,R.id.sabiduriaLbl);
        botonAndEditText(R.id.carismaButton, R.id.carismaEditText,R.id.carismaLbl);
        dadoImage1 = findViewById(R.id.primerDado);
        dadoImage2 = findViewById(R.id.segundoDado);
        dadoImage3 = findViewById(R.id.tercerDado);
        bundle = new Bundle();
        bundle.putString("Ventana", "Estadisticas");
    }

    private void botonAndEditText(int botonX, int editTextX, int lblX) {
        Button boton = findViewById(botonX);
        EditText editText = findViewById(editTextX);
        TextView label = findViewById(lblX);
        nombresEst.add(label.getText().toString());
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] randomSet = ranNums();
                mostrarDados(randomSet);
                int suma = randomSet[0] + randomSet[1] + randomSet[2];
                editText.setText(String.valueOf(suma));
                boton.setEnabled(false);
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
                bundle.putInt(label.getText().toString(),Integer.parseInt(String.valueOf(editText.getText())));
                generated++;
                if (generated == 6){
                    new Handler().postDelayed(()->{
                        cargarVista();
                    },2000);
                }
            }
        });
    }
    private void cargarVista(){
        Intent intent = new Intent( );
        intent.putStringArrayListExtra("NombresEstadisticas", nombresEst);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
    private int[] ranNums() {
        int[] numeros = new int[3];
        for (int i = 0; i < 3; i++) {
            numeros[i] = random.nextInt(6) + 1;
        }
        return numeros;
    }

    private void mostrarDados(int[] resultados) {
        dadoImage1.setImageResource(getDrawableId(resultados[0]));
        dadoImage2.setImageResource(getDrawableId(resultados[1]));
        dadoImage3.setImageResource(getDrawableId(resultados[2]));

        // Hacer visibles las imágenes de los dados
        dadoImage1.setVisibility(View.VISIBLE);
        dadoImage2.setVisibility(View.VISIBLE);
        dadoImage3.setVisibility(View.VISIBLE);
    }
    private int getDrawableId(int numero) {
        switch (numero) {
            case 1:
                return R.drawable.dado1;
            case 2:
                return R.drawable.dado2;
            case 3:
                return R.drawable.dado3;
            case 4:
                return R.drawable.dado4;
            case 5:
                return R.drawable.dado5;
            case 6:
                return R.drawable.dado6;
            default:
                return R.drawable.dado1; // Default case
        }
    }
}