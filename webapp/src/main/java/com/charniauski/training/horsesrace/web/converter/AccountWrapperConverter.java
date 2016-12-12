package com.charniauski.training.horsesrace.web.converter;

import com.charniauski.training.horsesrace.services.wrapper.AccountWrapper;
import com.charniauski.training.horsesrace.web.dto.wrapper.AccountWrapperDTO;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by ivc4 on 25.11.2016.
 */
@Component
public class AccountWrapperConverter implements GenericConverter<AccountWrapper, AccountWrapperDTO> {

    @Inject
    private AccountConverter accountConverter;

    @Inject
    private BetConverter betConverter;

    @Override
    public AccountWrapper toEntity(AccountWrapperDTO dto) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AccountWrapperDTO toDTO(AccountWrapper entity) {
        return toDTO(entity, null);
    }

    @Override
    public AccountWrapperDTO toDTO(AccountWrapper entity, String language) {
        AccountWrapperDTO accountWrapperDTO = new AccountWrapperDTO();
        accountWrapperDTO.setAccount(accountConverter.toDTO(entity.getAccount(), language));
        accountWrapperDTO.setBets(betConverter.toListDTO(entity.getBets(), language));
        return accountWrapperDTO;
    }
}
