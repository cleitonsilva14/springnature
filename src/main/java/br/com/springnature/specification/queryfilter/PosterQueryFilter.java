package br.com.springnature.specification.queryfilter;

import br.com.springnature.model.Poster;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import static br.com.springnature.specification.PosterSpecification.titleContains;

@Data
public class PosterQueryFilter {

    private String title;

    public Specification<Poster> toSpecification(){
        return titleContains(title);
    }

}
