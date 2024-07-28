package com.example.demo.service;

import com.example.demo.domain.Calendar;
import com.example.demo.domain.User;
import com.example.demo.dto.ResponseSleepLogDto;
import com.example.demo.dto.SleepLogDto;
import com.example.demo.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public void addSleepLog(User user, SleepLogDto sleepLogDto){
        Calendar calendar = calendarRepository.findByYearAndMonthAndDayAndUser((long)sleepLogDto.getDate().getYear(), (long)sleepLogDto.getDate().getMonthValue(), (long)sleepLogDto.getDate().getDayOfMonth(), user);
        if(calendar == null){
            calendar = Calendar.makeCalendar((long)sleepLogDto.getDate().getYear(), (long)sleepLogDto.getDate().getMonthValue(), (long)sleepLogDto.getDate().getDayOfMonth(), user);
        }
        calendar.setFromSleepLogDto(sleepLogDto);
        calendarRepository.save(calendar);
    }

    public List<ResponseSleepLogDto> viewAll(User user, LocalDateTime date){
        long year = date.getYear();
        long month = date.getMonthValue();
        return calendarRepository.findAllByYearAndMonth(year,month,user);
    }

    public ResponseSleepLogDto viewDetail(User user, LocalDateTime date){
        long year = date.getYear();
        long month = date.getMonthValue();
        long day = date.getDayOfMonth();
        return ResponseSleepLogDto.fromEntity(calendarRepository.findByYearAndMonthAndDayAndUser(year, month, day, user));
    }
}
