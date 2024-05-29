package com.ucaproject.ucaattendancesystem.controller;

import com.ucaproject.ucaattendancesystem.dto.RoomNoDto;
import com.ucaproject.ucaattendancesystem.service.RoomNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class RoomNoController {

    private RoomNoService roomService;

    @Autowired
    public RoomNoController(RoomNoService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("room")
    public ResponseEntity<List<RoomNoDto>> getRooms() {
        return new ResponseEntity<>(roomService.getAllRoomNo(), HttpStatus.OK);
    }

    @GetMapping("room/{id}")
    public ResponseEntity<RoomNoDto> getRoomById(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.getRoomNoById(id), HttpStatus.OK);
    }

    @PostMapping("room/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomNoDto> createRoom(@RequestBody RoomNoDto roomDto) {
        return new ResponseEntity<>(roomService.createRoomNo(roomDto), HttpStatus.CREATED);
    }

    @PutMapping("room/{id}/update")
    public ResponseEntity<RoomNoDto> updateRoom(@PathVariable Long id, @RequestBody RoomNoDto roomDto) {
        RoomNoDto response = roomService.updateRoomNo(roomDto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("room/{id}/delete")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomNo(id);
        return new ResponseEntity<>("Room deleted", HttpStatus.OK);
    }

}
