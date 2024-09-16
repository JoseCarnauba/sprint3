package br.com.fiap.stockagil.service;

import br.com.fiap.stockagil.entidade.PedidoPicklist;
import br.com.fiap.stockagil.repository.PedidoPicklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoPicklistService {

    @Autowired
    private PedidoPicklistRepository pedidoPicklistRepository;

}

