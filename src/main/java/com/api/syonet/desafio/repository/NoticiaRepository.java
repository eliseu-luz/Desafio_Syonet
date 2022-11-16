package com.api.syonet.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.syonet.desafio.model.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository< Noticia, Long > {
    List< Noticia > findBySent ( Boolean sent );
}
