package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DogActivity extends AppCompatActivity {

    Button bntDog;
    ImageView imgDog;
    TextView txtStatus;
    Button bntVoltando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog);

        imgDog = findViewById(R.id.imgDog);
        txtStatus = findViewById(R.id.txtStatus);
        bntDog = findViewById(R.id.bntDog);
        bntVoltando = findViewById(R.id.bntVoltando);
        bntVoltando.setOnClickListener(v -> finish());


        bntDog.setOnClickListener(v -> fetchDog());
    }

    private void fetchDog() {
        txtStatus.setText("Buscando cachorro...");

        String url = "https://dog.ceo/api/breeds/image/random";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        String imageUrl = response.getString("message");
                        txtStatus.setText("Carregando imagem...");


                        new Thread(() -> {
                            try {
                                URL imgUrl = new URL(imageUrl);
                                HttpURLConnection connection = (HttpURLConnection) imgUrl.openConnection();
                                connection.setDoInput(true);
                                connection.connect();
                                InputStream input = connection.getInputStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(input);

                                runOnUiThread(() -> {
                                    imgDog.setImageBitmap(bitmap);
                                    txtStatus.setText("Lindo Doguinho!!!");


                                });

                            } catch (Exception e) {
                                runOnUiThread(() -> txtStatus.setText("Erro ao carregar imagem."));
                                e.printStackTrace();
                            }
                        }).start();

                    } catch (Exception e) {
                        txtStatus.setText("Erro ao processar resposta.");
                        e.printStackTrace();
                    }
                },
                error -> txtStatus.setText("Erro: " + error.getMessage())
        );

        queue.add(request);
    }
}
