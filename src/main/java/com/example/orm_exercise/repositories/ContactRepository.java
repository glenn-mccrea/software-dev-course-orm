package com.example.orm_exercise.repositories;

import com.example.orm_exercise.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
