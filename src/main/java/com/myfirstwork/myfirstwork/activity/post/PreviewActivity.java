package com.myfirstwork.myfirstwork.activity.post;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.myfirstwork.myfirstwork.R;
import com.myfirstwork.myfirstwork.activity.main.LentaActivity;
import com.myfirstwork.myfirstwork.activity.post.adapter.AdapterGallery;
import com.myfirstwork.myfirstwork.activity.post.adapter.AdapterList;
import com.myfirstwork.myfirstwork.data.Query;
import com.myfirstwork.myfirstwork.data.source.Tag;
import com.myfirstwork.myfirstwork.data.source.Video;
import com.myfirstwork.myfirstwork.databinding.ActivityPreviewVideoBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG =" PreviewActivity" ;
    private static int COLUMS = 4;
    private  File file;
    private FirebaseStorage storage;
    private FirebaseFirestore firestore;
    AdapterGallery adapterGallery;
    DisplayMetrics displayMetrics;
    RadioButton[] radioButtons = new RadioButton[3];
    SearchView searchView;
    ListView listView;
    ActivityPreviewVideoBinding activityPreviewVideoBinding;
    AdapterList adapterList;
    ArrayList<Tag> tags = new ArrayList<>();
    ArrayList<String> textTags = new ArrayList<>();
    TextView textView, textInfo;
    Video video = new Video();
    Bundle bundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPreviewVideoBinding = DataBindingUtil.setContentView(this,R.layout.activity_preview_video);
        bundle = getIntent().getExtras();
        storage = FirebaseStorage.getInstance();
        firestore = FirebaseFirestore.getInstance();
        displayMetrics=getResources().getDisplayMetrics();
        Query query = new Query(this);
        tags= (ArrayList<Tag>) query.getTags();
        adapterList = new AdapterList(getString(tags));
        Log.i(LOG_TAG,query.getTags().toString());
        radioButtons[0]=findViewById(R.id.item0);
        radioButtons[1]=findViewById(R.id.item1);
        radioButtons[2]=findViewById(R.id.item2);
        searchView=findViewById(R.id.edit_tag);
        listView = findViewById(R.id.list);
        textView = findViewById(R.id.text_tag);
        textInfo = findViewById(R.id.edit_text);
        listView.setAdapter(adapterList);
        activityPreviewVideoBinding.progressBar.setVisibility(View.GONE);
        activityPreviewVideoBinding.item0.setOnClickListener(this);
        activityPreviewVideoBinding.item1.setOnClickListener(this);
        activityPreviewVideoBinding.item2.setOnClickListener(this);
        searchView.setQueryHint("Теги");
        listView.setVisibility(View.GONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listView.setVisibility(View.VISIBLE);
                adapterList.getFilter().filter(newText);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                listView.setVisibility(View.GONE);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text = new TextView(getApplicationContext());
                text.setText(adapterList.getFilter().convertResultToString(adapterList.getItem(position)));
                text.setBackgroundResource(R.drawable.button_view);
                textTags.add((String) text.getText());
                listView.setVisibility(View.GONE);
                String s="";
                for(int i = 0; i<textTags.size();i++){
                    String s1=textTags.get(i)+"   ";
                    s+=s1;
                }
                textView.setText(s);
            }
        });

        adapterGallery = new AdapterGallery(this);
        activityPreviewVideoBinding.gallery.setAdapter(adapterGallery);
        activityPreviewVideoBinding.gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("Range")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(LOG_TAG, String.valueOf(position));
                activityPreviewVideoBinding.group.check(radioButtons[position].getId());
                switch (position){
                    case 0:
                        video.setChild("vacansi/");
                        break;
                    case 1:
                        video.setChild("finder/");
                        break;
                    case 2:
                        video.setChild("blog/");
                        break;
                }
            }


            @SuppressLint("Range")
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        assert bundle != null;
        file = new File(Objects.requireNonNull(bundle.getString("video")));
        setVideoView(file.getAbsolutePath());
        activityPreviewVideoBinding.post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gallery:

                break;
            case R.id.item0:
                activityPreviewVideoBinding.gallery.setSelection(0,true);
                video.setChild("vacansi/");
                break;
            case R.id.item1:
                activityPreviewVideoBinding.gallery.setSelection(1,true);
                video.setChild("finder/");
                break;
            case  R.id.item2:
                activityPreviewVideoBinding.gallery.setSelection(2,true);
                video.setChild("blog/");
                break;
            case R.id.post:
                Query query = new Query(getApplicationContext());
                query.insertVideo(String.valueOf(bundle.getString("video")),String.valueOf(textInfo.getText()),
                        String.valueOf(textInfo.getText()));
                Intent intent = new Intent(PreviewActivity.this, LentaActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(PreviewActivity.this, "Video upload!", Toast.LENGTH_SHORT).show();
//                StorageReference storageReference = storage.getReferenceFromUrl("gs://myfirstwork-15e9c.appspot.com/").child(file.getName());
//                UploadTask uploadTask = storageReference.putFile(Uri.fromFile(file));
//                uploadTask.addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(PreviewActivity.this, "Error in upload!(", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                        activityPreviewVideoBinding.progressBar.setVisibility(View.VISIBLE);
//                        int progress = (int) ((taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount())*100);
//                        activityPreviewVideoBinding.progressBar.setProgress(progress);
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        video.setDislikes(0);
//                        video.setLikes(0);
//                        video.setName(file.getName());
//                        video.setInfo(textInfo.getText().toString());
//                        video.setPath(storageReference.getPath());
//                        video.setDataTime(LocalDateTime.now().getYear()+"."+LocalDateTime.now().getMonthOfYear()+
//                                LocalDateTime.now().getDayOfMonth()+'\n'+LocalDateTime.now().getHourOfDay()+":"+LocalDateTime.now().getMinuteOfHour());
//                        firestore.collection("videos").add(video).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Intent intent = new Intent(PreviewActivity.this, LentaActivity.class);
//                                startActivity(intent);
//                                finish();
//                                Toast.makeText(PreviewActivity.this, "Video upload!", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(PreviewActivity.this, "Video database error!", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });

                break;

        }
    }

    private void setVideoView(String path){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int)(displayMetrics.heightPixels-(180*displayMetrics.scaledDensity)));
        layoutParams.gravity= Gravity.CENTER;
        activityPreviewVideoBinding.videoPreview.setLayoutParams(layoutParams);
        activityPreviewVideoBinding.videoPreview.setVideoPath(path);
        activityPreviewVideoBinding.videoPreview.seekTo(1);
        activityPreviewVideoBinding.videoPreview.start();
    }

    private List<String> getString(ArrayList<Tag> tags){
        ArrayList<String> strings = new ArrayList<>();
        for(Tag tag : tags){
            strings.add(tag.getName());
        }
        return strings;
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.onActionViewCollapsed();
        }else{
            super.onBackPressed();
        }
    }
}
