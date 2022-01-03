package models.entities.roles;

import javax.persistence.Column;
import javax.persistence.Lob;

public class Expert extends User{
    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;
    private int rate ;
}
