package com.ucaproject.ucaattendancesystem.service;

import com.ucaproject.ucaattendancesystem.dto.RoomNoDto;
import com.ucaproject.ucaattendancesystem.entity.RoomNo;

import java.util.List;

public interface RoomNoService {
    RoomNoDto createRoomNo(RoomNoDto roomNoDto);
    List<RoomNoDto> getAllRoomNo();
    RoomNoDto getRoomNoById(Long id);
    RoomNoDto updateRoomNo(RoomNoDto roomNoDto, Long id);
    void deleteRoomNo(Long id);
}
