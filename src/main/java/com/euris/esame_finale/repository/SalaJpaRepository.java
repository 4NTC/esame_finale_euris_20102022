package com.euris.esame_finale.repository;

import com.euris.esame_finale.data.models.SalaCinematografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaJpaRepository extends JpaRepository<SalaCinematografica, Long> {
}
