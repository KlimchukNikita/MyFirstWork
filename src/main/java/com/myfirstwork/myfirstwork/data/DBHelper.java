package com.myfirstwork.myfirstwork.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static String DATABASENAME ="MFWDatabase";
    public static int VERSION = 6;

    public static String ID="_id";
    //TAGS
    public static String TABLE_TAGS="tags_table";
    public static String TAGS_NAME="name";

    //VIDEOS
    public static String TABLE_VIDEO="videos";
    public static String VIDEP_PATH="path";
    public static String VIDEO_NAME="name";
    public static String VIDEO_LIKE="likes";
    public static String VIDEO_DISLIKE="dislike";
    public static String VIDEO_USERID="user_id";
    public static String VIDEO_INFO="info";

    //USERS
    public static String TABLE_USERS="users";
    public static String USERS_NAME="name";
    public static String USERS_OLD="old";
    public static String USERS_SEX="sex";
    public static String USERS_POST="post";
    public static String USERS_ORGANIZATION_ID="organization_id";

    //ORGANIZATION
    public static String TABLE_ORGANIZATION="organization";

    public DBHelper(Context context){
        super(context,DATABASENAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_TAGS+" ("+
                ID+" integer not null primary key autoincrement unique, "+
                TAGS_NAME+" text not null "+
                ")");
        db.execSQL("create table "+TABLE_VIDEO+" ("+
                ID+" integer not null primary key autoincrement unique, "+
                VIDEP_PATH+" text not null, " +
                VIDEO_NAME+" text not null, " +
                VIDEO_LIKE+" integer default 0, " +
                VIDEO_DISLIKE+" integer default 0, " +
                VIDEO_INFO+" text, "+
                VIDEO_USERID+" integer)");
        db.execSQL("create table "+TABLE_USERS+" (" +
                ID+" integer not null primary key autoincrement unique, "+
                USERS_NAME+" text not null, " +
                USERS_OLD+" integer not null, " +
                USERS_SEX+" text not null, " +
                USERS_POST+" text )");
        DefaultData.createTagsName(db);
        DefaultData.createUsersDefault(db);
        DefaultData.createVideoDefault(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("drop table if exists " + TABLE_TAGS);
            db.execSQL("drop table if exists " + TABLE_USERS);
            db.execSQL("drop table if exists " + TABLE_VIDEO);
            onCreate(db);
        }
    }
}
