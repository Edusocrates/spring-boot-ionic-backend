package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.DTO.CategoriaDTO;
import com.edusocrates.cursoMC.exception.DataIntegrityException;
import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.repository.CategoriaRepository;
import com.edusocrates.cursoMC.serivce.CategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public CategoriaDTO insertCategoria(CategoriaDTO categoriaDTO) {
        categoriaDTO.setId(null);
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        Categoria savedCategoria = repository.save(categoria);
        return new CategoriaDTO(savedCategoria);
    }

    @Override
    public CategoriaDTO updateCategoria(Integer id, CategoriaDTO categoriaDTO) {
        Categoria categoriaBase = findById(id);
        categoriaDTO.setId(id);
        BeanUtils.copyProperties(categoriaDTO,categoriaBase);//realiza a copia do objeto para atualizar
        Categoria updatedCategoria = repository.save(categoriaBase);
        return new CategoriaDTO(updatedCategoria);
    }

    @Override
    public void deleteCategoria(Integer id) {
        findById(id);
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos!!");
        }


    }

    @Override
    public List<Categoria> getAllCategorias() {
        List<Categoria> categoriaList = repository.findAll();
        return categoriaList;
    }

    public Categoria findById(Integer categoriaId) {
            return repository.findById(categoriaId)
                    .orElseThrow(()->
                            new ObjectNotFoundException("Objeto não encontrado! Id: "+categoriaId
                                    +" Tipo: "+ Categoria.class.getName()));
        }

        public Page<Categoria> findAllWithPagenation(Integer page,Integer linesPerPage,
                                                     String orderBy,String direction){
            PageRequest pageRequest =  PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
            return repository.findAll(pageRequest);
        }
}
