package com.example.jardimviver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.jardimviver.adapters.AdapterParents;
import com.example.jardimviver.constants.ConstantsDB;
import com.example.jardimviver.dto.ParentDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

  private SQLiteDatabase database;
  ListView listParents;

  FloatingActionButton btnAddParent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    btnAddParent = findViewById(R.id.btnAddParent);
    listParents = findViewById(R.id.listViewParents);

    createDatabase();
    listParents();
    btnAddParent.setOnClickListener(btnAddParentAction);
  }

  View.OnClickListener btnAddParentAction = view -> {
    Intent intent = new Intent(this, FormActivity.class);
    startActivity(intent);
  };


  public void createDatabase() {
    try {
      database = openOrCreateDatabase(ConstantsDB.DATABASE_NAME, MODE_PRIVATE, null);
      database.execSQL("CREATE TABLE IF NOT EXISTS parents (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, surname VARCHAR, address VARCHAR, email VARCHAR, age INTEGER)");
    } catch (Exception error) {
      error.printStackTrace();
    }
  }
  private void listParents() {
    try {
      Cursor data = database.rawQuery("SELECT * FROM parents", null);
      ArrayList<ParentDTO> clients = new ArrayList<>();
      AdapterParents adapter = new AdapterParents(clients, this);
      data.moveToFirst();
      do {
        ParentDTO client = new ParentDTO(
            data.getString(1),
            data.getString(2),
            data.getString(3),
            data.getString(4),
            data.getInt(5)
        );
        clients.add(client);
      } while (data.moveToNext());

      System.out.println(clients);

      listParents.setAdapter(adapter);

      data.close();
    } catch (Exception error) {
      error.printStackTrace();
    }
  }
}