package org.example.meliorderservicespringboot.repository;

import org.example.meliorderservicespringboot.models.Custumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustumerRepository extends JpaRepository <Custumer, Long>{

}
