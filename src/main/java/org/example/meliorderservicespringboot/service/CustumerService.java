package org.example.meliorderservicespringboot.service;

import org.example.meliorderservicespringboot.models.Custumer;
import java.util.List;

public interface CustumerService {
    Custumer save(Custumer custumer);
    List<Custumer> findAll();
    Custumer findById(Long id);
    void delete(Long id);
}
