package ua.pride.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pride.service.RoomService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RoomService roomService;

    public AdminController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/addRoom")
    public ResponseEntity<HttpStatus> addRoom() {
        return roomService.addRoom();
    }
}
