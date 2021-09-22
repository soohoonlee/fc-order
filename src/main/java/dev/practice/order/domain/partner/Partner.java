package dev.practice.order.domain.partner;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.common.util.TokenGenerator;
import dev.practice.order.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "partners")
@NoArgsConstructor
@Slf4j
public class Partner extends AbstractEntity {

    public static final String PREFIX_PARTNER = "ptn_";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String partnerToken;
    private String partnerName;
    private String businessNo;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Builder
    public Partner(String partnerName, String businessNo, String email) {
        if (!StringUtils.hasLength(partnerName)) throw new InvalidParamException("empty partnerName");
        if (!StringUtils.hasLength(businessNo)) throw new InvalidParamException("empty businessNo");
        if (!StringUtils.hasLength(email)) throw new InvalidParamException("empty email");

        this.partnerToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER);
        this.partnerName = partnerName;
        this.businessNo = businessNo;
        this.email = email;
        this.status = Status.ENABLE;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"), DISABLE("비활성화");

        private final String description;
    }

    public void enable() {
        this.status = Status.ENABLE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }
}
