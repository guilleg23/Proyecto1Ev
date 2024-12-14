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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.carismaLbl), (v, insets) -> {
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
                    if (result.getResultCode() == Login.RESULT_OK) {
                        Intent intent = result.getData();
                        if(intent != null){
                            nombreEditText = findViewById(R.id.nombreEditText);
                            nombreEditText.setHint("Nombre");
                            nombreEditText.setText("");
                        }
                    }
                }
            }
    );

    public void CrearPersonaje(View view){
        nombreEditText = findViewById(R.id.nombreEditText);

        //Intent intent = new Intent(this, //falta la clase);
        //intent.putExtra("nombre",nombreEditText.getText().toString());
        //startForResult.launch(intent);
    }
}
