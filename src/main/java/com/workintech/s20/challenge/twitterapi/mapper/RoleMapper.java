package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.RoleRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RoleResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(RoleRequestDto roleRequestDtoDto) {
       Role role = new Role();
        role.setFirstName(roleRequestDtoDto.firstName());
        role.setLastName(roleRequestDtoDto.lastName());
        role.setUserName(roleRequestDtoDto.userName());
        role.setPassword(roleRequestDtoDto.password());
        role.setEmail(roleRequestDtoDto.email());
        role.setRole(roleRequestDtoDto.role());

        return role;
    }


    public RoleResponseDto toResponseDto(Role role) {
        return new RoleResponseDto(
                role.getFirstName(),
                role.getLastName(),
                role.getUserName(),
                role.getEmail(),
                role.getRole()
        );
    }

}
