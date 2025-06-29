package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.RoleRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RoleResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(RoleRequestDto roleRequestDtoDto) {
       Role role = new Role();
       role.setCode(roleRequestDtoDto.code());

       return role;
    }


    public RoleResponseDto toResponseDto(Role role) {
        return new RoleResponseDto(
                role.getId(),
                role.getCode(),
                role.getDescription()
        );
    }

}
