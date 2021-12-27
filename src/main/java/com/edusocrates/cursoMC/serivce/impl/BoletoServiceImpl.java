package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.model.PagamentoComBoleto;
import com.edusocrates.cursoMC.serivce.BoletoService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoServiceImpl implements BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instante);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(calendar.getTime());
;    }
}
