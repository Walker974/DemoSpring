package com.example.demo.mapper;

import com.example.demo.dto.ContributorDto;
import com.example.demo.entities.Contributors;

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

}
