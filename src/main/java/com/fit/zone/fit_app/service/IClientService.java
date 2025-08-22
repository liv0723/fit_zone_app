package com.fit.zone.fit_app.service;

import java.util.List;
import com.fit.zone.fit_app.model.Client;

public interface IClientService {

    public List<Client> search();
    public Client searchClient(Integer id);
    public void save(Client client);
    public void delete(Integer id);
}
