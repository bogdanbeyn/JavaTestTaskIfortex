package com.example.java_ifortex_test_task.service;

import com.example.java_ifortex_test_task.dto.UserResponseDTO;
import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.User;
import com.example.java_ifortex_test_task.mapper.UserMapper;
import com.example.java_ifortex_test_task.repository.SessionRepository;
import com.example.java_ifortex_test_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final UserMapper userMapper;

//    @GetMapping("/most-sessions")
    public UserResponseDTO getUserWithMostSessions() {
        User userWithMostSessions = userRepository.getUserWithMostSessions();
        if (userWithMostSessions == null) {
            return null;
        }
        return userMapper.toDto(userWithMostSessions);
    }

//    @GetMapping("/mobile-users")
    public List<UserResponseDTO> getUsersWithAtLeastOneMobileSession() {
        List<User> list = userRepository.getUsersWithAtLeastOneMobileSession(DeviceType.MOBILE);

        if(list.isEmpty() || list == null){
            return Collections.emptyList();
        }

        return list.stream().map(userMapper::toDto).collect(Collectors.toList());
    }
}
