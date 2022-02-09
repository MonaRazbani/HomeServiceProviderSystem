package ir.maktab.data.models.entities;

import ir.maktab.data.models.enums.TransactionStatus;
import ir.maktab.data.models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(columnDefinition = "BINARY(16)")
    private UUID identificationCode;

    @CreationTimestamp
    private Date creationDate ;

    @OneToOne
    private Order order;

    private double price ;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
