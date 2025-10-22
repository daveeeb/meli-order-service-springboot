package org.example.meliorderservicespringboot.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.meliorderservicespringboot.models.Custumer;
import org.example.meliorderservicespringboot.repository.CustumerRepository;
import org.example.meliorderservicespringboot.service.CustumerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustumerService {

    private final CustumerRepository custumerRepository;

    @Override
    public Custumer save(Custumer customer) {
        return custumerRepository.save(customer);
    }

    @Override
    public List<Custumer> findAll() {
        return custumerRepository.findAll();
    }

    @Override
    public Custumer findById(Long id) {
        return custumerRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        custumerRepository.deleteById(id);
    }
}
