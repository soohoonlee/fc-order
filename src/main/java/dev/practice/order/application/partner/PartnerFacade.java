package dev.practice.order.application.partner;

import dev.practice.order.domain.notification.NotificationService;
import dev.practice.order.domain.partner.PartnerCommand;
import dev.practice.order.domain.partner.PartnerInfo;
import dev.practice.order.domain.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartnerFacade {

    private final NotificationService notificationService;
    private final PartnerService partnerService;

    public PartnerInfo registerPartner(PartnerCommand command) {
        var partnerInfo = partnerService.registerPartner(command);
        notificationService.sendEmail(partnerInfo.getEmail(), "title", "description");
        return partnerInfo;
    }
}
