package pl.rownicki.roombooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rownicki.roombooking.model.Room;
import pl.rownicki.roombooking.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
       return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findRoom(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(roomService.findRoomById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room newRoom = roomService.addRoom(room);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        Room newRoom = roomService.updateRoom(room);
        return new ResponseEntity<>(newRoom, HttpStatus.OK);
    }



}
