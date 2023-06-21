package com.example.jardimviver;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jardimviver.constants.ConstantsDB;
import com.example.jardimviver.dto.ParentDTO;
import com.example.jardimviver.validors.InputValidator;

import java.util.function.Function;

public class FormActivity extends AppCompatActivity {

  Button btnRegister;
  EditText inputName, inputEmail, inputSurname, inputAge, inputAddress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form);

    inputName = findViewById(R.id.inputName);
    inputEmail = findViewById(R.id.inputEmail);
    inputAge = findViewById(R.id.inputAge);
    inputAddress = findViewById(R.id.inputAddress);
    inputSurname = findViewById(R.id.inputSurname);
    btnRegister = findViewById(R.id.btnRegister);
    btnRegister.setOnClickListener(btnRegisterAction);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle("Cadastro de Pais");
      actionBar.setHomeButtonEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
    }

  }

  View.OnClickListener btnRegisterAction = view -> {

    btnRegister.setEnabled(false);
    String name = inputName.getText().toString();
    String surname = inputSurname.getText().toString();
    String address = inputAddress.getText().toString();
    String email = inputEmail.getText().toString();
    String ageString = inputAge.getText().toString();

    boolean isValid = validateInputs(name, surname, address, email, ageString);

    System.out.println(isValid);

    if (!isValid) {
      btnRegister.setEnabled(true);
      return;
    }
    ParentDTO parentDTO = createParentDto();
    saveData(parentDTO);

    Context context = getApplicationContext();
    CharSequence text = "Pai cadastrado com sucesso!";
    int duration = Toast.LENGTH_LONG;

    Toast toast = Toast.makeText(context, text, duration);
    toast.show();

    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("REFRESH_LIST", true);
    startActivity(intent);
    finish();
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

  private boolean validateInputs(String name, String surname, String address, String email, String ageString) {
    Function<String, Boolean> isNotEmptyAndShort = InputValidator::isNotEmptyAndShort;
    Function<String, Boolean> isEmailValid = InputValidator::isValidEmail;
    Function<String, Boolean> isAgeValid = (String input) -> InputValidator.isValidAge(Integer.valueOf(input));

    boolean isValid = true;

    if (!isNotEmptyAndShort.apply(name)) {
      isValid = false;
      inputName.setError("O nome é obrigatório");
    }

    if (!isNotEmptyAndShort.apply(surname)) {
      isValid = false;

      inputSurname.setError("O sobrenome é obrigatório");
    }

    if (!isNotEmptyAndShort.apply(address)) {
      isValid = false;
      inputAddress.setError("O endereço é obrigatório");
    }

    if (!isEmailValid.apply(email)) {
      isValid = false;
      inputEmail.setError("O email é inválido");
    }

    if (!isAgeValid.apply(ageString)) {
      isValid = false;
      inputAge.setError("A idade é inválida");
    }
    return isValid;

  }

  private void saveData(ParentDTO parentDTO) {
    SQLiteDatabase database = openOrCreateDatabase(ConstantsDB.DATABASE_NAME, MODE_PRIVATE, null);
    database.execSQL("INSERT INTO parents (name, surname, address, email, age) VALUES ('" + parentDTO.getName() + "', '" + parentDTO.getSurname() + "', '" + parentDTO.getAddress() + "', '" + parentDTO.getEmail() + "', '" + parentDTO.getAge() + "')");
    database.close();
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

}