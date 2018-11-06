package com.edwinacubillos.camara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView iFoto;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iFoto = findViewById(R.id.iFoto);
        name = Environment.getExternalStorageDirectory().getPath()+"/foto.jpg";
        Log.d("path", name);
    }

    public void onTakePictureClicked(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri salida = Uri.fromFile(new File(name));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, salida);
        startActivityForResult(intent, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK){
            Bitmap bMap = BitmapFactory.decodeFile(name);

            Matrix mat = new Matrix();
            mat.postRotate(90);
            Bitmap bMapRotate = Bitmap.createBitmap(bMap,
                    0,
                    0,
                    bMap.getWidth(),
                    bMap.getHeight(),
                    mat,
                    true);
            iFoto.setImageBitmap(bMapRotate);
        }
    }
}
