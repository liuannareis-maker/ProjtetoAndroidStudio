package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CapturaActivity extends AppCompatActivity {

    private ImageView imgCap;
    private Uri photoUri;
    private File photoFile;

    private ActivityResultLauncher<Intent> cameraLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captura);

        imgCap = findViewById(R.id.imgCap);
        Button bntCap = findViewById(R.id.bntCap);
        Button bntShare = findViewById(R.id.bntShare);

        // Configura o retorno da câmera
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        imgCap.setImageURI(photoUri);
                    } else {
                        Toast.makeText(this, "Captura cancelada!", Toast.LENGTH_SHORT).show();
                    }
                });

        // Tirar foto
        bntCap.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            } else {
                abrirCamera();
            }
        });

        // Compartilhar no WhatsApp
        bntShare.setOnClickListener(v -> {
            if (photoUri != null) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                shareIntent.putExtra(Intent.EXTRA_STREAM, photoUri);
                shareIntent.setPackage("com.whatsapp");

                try {
                    startActivity(shareIntent);
                } catch (Exception e) {
                    Toast.makeText(this, "WhatsApp não instalado!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Nenhuma foto capturada!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void abrirCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                photoFile = criarArquivoImagem();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao criar arquivo!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(
                        this,
                        getPackageName() + ".provider",
                        photoFile
                );

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                cameraLauncher.launch(intent);
            }
        }
    }

    private File criarArquivoImagem() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir("Pictures");
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }
}


