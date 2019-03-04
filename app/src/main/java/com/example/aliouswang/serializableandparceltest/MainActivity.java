package com.example.aliouswang.serializableandparceltest;

import android.os.HandlerThread;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSerializableClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long totalTime= 0;
                    for (int i = 0; i < 100; i++) {
                        totalTime += testWriteSerializable(1000);
                    }
                    Log.e("take_time", "serializable avg:" + totalTime/ 100 + "ms");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onParcelClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long totalTime= 0;
                    for (int i = 0; i < 100; i++) {
                        totalTime += testWriteParcelable(1000);
                    }
                    Log.e("take_time", "parcel avg:" + totalTime/ 100 + "ms");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private long testWriteSerializable(int count) throws IOException {
        File file = new File(getFilesDir() + File.separator + "person");
        file.deleteOnExit();
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Person person = new Person(i, "Jake" + i);
            objectOutputStream.writeObject(person);
        }
        objectOutputStream.flush();
        long takeTime = System.currentTimeMillis() - startTime;
        Log.e("take_time", "serializable:" + takeTime + "ms");

        objectOutputStream.close();
        fileOutputStream.close();
        return takeTime;
    }

    private long testWriteParcelable(int count) throws IOException {
        File file = new File(getFilesDir() + File.separator + "person");
        file.deleteOnExit();
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Parcel parcel = Parcel.obtain();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            Person2 person2 = new Person2(i, "Jake" + i);
            parcel.writeParcelable(person2, 0);
        }

        fileOutputStream.write(parcel.marshall());

        long takeTime = System.currentTimeMillis() - startTime;
        Log.e("take_time", "parcel:" + takeTime + "ms");

        fileOutputStream.flush();
        fileOutputStream.close();
        return takeTime;
    }
}
