package com.euris.esame_finale.repository;

import com.euris.esame_finale.data.models.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigliettoRepository extends JpaRepository<Biglietto, Long> {
}
