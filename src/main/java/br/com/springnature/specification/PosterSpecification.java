package br.com.springnature.specification;

import br.com.springnature.model.Poster;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class PosterSpecification {

    public static Specification<Poster> titleContains(String title){

        return ((root, query, criteriaBuilder) -> {
           if (ObjectUtils.isEmpty(title)){
               return null;
           }
           return criteriaBuilder.like(root.get("title"), "%" + title + "%");
        });
    }
}
