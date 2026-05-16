package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "exam_rooms")
public class ExamRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "status", length = 50)
    private String status;

    public ExamRoom() {}

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
