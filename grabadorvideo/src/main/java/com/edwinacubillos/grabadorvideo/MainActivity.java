package com.edwinacubillos.grabadorvideo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.iVideo);
        name = Environment.getExternalStorageDirectory() + "/video.mp4";
    }

    public void onGrabarClicked(View view) {
        Intent intent = new Intent (MediaStore.ACTION_VIDEO_CAPTURE);
        Uri salida = Uri.fromFile(new File(name));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, salida);
        startActivityForResult(intent, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            videoView.setVideoURI(uri);
            videoView.setMediaController(new MediaController(this));
            videoView.start();
        }
    }
}
