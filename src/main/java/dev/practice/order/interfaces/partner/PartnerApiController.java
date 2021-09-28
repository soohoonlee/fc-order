package dev.practice.order.interfaces.partner;

import dev.practice.order.application.partner.PartnerFacade;
import dev.practice.order.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/api/v1/partners")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PartnerApiController {

    private final PartnerFacade partnerFacade;

    @PostMapping
    public CommonResponse registerPartner(@RequestBody @Valid PartnerDto.RegisterRequest request) {
        var command = request.toCommand();
        var partnerInfo = partnerFacade.registerPartner(command);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }
}
