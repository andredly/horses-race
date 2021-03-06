package com.charniauski.training.horsesrace.web.controller;

import com.charniauski.training.horsesrace.datamodel.AbstractModel;
import com.charniauski.training.horsesrace.services.GenericService;
import com.charniauski.training.horsesrace.services.exception.NoSuchEntityException;
import com.charniauski.training.horsesrace.web.converter.GenericConverter;
import com.charniauski.training.horsesrace.web.security.CustomSecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


public abstract class AbstractController<T extends AbstractModel, D> {

    @SuppressWarnings("unchecked")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<D>> getAll(HttpServletRequest request) {
        String language = request.getHeader("Language");
        List<T> all = getGenericService().getAll();
        return new ResponseEntity<>(getConverter().toListDTO(all,language), HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @GetMapping(value = "/{id}")
    public ResponseEntity<D> getById(
            @PathVariable Long id, HttpServletRequest request) {
        String language = request.getHeader("Language");
        T entity = (T) getGenericService().get(id);
        checkNull(entity, id);
        return new ResponseEntity<>(getConverter().toDTO(entity,language), HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOOKMAKER')")
    @PostMapping(produces = "application/json")
    public ResponseEntity<D> create(
            @RequestBody @Valid D dto) {
        getGenericService().save(getConverter().toEntity(dto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOOKMAKER')")
    @PostMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<D>> createAll(
            @RequestBody @Valid List<D> dtos) {
        getGenericService().saveAll(getConverter().toListEntity(dtos));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BOOKMAKER')")
    @PostMapping(value = "/{id}")
    public ResponseEntity<Void> update(
            @RequestBody D dto,
            @PathVariable Long id) {
        T entity = getConverter().toEntity(dto);
        entity.setId(id);
        getGenericService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getGenericService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    protected abstract GenericConverter<T, D> getConverter();

    protected abstract GenericService getGenericService();

    void checkNull(Object checkObject, Object... arg) {
        if (checkObject == null) {
            throw new NoSuchEntityException("Do not found entity for arg: " + Arrays.toString(arg));
        }
    }

    boolean isNotAuthorization(String login)  {
        UserDetails loggedUserDetails = CustomSecurityContextHolder.getLoggedUserDetails();
        if (loggedUserDetails==null){return true;}
        for (GrantedAuthority grantedAuthority : loggedUserDetails.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                if (!loggedUserDetails.getUsername().equals(login)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

}
