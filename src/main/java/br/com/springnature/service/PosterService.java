package br.com.springnature.service;


import br.com.springnature.dto.PosterDto;
import br.com.springnature.exception.PosterNotFoundException;
import br.com.springnature.exception.PosterTitleUniqueException;
import br.com.springnature.model.Poster;
import br.com.springnature.repository.PosterRepository;
import br.com.springnature.specification.queryfilter.PosterQueryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PosterService {

    private final PosterRepository posterRepository;

    @Transactional
    public Poster savePoster(PosterDto posterDto){
        try {
            Poster poster = new Poster();

            BeanUtils.copyProperties(posterDto, poster);

            return posterRepository.save(poster);
        } catch (DataIntegrityViolationException e) {
            throw new PosterTitleUniqueException(String.format("Title= {%s} already exists!", posterDto.getTitle()));
        }

    }

    @Transactional(readOnly = true)
    public List<Poster> findAll(Pageable pageable, PosterQueryFilter filter){
        return posterRepository
                .findAll(filter.toSpecification(), pageable)
                .stream()
                .toList();
    }


    @Transactional(readOnly = true)
    public List<Poster> findAllPosters(){
        return posterRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Poster findById(Long id){
        return posterRepository
                .findById(id)
                .orElseThrow(() -> new PosterNotFoundException(String.format("Poster {id=%d} not found!", id)));
    }

}
