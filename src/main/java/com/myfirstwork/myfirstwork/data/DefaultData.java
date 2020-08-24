package com.myfirstwork.myfirstwork.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.myfirstwork.myfirstwork.R;

public class DefaultData {
    public static void createTagsName(SQLiteDatabase database){
        String [] names = {"IT", "Строительство", "Транспорт", "Образование", "Медицина","Менеджмент"};
        for (String name : names){
        ContentValues values = new ContentValues();
        values.put(DBHelper.TAGS_NAME,name);
        database.insert(DBHelper.TABLE_TAGS,null,values);
        }
    }

    public static void createVideoDefault(SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put(DBHelper.VIDEP_PATH,"android.resource://com.myfirstwork.myfirstwork"+"/"+ R.raw.video1);
        values.put(DBHelper.VIDEO_INFO,"Анастасия. 19 лет. Студентка РГРТУ. Работаю маркетологом.");
        values.put(DBHelper.VIDEO_USERID,1);
        values.put(DBHelper.VIDEO_NAME,"Анастасия, 19 лет. Маркетолог.");
        database.insert(DBHelper.TABLE_VIDEO,null,values);
        values.clear();

        values.put(DBHelper.VIDEP_PATH,"android.resource://com.myfirstwork.myfirstwork"+"/"+ R.raw.video2);
        values.put(DBHelper.VIDEO_INFO,"Команда MY BOX");
        values.put(DBHelper.VIDEO_USERID,2);
        values.put(DBHelper.VIDEO_NAME,"MY BOX");
        database.insert(DBHelper.TABLE_VIDEO,null,values);
        values.clear();

        values.put(DBHelper.VIDEP_PATH,"android.resource://com.myfirstwork.myfirstwork"+"/"+ R.raw.video3);
        values.put(DBHelper.VIDEO_INFO,"Даниил. 20 лет. Кассир");
        values.put(DBHelper.VIDEO_USERID,3);
        values.put(DBHelper.VIDEO_NAME,"Кассир");
        database.insert(DBHelper.TABLE_VIDEO,null,values);
        values.clear();

        values.put(DBHelper.VIDEP_PATH,"android.resource://com.myfirstwork.myfirstwork"+"/"+ R.raw.video4);
        values.put(DBHelper.VIDEO_INFO,"Сергей, 21 год. Студент. Подрабатываю дворником.");
        values.put(DBHelper.VIDEO_USERID,4);
        values.put(DBHelper.VIDEO_NAME,"Дворник");
        database.insert(DBHelper.TABLE_VIDEO,null,values);
        values.clear();
    }

    public static void createUsersDefault(SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put(DBHelper.USERS_NAME,"Анастасия");
        values.put(DBHelper.USERS_OLD,19);
        values.put(DBHelper.USERS_POST,"Менеджер");
        values.put(DBHelper.USERS_SEX,"Женский");
        database.insert(DBHelper.TABLE_USERS,null,values);

        values.put(DBHelper.USERS_NAME,"My Box");
        values.put(DBHelper.USERS_OLD,1);
        values.put(DBHelper.USERS_POST,"Стартап");
        values.put(DBHelper.USERS_SEX,"");
        database.insert(DBHelper.TABLE_USERS,null,values);

        values.put(DBHelper.USERS_NAME,"Даниил");
        values.put(DBHelper.USERS_OLD,20);
        values.put(DBHelper.USERS_POST,"Кассир");
        values.put(DBHelper.USERS_SEX,"Мужской");
        database.insert(DBHelper.TABLE_USERS,null,values);

        values.put(DBHelper.USERS_NAME,"Сергей");
        values.put(DBHelper.USERS_OLD,21);
        values.put(DBHelper.USERS_POST,"Дворник");
        values.put(DBHelper.USERS_SEX,"Мужской");
        database.insert(DBHelper.TABLE_USERS,null,values);
    }
}
