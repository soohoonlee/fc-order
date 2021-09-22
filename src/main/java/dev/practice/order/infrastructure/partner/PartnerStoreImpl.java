package dev.practice.order.infrastructure.partner;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.partner.Partner;
import dev.practice.order.domain.partner.PartnerStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class PartnerStoreImpl implements PartnerStore {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner store(Partner partner) {
        if (!StringUtils.hasLength(partner.getPartnerToken())) throw new InvalidParamException("partner.getPartnerToken()");
        if (!StringUtils.hasLength(partner.getPartnerName())) throw new InvalidParamException("partner.getPartnerToken()");
        if (!StringUtils.hasLength(partner.getBusinessNo())) throw new InvalidParamException("partner.getBusinessNo()");
        if (!StringUtils.hasLength(partner.getEmail())) throw new InvalidParamException("partner.getEmail()");
        if (partner.getStatus() == null) throw new InvalidParamException("partner.getStatus()");

        return partnerRepository.save(partner);
    }
}
