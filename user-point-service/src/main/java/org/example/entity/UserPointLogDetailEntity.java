package org.example.entity;

import lombok.*;
import org.example.types.UpdateState;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "user_point_log_detail")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPointLogDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * FK (PK of the UserPointEntity)
     */
    private Long userPointId;

    private String reason;

    /**
     * how may amount was updated at the time.
     */
    private int changedBy;

    @Enumerated(EnumType.STRING)
    private UpdateState updateState;

    private LocalDateTime updatedTime;

}
