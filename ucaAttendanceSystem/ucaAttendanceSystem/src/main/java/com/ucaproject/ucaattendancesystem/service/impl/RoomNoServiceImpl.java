package com.ucaproject.ucaattendancesystem.service.impl;

import com.ucaproject.ucaattendancesystem.dto.RoomNoDto;
import com.ucaproject.ucaattendancesystem.entity.RoomNo;
import com.ucaproject.ucaattendancesystem.exceptions.RoomNoNotFoundException;
import com.ucaproject.ucaattendancesystem.repository.RoomNoRepository;
import com.ucaproject.ucaattendancesystem.service.RoomNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomNoServiceImpl implements RoomNoService {

    private RoomNoRepository roomNoRepository;

    @Autowired
    public RoomNoServiceImpl(RoomNoRepository roomNoRepository) {
        this.roomNoRepository = roomNoRepository;
    }

    @Override
    public RoomNoDto createRoomNo(RoomNoDto roomNoDto) {
        RoomNo roomNo = new RoomNo();
        roomNo.setRoomName(roomNoDto.getRoomName());
        roomNo.setIsOccupied(roomNoDto.isOccupied());
        roomNo.setSide(roomNoDto.getSide());

        RoomNo newRoomNo = roomNoRepository.save(roomNo);

        RoomNoDto roomNoResponse = new RoomNoDto();
        roomNoResponse.setId(newRoomNo.getId());
        roomNoResponse.setRoomName(newRoomNo.getRoomName());
        roomNoResponse.setOccupied(newRoomNo.getIsOccupied());
        roomNoResponse.setSide(newRoomNo.getSide());

        return roomNoResponse;
    }

    @Override
    public List<RoomNoDto> getAllRoomNo() {
        List<RoomNo> room = roomNoRepository.findAll();
        return room.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
    }

    @Override
    public RoomNoDto getRoomNoById(Long id) {
        RoomNo roomNo = roomNoRepository.findById(id).orElseThrow(() -> new RoomNoNotFoundException("Room Not Found"));
        return mapToDto(roomNo);
    }

    @Override
    public RoomNoDto updateRoomNo(RoomNoDto roomNoDto, Long id) {
        RoomNo room = roomNoRepository.findById(id).orElseThrow(() -> new RoomNoNotFoundException("Room Not Found"));

        room.setRoomName(roomNoDto.getRoomName());
        room.setIsOccupied(roomNoDto.isOccupied());
        room.setSide(roomNoDto.getSide());

        RoomNo updatedRoom = roomNoRepository.save(room);
        return mapToDto(updatedRoom);
    }

    @Override
    public void deleteRoomNo(Long id) {
        RoomNo room = roomNoRepository.findById(id).orElseThrow(() -> new RoomNoNotFoundException("Room Not Found"));
        roomNoRepository.delete(room);
    }

    private RoomNoDto mapToDto(RoomNo roomNo) {
        RoomNoDto roomNoDto = new RoomNoDto();
        roomNoDto.setId(roomNo.getId());
        roomNoDto.setRoomName(roomNo.getRoomName());
        roomNoDto.setOccupied(roomNo.getIsOccupied());
        roomNoDto.setSide(roomNo.getSide());
        return roomNoDto;
    }

    private RoomNo mapToEntity(RoomNoDto roomNoDto) {
        RoomNo roomNo = new RoomNo();
        roomNo.setRoomName(roomNoDto.getRoomName());
        roomNo.setIsOccupied(roomNoDto.isOccupied());
        roomNo.setSide(roomNoDto.getSide());
        return roomNo;
    }
}
