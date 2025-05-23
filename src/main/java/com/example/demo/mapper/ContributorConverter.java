package com.example.demo.mapper;

import com.example.demo.dto.ContributorDto;
import com.example.demo.entities.Contributors;

import java.util.List;
import java.util.stream.Collectors;

public class ContributorConverter {
    public static ContributorDto toDto(Contributors contributor) {
        if (contributor == null) {
            return null;
        }
        return new ContributorDto(contributor.getId(), contributor.getName(), contributor.getEmail());
    }

    public static Contributors toEntity(ContributorDto dto) {
        if (dto == null) {
            return null;
        }
        Contributors contributor = new Contributors();
        contributor.setId(dto.id());
        contributor.setName(dto.name());
        contributor.setEmail(dto.email());
        return contributor;
    }

    public static List<ContributorDto> toDtoList(List<Contributors> contributors) {
        if (contributors == null || contributors.isEmpty()) {
            return List.of();
        }
        return contributors.stream()
                .map(ContributorConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<Contributors> toList(List<ContributorDto> contributors) {
        if (contributors == null || contributors.isEmpty()) {
            return List.of();
        }
        return contributors.stream()
                .map(ContributorConverter::toEntity)
                .collect(Collectors.toList());
    }

}
