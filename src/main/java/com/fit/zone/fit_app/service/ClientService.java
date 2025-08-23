package com.fit.zone.fit_app.service;

import com.fit.zone.fit_app.model.Client;
import com.fit.zone.fit_app.repository.IClientRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@Service
public class ClientService implements IClientService{

    @Autowired
    private IClientRepository iClientRepository;

    @Override
    public List<Client> search() {
        return iClientRepository.findAll();
    }

    @Override
    public Client searchClient(Integer id) {
        //left verbose
        //Client client1 = iClientRepository.findById(id).orElse(null);

        //more verbose
        Optional<Client> client2 = iClientRepository.findById(id);
        if (client2.isEmpty()) {
            return null;
        }
        Client client = client2.get();
        return client;
    }

    @Override
    public Client saveClient(Client client) {

        return iClientRepository.save(client);

    }

    @Override
    public void delete(Integer id) {
        iClientRepository.deleteById(id);

    }
}
