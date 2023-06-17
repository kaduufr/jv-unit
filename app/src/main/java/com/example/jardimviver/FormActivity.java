package com.example.jardimviver;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jardimviver.dto.ParentDTO;

public class FormActivity extends AppCompatActivity {

  Button btnRegister;
  EditText inputName, inputEmail, inputSurname, inputAge, inputAddress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form);

    inputName = (EditText) findViewById(R.id.inputName);
    inputEmail = (EditText) findViewById(R.id.inputEmail);
    inputAge = (EditText) findViewById(R.id.inputAge);
    inputAddress = (EditText) findViewById(R.id.inputAddress);
    inputSurname = (EditText) findViewById(R.id.inputSurname);
    btnRegister = (Button) findViewById(R.id.btnRegister);
    btnRegister.setOnClickListener(btnRegisterAction);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle("Cadastro de Pais");
    }

  }
  View.OnClickListener btnRegisterAction = view -> {
    validateInputs();
    ParentDTO parent = createParentDto();

  };

  private ParentDTO createParentDto() {
    String name = inputName.getText().toString();
    String surname = inputSurname.getText().toString();
    String address = inputAddress.getText().toString();
    String email = inputEmail.getText().toString();
    String ageString = inputAge.getText().toString();

    return new ParentDTO(
        name,
        surname,
        address,
        email,
        Integer.parseInt(ageString));
  }

  private void validateInputs() {
    EditText[] inputs = {inputName, inputEmail, inputSurname,inputAge, inputAddress};

    for (EditText input : inputs) {
      if (TextUtils.isEmpty(input.getText().toString())) {
        input.setError("Campo obrigatorio.");
      }
    }
  }
}