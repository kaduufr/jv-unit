package com.example.jardimviver.dto;

import androidx.annotation.NonNull;

public class ParentDTO {
    String name, surname, address, email;
    Integer age;

    public ParentDTO(
            String name,
            String surname,
            String address,
            String email,
            Integer age
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
