package br.com.springnature.repository;

import br.com.springnature.model.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends JpaRepository<Poster, Long>, JpaSpecificationExecutor<Poster> {
}