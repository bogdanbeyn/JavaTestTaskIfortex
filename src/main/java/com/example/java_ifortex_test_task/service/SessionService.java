package com.example.java_ifortex_test_task.service;

import com.example.java_ifortex_test_task.dto.SessionResponseDTO;
import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.Session;
import com.example.java_ifortex_test_task.mapper.SessionMapper;
import com.example.java_ifortex_test_task.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;



    private final SessionMapper sessionMapper;



    // Returns the first (earliest) desktop Session
    public SessionResponseDTO getFirstDesktopSession() {
        Session firstDesktopSession = sessionRepository.getFirstDesktopSession(DeviceType.DESKTOP);
        if(firstDesktopSession == null){
            return null;
        }
        return sessionMapper.toDto(firstDesktopSession);
    }

    // Returns only Sessions from Active users that were ended before 2025
    public List<SessionResponseDTO> getSessionsFromActiveUsersEndedBefore2025() {
        LocalDateTime time = LocalDateTime.of(2025, Month.JANUARY,1,0,0,0);

        List<Session> list= sessionRepository.getSessionsFromActiveUsersEndedBefore2025(time);

        if(list.isEmpty() || list == null){
            return Collections.emptyList();
        }

        return list.stream().map(sessionMapper::toDto).collect(Collectors.toList());
    }

}