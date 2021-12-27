package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.model.PagamentoComBoleto;

import java.util.Date;

public interface BoletoService {

     void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante);
}
