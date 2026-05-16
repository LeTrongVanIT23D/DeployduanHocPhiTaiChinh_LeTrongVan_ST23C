package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "buildings")
public class Building extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code", length = 100, unique = true)
    private String code;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "total_floors")
    private Integer totalFloors;

    @Column(name = "building_type", length = 10)
    private String buildingType;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "note", length = 255)
    private String note;

    public Building() {}

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getTotalFloors() { return totalFloors; }
    public void setTotalFloors(Integer totalFloors) { this.totalFloors = totalFloors; }

    public String getBuildingType() { return buildingType; }
    public void setBuildingType(String buildingType) { this.buildingType = buildingType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
