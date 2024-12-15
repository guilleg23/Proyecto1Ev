package com.example.grupo3practicafinal1pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
private TextView nombreEditText;
String Usuario = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LayoutLogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Login.RESULT_OK && result.getData() != null) {
                        Bundle bundle = result.getData().getExtras();
                        if(bundle != null){
                            Log.d("hola", "onActivityResult: OK ");
                            nombreEditText = findViewById(R.id.nombreEditText);
                            nombreEditText.setHint("Nombre");
                            nombreEditText.setText("");
                            String nombrePersonaje = bundle.getString("NombrePersonaje");
                            String clase = bundle.getString("ClasePersonaje");
                            Bundle stats = bundle.getBundle("Estadisticas");
                            Bundle habilidades = bundle.getBundle("Habilidades");
                            imprimirPersonaje(nombrePersonaje,clase,stats,habilidades);
                        }
                    }
                }
            }
    );

    public void CrearPersonaje(View view){
        nombreEditText = findViewById(R.id.nombreEditText);
        Intent intent = new Intent(this, MainActivity.class);
        Usuario =  nombreEditText.getText().toString();
        intent.putExtra("nombre",Usuario);
        startForResult.launch(intent);
    }

    private void imprimirPersonaje(String nombre, String clase,
                                   Bundle estadisticas, Bundle habilidades){

        ArrayList<String> nombresEstadisticas = estadisticas.getStringArrayList("NombresEstadisticas");

        Log.d("hola","Usuario: " + Usuario + "\n"+
                            "Nombre: " +nombre + "\n" + "Clase: " + clase +"\n" +
                            "Estadisticas:"+ "\n");
        for (int i = 0; i <nombresEstadisticas.size() ; i++) {
            Log.d("hola",nombresEstadisticas.get(i) + " " + estadisticas.getInt(nombresEstadisticas.get(i)));
        }

    }
}
