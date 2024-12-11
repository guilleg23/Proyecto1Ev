package com.example.grupo3practicafinal1pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HabilidadesActivity extends AppCompatActivity {

    private int selectedCount = 0;

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
                    if (isChecked) {
                        selectedCount++;

                        if (selectedCount == 3) {
                            Intent intent = new Intent(HabilidadesActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        selectedCount--;
                    }
                }
            });
        }
    }
}
