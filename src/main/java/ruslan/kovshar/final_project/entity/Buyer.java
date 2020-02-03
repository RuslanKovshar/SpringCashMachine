package ruslan.kovshar.final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static ruslan.kovshar.final_project.textcontants.TablesConstants.BUYER_INFO;

/**
 * Buyer entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table/*(name = BUYER_INFO)*/
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOnCard;

    private String cardNumber;
}
