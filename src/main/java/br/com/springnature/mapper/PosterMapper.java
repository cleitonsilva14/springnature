package br.com.springnature.mapper;


import br.com.springnature.dto.PosterDto;
import br.com.springnature.model.Poster;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PosterMapper {

    // poster -> posterDto
    public static PosterDto toDto(Poster poster){
        ModelMapper mapper = new ModelMapper();

        return mapper
                .map(poster, PosterDto.class);
    }

    // posterDto -> poster
    public static Poster toPoster(PosterDto posterDto){
        return new ModelMapper()
                .map(posterDto, Poster.class);
    }

    // Poster -> List<PostDto>
    public static List<PosterDto> toListPosterDto(List<Poster> posters){
        return posters
                .stream()
                .map(poster -> toDto(poster))
                .collect(Collectors.toList());
    }
}
