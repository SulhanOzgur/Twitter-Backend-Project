package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.TweetResponseDto;
import com.workintech.s20.challenge.twitterapi.dto.UserResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.User;
import com.workintech.s20.challenge.twitterapi.exception.UserNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.UserMapper;
import com.workintech.s20.challenge.twitterapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDto findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id + "id'li user bulunamadı."));
    }

    @Override
    public UserResponseDto save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserResponseDto replaceOrCreate(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        }
        return userRepository.save(user);
    }

    @Override
    public UserResponseDto update(Long id, User user) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id + " id'li user bulunamadı."));

        if (user.getFirstName() != null) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userToUpdate.setLastName(user.getLastName());
        }
        if (user.getUserName() != null) {
            userToUpdate.setUserName(user.getUserName());
        }
        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getRole() != null) {
            userToUpdate.setRole(user.getRole());
        }

        return userRepository.save(userToUpdate);
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
        return user.getTweets();
    }
}
