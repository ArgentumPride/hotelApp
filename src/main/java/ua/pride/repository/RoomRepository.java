package ua.pride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.pride.entity.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findRoomsByOccupierIsNull();

    @Query("select r " +
            "from Room as r " +
            "where r.exitDate = current_date ")
    List<Room> findRoomsWhichHaveToPay();

    @Query(value =
            "select r " +
            "from Room as r " +
            "where r.exitDate between current_date and interval '1month' ", nativeQuery = true)
    List<Room> findFullMonth();

}

