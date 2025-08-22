package com.fit.zone.fit_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fit.zone.fit_app.model.Client;

public interface IClientRepository extends JpaRepository<Client, Integer> {
}
