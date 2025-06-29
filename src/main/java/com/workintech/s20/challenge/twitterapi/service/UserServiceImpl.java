package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.TweetResponseDto;
import com.workintech.s20.challenge.twitterapi.dto.UserRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.UserResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Role;
import com.workintech.s20.challenge.twitterapi.entity.User;
import com.workintech.s20.challenge.twitterapi.exception.RoleNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.UserNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.TweetMapper;
import com.workintech.s20.challenge.twitterapi.mapper.UserMapper;
import com.workintech.s20.challenge.twitterapi.repository.RoleRepository;
import com.workintech.s20.challenge.twitterapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private TweetMapper tweetMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, TweetMapper tweetMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.tweetMapper = tweetMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id + "id'li user bulunamadı."));
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        Role role = roleRepository.findById(userRequestDto.roleId())
                .orElseThrow(() -> new RoleNotFoundException(userRequestDto.roleId() + "id'li role bulunamadı."));
        User user = userMapper.toEntity(userRequestDto);
        user.setRole(role);
        return userMapper.toResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto replaceOrCreate(Long id, UserRequestDto userRequestDto) {
        Role role = roleRepository.findById(userRequestDto.roleId())
                .orElseThrow(() -> new RoleNotFoundException(userRequestDto.roleId() + "id'li role bulunamadı."));

        User user = userMapper.toEntity(userRequestDto);
        user.setId(id);
        user.setRole(role);
        return userMapper.toResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id + " id'li user bulunamadı."));

        if (userRequestDto.firstName() != null) {
            userToUpdate.setFirstName(userRequestDto.firstName());
        }
        if (userRequestDto.lastName() != null) {
            userToUpdate.setLastName(userRequestDto.lastName());
        }
        if (userRequestDto.userName() != null) {
            userToUpdate.setUserName(userRequestDto.userName());
        }
        if (userRequestDto.password() != null) {
            userToUpdate.setPassword(userRequestDto.password());
        }
        if (userRequestDto.email() != null) {
            userToUpdate.setEmail(userRequestDto.email());
        }
        if (userRequestDto.roleId() != null) {
            Role role = roleRepository.findById(userRequestDto.roleId())
                    .orElseThrow(() -> new RoleNotFoundException(userRequestDto.roleId() + "id'li role bulunamadı."));
            userToUpdate.setRole(role);
        }

        return userMapper.toResponseDto(userRepository.save(userToUpdate));
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    /** Transactional ? */
    public List<TweetResponseDto> getTweetsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId + " id'li user bulunamadı."));
        return user.getTweets()
                .stream()
                .map(tweetMapper::toResponseDto)
                .toList();
    }
}
