package com.myfirstwork.myfirstwork.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.myfirstwork.myfirstwork.data.source.Tag;
import com.myfirstwork.myfirstwork.data.source.User;
import com.myfirstwork.myfirstwork.data.source.Video;

import java.util.ArrayList;
import java.util.List;

public class Query {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public Query(Context context){
        this.dbHelper=new DBHelper(context);
        this.database = dbHelper.getReadableDatabase();
    }

    public void close(){
        database.close();
        dbHelper.close();
    }

    public List<Tag> getTags(){
        ArrayList <Tag> tags = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * from "+DBHelper.TABLE_TAGS ,null);
        cursor.moveToNext();
        for (int i= 0;!cursor.isAfterLast();i++){
            Tag tag = new Tag();
            tag.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)));
            tag.setName(cursor.getString(cursor.getColumnIndex(DBHelper.TAGS_NAME)));
            tags.add(tag);
            cursor.moveToNext();
        }
        return tags;
    }

    public Tag getTagByID(int id){
        Tag tag = new Tag();
        Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABLE_TAGS+" WHERE " +DBHelper.ID+" = "+id,null);
        cursor.moveToNext();
        tag.setName(cursor.getString(cursor.getColumnIndex(DBHelper.TAGS_NAME)));
        tag.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)));
        return tag;
    }

    public void insertTag(Tag tag){
        ContentValues values = new ContentValues();
        values.put(DBHelper.ID,tag.getId());
        values.put(DBHelper.TAGS_NAME,tag.getName());
        database.insert(DBHelper.TABLE_TAGS,null,values);
    }

    public ArrayList<Video> getVideos(){
        ArrayList<Video> videos = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABLE_VIDEO,null);
        cursor.moveToNext();
        for (int i= 0; !cursor.isAfterLast();i++){
            Video video = new Video();
            video.setName(cursor.getString(cursor.getColumnIndex(DBHelper.VIDEO_NAME)));
            video.setPath(cursor.getString(cursor.getColumnIndex(DBHelper.VIDEP_PATH)));
            video.setInfo(cursor.getString(cursor.getColumnIndex(DBHelper.VIDEO_INFO)));
            video.setLikes(cursor.getInt(cursor.getColumnIndex(DBHelper.VIDEO_LIKE)));
            video.setDislikes(cursor.getInt(cursor.getColumnIndex(DBHelper.VIDEO_DISLIKE)));
            video.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)));
            video.setUser_id(cursor.getInt(cursor.getColumnIndex(DBHelper.VIDEO_USERID)));
            videos.add(video);
            cursor.moveToNext();
        }
        return videos;
    }

    public void insertVideo(String path,String name, String info){
        ContentValues values = new ContentValues();
        values.put(DBHelper.VIDEO_NAME,name);
        values.put(DBHelper.VIDEP_PATH,path);
        values.put(DBHelper.VIDEO_INFO,info);
        values.put(DBHelper.VIDEO_USERID,0);
        database.insert(DBHelper.TABLE_VIDEO,null,values);
        values.clear();
    }

    public void updateLikeVideo(int i,String path){
        database.execSQL("Update "+DBHelper.TABLE_VIDEO+" set "+DBHelper.VIDEO_LIKE+"= "+i+" where "+DBHelper.VIDEP_PATH+" = \""+path+"\"");
    }

    public void updateDislikeVideo(int i,String path){
        database.execSQL("Update "+DBHelper.TABLE_VIDEO+" set "+DBHelper.VIDEO_DISLIKE+"= "+i+" where "+DBHelper.VIDEP_PATH+" = \""+path+"\"");
    }

    public User getUserByPath(String path){
        User user = new User();
        Cursor cursor = database.rawQuery("SELECT * FROM "+DBHelper.TABLE_USERS+" WHERE "+DBHelper.ID+" = " +
                "( SELECT "+DBHelper.VIDEO_USERID+" FROM "+DBHelper.TABLE_VIDEO+
                " WHERE "+DBHelper.VIDEP_PATH+"= \""+path+"\")",null);
        cursor.moveToNext();
        try {
            user.setName(cursor.getString(cursor.getColumnIndex(DBHelper.USERS_NAME)));
            user.setOld(cursor.getInt(cursor.getColumnIndex(DBHelper.USERS_OLD)));
            user.setPost(cursor.getString(cursor.getColumnIndex(DBHelper.USERS_POST)));
            user.setSex(cursor.getString(cursor.getColumnIndex(DBHelper.USERS_SEX)));
        }catch (CursorIndexOutOfBoundsException e){
            Log.e("LOG", String.valueOf(e));
        }
        return user;
    }
}
