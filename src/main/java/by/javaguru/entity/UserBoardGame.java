package by.javaguru.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Table(name = "user_boardgame")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "userBoardGame")
@Audited
public class UserBoardGame extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Audited
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardgame_id")
    @Audited
    private BoardGame boardGame;

    @Column(name = "price")
    private Integer price;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
}


