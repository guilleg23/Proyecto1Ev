package com.example.grupo3practicafinal1pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

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
        if (!Usuario.isEmpty()){
            intent.putExtra("nombre",Usuario);
            startForResult.launch(intent);
        }else{
            Toast.makeText(this, "Escribe el Nombre!", Toast.LENGTH_SHORT).show();
        }
    }

    private void imprimirPersonaje(String nombre, String clase,
                                   Bundle estadisticas, Bundle habilidades){
        LinearLayout lay = findViewById(R.id.OverlayLayout);
        lay.setVisibility(View.VISIBLE);
        ArrayList<String> nombresEstadisticas = estadisticas.getStringArrayList("NombresEstadisticas");
        ArrayList<String> habilidadesSeleccionadas = habilidades.getStringArrayList("habilidadesSeleccionadas");
        Log.d("hola","Usuario: " + Usuario + "\n"+
                            "Nombre: " +nombre + "\n" + "Clase: " + clase +"\n" +
                            "Estadisticas:"+ "\n");
        TextView usr = findViewById(R.id.UsuarioFinal);
        TextView nom =  findViewById(R.id.NombreFinal);
        TextView cla = findViewById(R.id.ClaseFinal);
        usr.setText(Usuario);
        nom.setText(nombre);
        cla.setText(clase);
        TextView aby1 = findViewById(R.id.habilidad1);
        TextView aby2 = findViewById(R.id.habilidad2);
        TextView aby3 = findViewById(R.id.habilidad3);
        TextView[] aby = {aby1,aby2,aby3};
        TextView stat1 = findViewById(R.id.fuerzaOverlay);
        TextView stat2 = findViewById(R.id.destrezaOverlay);
        TextView stat3 = findViewById(R.id.constitucionOverlay);
        TextView stat4 = findViewById(R.id.inteligenciaOverlay);
        TextView stat5 = findViewById(R.id.sabiduriaOverlay);
        TextView stat6 = findViewById(R.id.carismaOverlay);
        TextView[] stat ={stat1,stat2,stat3,stat4,stat5,stat6};
        for (int i = 0; i <nombresEstadisticas.size() ; i++) {
            Log.d("hola",nombresEstadisticas.get(i) + " " + estadisticas.getInt(nombresEstadisticas.get(i)));
            String a = nombresEstadisticas.get(i) + " " + estadisticas.getInt(nombresEstadisticas.get(i));
            stat[i].setText(a);
        }
        for (int i = 0; i <habilidadesSeleccionadas.size() ; i++) {
            Log.d("hola", habilidadesSeleccionadas.get(i));
            aby[i].setText(habilidadesSeleccionadas.get(i));

        }

    }
    public void CerrarLayout(View view){
        LinearLayout a = findViewById(R.id.OverlayLayout);
        a.setVisibility(View.GONE);
    }
}
