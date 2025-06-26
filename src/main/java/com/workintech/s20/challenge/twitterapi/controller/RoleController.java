package com.workintech.s20.challenge.twitterapi.controller;

import com.workintech.s20.challenge.twitterapi.dto.RoleRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RoleResponseDto;
import com.workintech.s20.challenge.twitterapi.dto.UserResponseDto;
import com.workintech.s20.challenge.twitterapi.service.RoleService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /* ******************************************************************************************* */

    @GetMapping
    public List<RoleResponseDto> getAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleResponseDto getById(@Positive @PathVariable Long id) {
        return roleService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDto save(@Validated @RequestBody RoleRequestDto roleRequestDto) {
        return roleService.save(roleRequestDto);
    }

    @PutMapping("/{id}")
    public RoleResponseDto replaceOrCreate(@Positive @PathVariable Long id, @Validated @RequestBody RoleRequestDto roleRequestDto) {
        return roleService.replaceOrCreate(id, roleRequestDto);
    }

    @PatchMapping("/{id}")
    public RoleResponseDto update(@Positive @PathVariable Long id, @Validated @RequestBody RoleRequestDto roleRequestDto) {
        return roleService.update(id, roleRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable Long id) {
        roleService.delete(id);
    }

    /* ******************************************************************************************* */

    @GetMapping("/{id}/users")
    public List<UserResponseDto> getUsersByRoleId(@Positive @PathVariable Long id) {
        return roleService.getUsersByRoleId(id);
    }
}
