package com.api.syonet.desafio.service.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.syonet.desafio.model.Noticia;
import com.api.syonet.desafio.service.NoticiaService;

@RestController
@RequestMapping( "/news" )
public class NoticiaController {

    @Resource
    private NoticiaService noticiaService;

    @PostMapping
    public void save ( @RequestBody Noticia noticia ) {
        this.noticiaService.save( noticia );
    }
}
