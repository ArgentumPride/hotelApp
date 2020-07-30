package ua.pride.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pride.entity.Room;
import ua.pride.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/usual")
public class UsualOperations {

    private final RoomService roomService;

    public UsualOperations(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/toDeparture")
    public List<Room> departureCheck() {
        return roomService.getUsersWhichNeedToPay();
    }

    @GetMapping("/getFullMonth")
    public List<Room> fullMonth() {
        return roomService.getFullMonth();
    }
}
