package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Abode;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Vendee;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.AbodeMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.VendeeMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.AbodeRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.VendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("VendeeServiceImpl")
public class VendeeServiceImpl implements VendeeService {

    @Autowired
    private VendeeMapper vendeeMapper;
    @Autowired
    private AbodeMapper abodeMapper;
    @Autowired
    private VendeeRepository vendeeRepository;
    @Autowired
    private AbodeRepository abodeRepository;

    @Override
    public Vendee create(VendeeDtoRequest request) {
        // Батьківська сутність (Vendee) повинна бути збережена першою.
        // Після чого, дочірня сутність (Abode).
        // Отримуємо дані Abode з Request.
        Abode _abd =
                abodeMapper.dtoCreateToEntity(request);
        // Отримуємо дані Vendee, яку зберігли в БД.
        Vendee _vnd =
                // Дані Vendee зберігаємо в БД, але спершу без Abode.
                vendeeRepository.save(
                        // Отримуємо дані Vendee з Request.
                        vendeeMapper.dtoCreateToEntity(request)
                );
        // Додаємо дані сутності Vendee до сутності Abode.
        _abd.setVendee(_vnd);
        // Отримуємо дані сутності Abode при збереженні в БД.
        Abode abode = abodeRepository.save(_abd);
        // Додаємо дані сутності Abode до сутності Vendee.
        _vnd.setAbode(abode);
        // Повертаємо створену сутність Vendee.
        return _vnd;
    }

    @Override
    public List<Vendee> getAll() {
        return vendeeRepository.findAll();
    }

    @Override
    public List<Abode> getAbodes() {
        return abodeRepository.findAll();
    }

    @Override
    public Vendee getById(Long id) {
        return vendeeRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Vendee updateById(Long id, VendeeDtoRequest request) {
        Optional<Vendee> vendeeOptional = vendeeRepository.findById(id);
        Optional<Abode> abodeOptional = abodeRepository.findById(id);
        return (vendeeOptional.isPresent() & abodeOptional.isPresent()) ?
                vendeeRepository.saveAndFlush(
                        vendeeMapper.dtoUpdateByIdToEntity(
                                id, request,
                                vendeeOptional.get(),
                                abodeOptional.get())) : null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (vendeeRepository.findById(id).isPresent()) {
            vendeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
