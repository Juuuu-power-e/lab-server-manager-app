package com.labadmin.repository;

import com.labadmin.domain.server.LabServer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabServerRepository extends JpaRepository<LabServer, Long> {
    Optional<LabServer> findByName(String name);
}
