package com.myfirstwork.myfirstwork.activity.main;

import android.os.Bundle;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.myfirstwork.myfirstwork.R;

import java.io.File;
import java.io.IOException;

public class History extends AppCompatActivity {
    private VideoView videoView;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl("gs://myfirstwork-15e9c.appspot.com/vacansi/VIDEO_20191105_035338_1353030900698121628.mp4");
        videoView=findViewById(R.id.video);
        try {
            File localFile = File.createTempFile("video","mp4");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    videoView.setVideoPath(localFile.getAbsolutePath());
                    videoView.start();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
