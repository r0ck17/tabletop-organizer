package by.javaguru.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
@Audited
public class UserRole extends BaseEntity<Integer> {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private UserRoleType role;

    public UserRole(int i, UserRoleType role) {
        super(i);
        this.role = role;
    }
}
