package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.RoleRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RoleResponseDto;
import com.workintech.s20.challenge.twitterapi.dto.UserResponseDto;

import java.util.List;

public interface RoleService {

    List<RoleResponseDto> findAll();
    RoleResponseDto findById(Long id);
    RoleResponseDto save(RoleRequestDto roleRequestDto);
    RoleResponseDto replaceOrCreate(Long id, RoleRequestDto roleRequestDto);
    RoleResponseDto update(Long id, RoleRequestDto roleRequestDto);
    void delete(Long id);

    List<UserResponseDto> getUsersByRoleId(Long roleId);

}
