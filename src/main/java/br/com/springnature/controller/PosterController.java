package br.com.springnature.controller;

import br.com.springnature.dto.PosterDto;
import br.com.springnature.mapper.PosterMapper;
import br.com.springnature.model.Poster;
import br.com.springnature.service.PosterService;
import br.com.springnature.specification.queryfilter.PosterQueryFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/poster")
public class PosterController {

    private final PosterService posterService;

    @GetMapping
    public ResponseEntity<List<Poster>> findAll(){

        return ResponseEntity
               .status(HttpStatus.OK)
                .body(posterService.findAllPosters());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Poster>> search(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, PosterQueryFilter query){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(posterService.findAll(pageable, query));
    }

    @PostMapping
    public ResponseEntity<Poster> save(@Valid @RequestBody PosterDto poster){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(posterService.savePoster(poster));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poster> findById(@PathVariable("id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(posterService.findById(id));
    }


}
