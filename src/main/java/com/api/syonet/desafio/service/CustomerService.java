package com.api.syonet.desafio.service;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.api.syonet.desafio.model.Customer;
import com.api.syonet.desafio.model.Noticia;
import com.api.syonet.desafio.repository.CustomerRepository;

@Service
public class CustomerService {

    @Resource
    private CustomerRepository customerRepository;

    @Resource
    private NoticiaService noticiaService;

    @Resource
    private EmailService emailService;

    public void save ( Customer customer ) {
        this.customerRepository.save( customer );
    }

    public List< Customer > findAll () {
        return this.customerRepository.findAll();
    }

    @Scheduled( cron = "0 0 8 * * *" )
    public void sendNews () {
        List< Noticia > noticiaList = this.noticiaService.findBySent( false );
        List< Customer > customerList = this.findAll();
        customerList.forEach( customer -> {
            //para cada cliente enviar as noticias
            noticiaList.forEach( noticia -> {
                try {
                    if ( customer.getBirthday().isEqual( LocalDate.now() ) ) {
                        this.emailService.SendMail(
                            "Feliz aniversário ".concat( customer.getName() ).concat( "!" )
                                .concat( "\n" )
                                .concat( noticia.getDescription() ), noticia.getTitle(), customer.getEmail()
                        );
                    } else {
                        this.emailService.SendMail(
                            noticia.getDescription(), noticia.getTitle(), customer.getEmail()
                        );
                    }
                    noticia.setSent( true );
                    this.noticiaService.save( noticia );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            } );
        } );
    }
}
