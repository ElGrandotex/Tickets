package com.jv.Tickets.Ticket;

import com.jv.Tickets.Event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class Ticket {
    @Id
    @GeneratedValue
    Integer id;
    String code;
    Double price;
    Status status;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
