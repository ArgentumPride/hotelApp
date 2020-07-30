package ua.pride.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pride.entity.Room;
import ua.pride.entity.User;
import ua.pride.exception.RoomNotFoundException;
import ua.pride.exception.UserNotFoundException;
import ua.pride.repository.RoomRepository;
import ua.pride.repository.UserRepository;
import ua.pride.service.RoomService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static ua.pride.entity.Room.createNewRoom;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Float ROOM_PRICE = 4200F;

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Room> getEmptyRooms() {
        return roomRepository.findRoomsByOccupierIsNull();
    }

    @Override
    public ResponseEntity<HttpStatus> addRoom() {
        roomRepository.save(createNewRoom(ROOM_PRICE));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<HttpStatus> occupy(Long roomId, Long userId) {
        Optional<User> occupier = userRepository.findById(userId);
        if (occupier.isPresent()) {
            Optional<Room> room = roomRepository.findById(roomId);
            if (room.isPresent()) {
                room.get().setOccupier(occupier.get());
                room.get().setOccupationDate(Date.valueOf(
                        LocalDate.now().plus(1, ChronoUnit.MONTHS))
                );
            } else throw new RoomNotFoundException();
        } else throw new UserNotFoundException();
        return ResponseEntity.ok().build();
    }

    @Override
    public List<Room> getUsersWhichNeedToPay() {
        return roomRepository.findRoomsWhichHaveToPay();
    }

    @Override
    public List<Room> getFullMonth() {
        return roomRepository.findFullMonth();
    }
}
