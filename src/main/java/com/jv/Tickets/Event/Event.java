package com.jv.Tickets.Event;

import com.jv.Tickets.Ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Event {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String description;
    LocalDateTime date;
    String location;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;
}
