package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.RoleRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RoleResponseDto;
import com.workintech.s20.challenge.twitterapi.dto.UserResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Role;
import com.workintech.s20.challenge.twitterapi.exception.RoleNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.RoleMapper;
import com.workintech.s20.challenge.twitterapi.mapper.UserMapper;
import com.workintech.s20.challenge.twitterapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;
    private UserMapper userMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, UserMapper userMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<RoleResponseDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id + " id'li role bulunamadı."));
        return roleMapper.toResponseDto(role);
    }

    @Override
    public RoleResponseDto save(RoleRequestDto roleRequestDto) {
        Role role = roleMapper.toEntity(roleRequestDto);
        return roleMapper.toResponseDto(roleRepository.save(role));
    }

    @Override
    public RoleResponseDto replaceOrCreate(Long id, RoleRequestDto roleRequestDto) {
        Role role = roleRepository.findById(id).orElse(new Role());
        role.setId(id);
        role.setCode(roleRequestDto.code());
        return roleMapper.toResponseDto(roleRepository.save(role));
    }

    @Override
    public RoleResponseDto update(Long id, RoleRequestDto roleRequestDto) {
        Role roleToUpdate = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id + " id'li role bulunamadı."));

        if (roleRequestDto.code() != null) {
            roleToUpdate.setCode(roleRequestDto.code());
        }

        return roleMapper.toResponseDto(roleRepository.save(roleToUpdate));
    }


    @Override
    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id + " id'li role bulunamadı."));
        roleRepository.delete(role);
    }

    /* ******************************************************************************************* */

    @Override
    public List<UserResponseDto> getUsersByRoleId(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId + " id'li role bulunamadı."));
        return role.getUsers()
                .stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
