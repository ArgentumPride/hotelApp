package ua.pride.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pride.entity.Room;
import ua.pride.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private final RoomService roomService;

    public ManagementController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getEmptyRooms")
    public List<Room> getEmptyRooms() {
        return roomService.getEmptyRooms();
    }

    @PostMapping("/occupy")
    public ResponseEntity<HttpStatus> occupyRoom(@RequestParam Long roomId,
                                                 @RequestParam Long userId) {
        return roomService.occupy(roomId, userId);
    }

}
