package com.arslan.E_voting.Backend.Mapper;

import com.arslan.E_voting.Backend.DTO.CreateElectionRequestDTO;
import com.arslan.E_voting.Backend.DTO.ElectionResponseDTO;
import com.arslan.E_voting.Backend.Entity.Election;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ElectionMapper {

    ElectionMapper INSTANCE = Mappers.getMapper(ElectionMapper.class);

    @Mapping(target = "createdByName", source = "createdBy.fullName")
    ElectionResponseDTO toDto(Election election);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true) // you'll set this manually
    @Mapping(target = "candidates", ignore = true)
    @Mapping(target = "votes", ignore = true)
    Election fromDto(CreateElectionRequestDTO dto);
}
