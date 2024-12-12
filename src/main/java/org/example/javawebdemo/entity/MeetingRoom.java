package org.example.javawebdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoom {
    int rid;
    String room_name;
    int capacity;
    String image;
    String description;
    String location;
}
