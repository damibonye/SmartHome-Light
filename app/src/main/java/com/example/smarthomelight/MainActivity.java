package com.example.smarthomelight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutRaiz;
    private TextView tvEstadoLuz;
    private Button btnInterruptor;

    // Estado del interruptor
    private boolean luzEncendida = false;

    // RAMA feature/contador-luces
    private TextView tvContador;
    private int contadorPulsaciones = 0;

    // RAMA feature/modo-noche
    private SwitchCompat switchModoNoche;
    private TextView tvTitulo;

    // RAMA feature/boton-panico
    private Button btnPanico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutRaiz = findViewById(R.id.layoutRaiz);
        tvEstadoLuz = findViewById(R.id.tvEstadoLuz);
        btnInterruptor = findViewById(R.id.btnInterruptor);

        // RAMA feature/contador-luces
        tvContador = findViewById(R.id.tvContador);
        tvContador.setText(getString(R.string.texto_contador, contadorPulsaciones));

        btnInterruptor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Alternamos el estado
                luzEncendida = !luzEncendida;

                if (luzEncendida) {
                    tvEstadoLuz.setText(R.string.estado_luz_encendida);
                    tvEstadoLuz.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.estado_encendido));
                } else {
                    tvEstadoLuz.setText(R.string.estado_luz_apagada);
                    tvEstadoLuz.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.estado_apagado));
                }

                // RAMA feature/contador-luces
                contadorPulsaciones++;
                tvContador.setText(getString(R.string.texto_contador, contadorPulsaciones));
            }
        });

        // RAMA feature/modo-noche
        tvTitulo = findViewById(R.id.tvTitulo);
        switchModoNoche = findViewById(R.id.switchModoNoche);

        switchModoNoche.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layoutRaiz.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.fondo_oscuro));
                    tvTitulo.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.texto_oscuro));
                } else {
                    layoutRaiz.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.fondo_claro));
                    tvTitulo.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.texto_claro));
                }
            }
        });

        // RAMA feature/boton-panico
        btnPanico = findViewById(R.id.btnPanico);

        btnPanico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.toast_alerta_panico, Toast.LENGTH_LONG).show();
            }
        });
    }
}
