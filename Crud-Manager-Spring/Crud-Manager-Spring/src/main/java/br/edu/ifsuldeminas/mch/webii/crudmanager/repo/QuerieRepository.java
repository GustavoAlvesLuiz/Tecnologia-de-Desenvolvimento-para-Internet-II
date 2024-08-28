package br.edu.ifsuldeminas.mch.webii.crudmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Querie;

@Repository
public interface QuerieRepository extends JpaRepository<Querie, Integer> {

}
