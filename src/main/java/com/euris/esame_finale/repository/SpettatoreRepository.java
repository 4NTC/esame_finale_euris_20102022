package com.euris.esame_finale.repository;

import com.euris.esame_finale.data.models.Spettatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpettatoreRepository extends JpaRepository<Spettatore, Long> {
}
