package com.api.syonet.desafio.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.api.syonet.desafio.model.Noticia;
import com.api.syonet.desafio.repository.NoticiaRepository;

@Service
public class NoticiaService {

    @Resource
    private NoticiaRepository noticiaRepository;

    public void save ( Noticia noticia ) {
        this.noticiaRepository.save( noticia );
    }

    public List< Noticia > findBySent ( Boolean sent ) {
        return this.noticiaRepository.findBySent( sent );
    }
}
