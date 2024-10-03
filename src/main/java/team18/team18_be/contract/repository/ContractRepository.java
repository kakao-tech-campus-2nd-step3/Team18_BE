package team18.team18_be.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.contract.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {

}
