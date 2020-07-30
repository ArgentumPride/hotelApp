package ua.pride.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.pride.entity.Room;

import java.util.List;

public interface RoomService {

    List<Room> getEmptyRooms();

    ResponseEntity<HttpStatus> addRoom();

    ResponseEntity<HttpStatus> occupy(Long roomId, Long userId);

    List<Room> getUsersWhichNeedToPay();

    List<Room> getFullMonth();
}
