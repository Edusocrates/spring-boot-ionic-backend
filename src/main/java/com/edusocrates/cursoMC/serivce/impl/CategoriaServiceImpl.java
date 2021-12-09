package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.DTO.CategoriaDTO;
import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.repository.CategoriaRepository;
import com.edusocrates.cursoMC.serivce.CategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoriaServiceImpl implements CategoriaService {


    @Autowired
    private CategoriaRepository repository;

    @Override
    public Categoria getCategoriaById(Integer id) {
        Categoria categoria = findById(id);
        return categoria;
    }

    @Override
    public Categoria insertCategoria(Categoria categoria) {
        categoria.setId(null);
        return repository.save(categoria);
    }

    @Override
    public Categoria updateCategoria(Integer id, Categoria requestCategoria) {
        Categoria categoriaBase = findById(id);
        BeanUtils.copyProperties(requestCategoria,categoriaBase);//realiza a copia do objeto para atualizar
        Categoria savedCategoria = repository.save(categoriaBase);
        return savedCategoria;
    }

    @Override
    public void deleteCategoria(Integer id) {
        findById(id);
        repository.deleteById(id);

    }

    private Categoria findById(Integer categoriaId) {
            return repository.findById(categoriaId)
                    .orElseThrow(()->
                            new ObjectNotFoundException("Objeto não encontrado! Id: "+categoriaId
                                    +" Tipo: "+ Categoria.class.getName()));
        }
}
