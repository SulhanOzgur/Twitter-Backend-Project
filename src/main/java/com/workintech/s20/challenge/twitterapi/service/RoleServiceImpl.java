package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.RoleResponseDto;
import com.workintech.s20.challenge.twitterapi.dto.UserResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Role;
import com.workintech.s20.challenge.twitterapi.exception.RoleNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.RoleMapper;
import com.workintech.s20.challenge.twitterapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<RoleResponseDto> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleResponseDto findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id +" id'li role bulunamadı."));
    }

    @Override
    public RoleResponseDto save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleResponseDto replaceOrCreate(Long id, Role role) {
        Optional<Role> optionalRole = roleRepository.findById(id);

        if (optionalRole.isPresent()) {
            role.setId(id);
            return roleRepository.save(role);
        }
        return roleRepository.save(role);
    }

    @Override
    public RoleResponseDto update(Long id, Role role) {
        Role roleToUpdate = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id + " id'li role bulunamadı."));

        if (role.getCode() != null) {
            roleToUpdate.setCode(role.getCode());
        }
        if (role.getDescription() != null) {
            roleToUpdate.setDescription(role.getDescription());
        }

        return roleRepository.save(roleToUpdate);
    }


    @Override
    public void delete(Long id) {
        Role role = findById(id);
        roleRepository.delete(role);
    }

    /* ******************************************************************************************* */

    @Override
    public List<UserResponseDto> getUsersByRoleId(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId + " id'li role bulunamadı."));
        return role.getUsers();
    }

}
