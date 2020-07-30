package ua.pride.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal roomSalary;
    private Float roomPrice;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "room")
    private User occupier;
    private Date occupationDate;
    private Date exitDate;

    public Room(Float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public static Room createNewRoom(Float roomPrice) {
        return new Room(roomPrice);
    }
}
