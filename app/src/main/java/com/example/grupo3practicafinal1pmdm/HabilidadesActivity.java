package com.example.grupo3practicafinal1pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HabilidadesActivity extends AppCompatActivity {

    private int selectedCount = 0;
    private ArrayList<String> habilidadesSeleccionadas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_habilidades);

        CheckBox[] checkBoxes = new CheckBox[]{
                findViewById(R.id.checkBoxAtletismo),
                findViewById(R.id.checkBoxAcrobacia),
                findViewById(R.id.checkBoxJuegoDeManos),
                findViewById(R.id.checkBoxSigilo),
                findViewById(R.id.checkBoxArcano),
                findViewById(R.id.checkBoxHistoria),
                findViewById(R.id.checkBoxInvestigacion),
                findViewById(R.id.checkBoxNaturaleza),
                findViewById(R.id.checkBoxReligion),
                findViewById(R.id.checkBoxMedicina),
                findViewById(R.id.checkBoxPercepcion),
                findViewById(R.id.checkBoxPerspicacia),
                findViewById(R.id.checkBoxSupervivencia),
                findViewById(R.id.checkBoxTratoConAnimales),
                findViewById(R.id.checkBoxEngano),
                findViewById(R.id.checkBoxIntimidacion),
                findViewById(R.id.checkBoxInterpretacion),
                findViewById(R.id.checkBoxPersuasion)
        };

        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String habilidad = buttonView.getText().toString();
                    if (isChecked) {
                        habilidadesSeleccionadas.add(habilidad);
                        selectedCount++;

                        if (selectedCount == 3) {
                            enviarResultado();
                        }
                    } else {
                        habilidadesSeleccionadas.remove(habilidad);
                        selectedCount--;
                    }
                }
            });
        }
    }

    private void enviarResultado() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("habilidadesSeleccionadas", habilidadesSeleccionadas);

        Intent intent = new Intent();
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();
    }
}
