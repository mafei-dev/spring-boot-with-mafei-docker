package org.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_point")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPointEntity {
    /**
     * the PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * the username
     */
    private String username;

    /**
     * current points amount.
     */
    private int points;
}
