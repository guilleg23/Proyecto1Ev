package com.example.grupo3practicafinal1pmdm;

import android.content.Intent;
import android.os.Bundle;
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
                            nombreEditText = findViewById(R.id.nombreEditText);
                            nombreEditText.setHint("Nombre");
                            nombreEditText.setText("");
                            String nombrePersonaje = bundle.getString("");
                            String clase = bundle.getString("");
                            Bundle stats = bundle.getBundle("");
                            Bundle habilidades = bundle.getBundle("");

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
        System.out.println("Usuario: " + Usuario + "\n"+
                            "Nombre: " +nombre + "\n" + "Clase: " + clase +"\n" +
                            "Estadisticas:"+ "\n"+
                            "Fuerza:"+ estadisticas.getString("") +
                            "Destreza:"+ estadisticas.getString("") +
                            "Constitucion:"+ estadisticas.getString("") +
                            "Inteligencia:"+ estadisticas.getString("") +
                            "Sabiduria:"+ estadisticas.getString("") +
                            "Carisma:"+ estadisticas.getString("")



        );
    }
}
