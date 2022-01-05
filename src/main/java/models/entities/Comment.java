package models.entities;

import lombok.Data;

import javax.persistence.*;
import javax.swing.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(nullable = false)
    private int rate ;
    private String comment ;
    @OneToOne
    private Instruction instruction ;
}
