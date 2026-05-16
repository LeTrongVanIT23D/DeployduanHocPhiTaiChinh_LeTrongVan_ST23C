package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "name", length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @Column(name = "floor_floornumber")
    private Integer floorFloorNumber; // Explicitly map to integer or map to Floor entity. Schema says floor_floornumber INT.

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "type", length = 50)
    private String type; // lý thuyết, lab, phòng máy

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "has_projector")
    private Boolean hasProjector;

    @Column(name = "has_air_conditioner")
    private Boolean hasAirConditioner;

    @Column(name = "has_computer")
    private Boolean hasComputer;

    @Column(name = "description", length = 255)
    private String description;

    public Room() {}

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Building getBuilding() { return building; }
    public void setBuilding(Building building) { this.building = building; }

    public Integer getFloorFloorNumber() { return floorFloorNumber; }
    public void setFloorFloorNumber(Integer floorFloorNumber) { this.floorFloorNumber = floorFloorNumber; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getHasProjector() { return hasProjector; }
    public void setHasProjector(Boolean hasProjector) { this.hasProjector = hasProjector; }

    public Boolean getHasAirConditioner() { return hasAirConditioner; }
    public void setHasAirConditioner(Boolean hasAirConditioner) { this.hasAirConditioner = hasAirConditioner; }

    public Boolean getHasComputer() { return hasComputer; }
    public void setHasComputer(Boolean hasComputer) { this.hasComputer = hasComputer; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
