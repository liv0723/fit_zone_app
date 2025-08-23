package com.fit.zone.fit_app;

import com.fit.zone.fit_app.model.Client;
import com.fit.zone.fit_app.service.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.lang.Override;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class FitAppApplication implements CommandLineRunner {

	@Autowired
	private IClientService iClientService;

	private static Logger logger;
	private static Scanner scanner;


	public static void main(String[] args) {
		logger = LoggerFactory.getLogger(FitAppApplication.class);
		scanner = new Scanner(System.in);


		logger.info("\n *************** Initialing **************");
		SpringApplication.run(FitAppApplication.class, args);
		logger.info("\n *************** ending **************");
	}

	@Override
	public void run(String ...args) {
		initializing();
	}

	public void initializing() {
		var option = 0;
		var out = false;

		while (!out) {
			option = showMenu();
			if (option > 0 && option <= 6) out = executeOption(option);
			else {
				logger.info("The option was bad");
			}
		}
	}

	public int showMenu() {
		logger.info("""
				\n*************** Fit Zone App **************
				1: List clients
				2: Search Client
				3: Insert Client
				4: Update Client
				5: Delete Client
				6: Close the app
				Insert the option:\s""");
		var option = Integer.parseInt(scanner.nextLine());
		return option;
	}

	public boolean executeOption(int option) {
		switch (option) {
			case 1 -> {
				listClients();
			    return false;
			}
			case 2 -> {
				searchClient();
				return false;
			}
			case 3 -> {
				saveClient();
				return false;
			}
			case 4 -> {
				updateClient();
				return false;
			}
			case 5 -> {
				deleteClient();
				return false;
			}
		}
		return true;
	}

	public void listClients() {
		List<Client> clients = iClientService.search();
		clients.forEach(client -> logger.info(client.toString()));
	}

	public void searchClient() {
		logger.info("Intro the id:\s");
		var id = Integer.parseInt(scanner.nextLine());
		var client = iClientService.searchClient(id);
		if (client != null) logger.info("The client is:\s" + iClientService.searchClient(id));
		else logger.info("The client no exist");
	}

	public void saveClient() {
		logger.info("Intro the name:\s");
		var name = scanner.nextLine();
		logger.info("Intro the lastname:\s");
		var lastname = scanner.nextLine();
		logger.info("Intro the membership:\s");
		var membership = Integer.parseInt(scanner.nextLine());
		Client client = new Client();
		client.setName(name);
		client.setLastname(lastname);
		client.setMembreship(membership);
		logger.info("Client saved\s" + iClientService.saveClient(client).toString());
	}

	public void updateClient() {
		logger.info("Intro the id:\s");
		var id = Integer.parseInt(scanner.nextLine());
		logger.info("Intro the name:\s");
		var name = scanner.nextLine();
		logger.info("Intro the lastname:\s");
		var lastname = scanner.nextLine();
		logger.info("Intro the membership:\s");
		var membership = Integer.parseInt(scanner.nextLine());
		Client client = iClientService.searchClient(id);

		if (client != null) {
			client.setName(name);
			client.setLastname(lastname);
			client.setMembreship(membership);
			logger.info("Client update\s" + iClientService.saveClient(client).toString());
		}
	}

	public void deleteClient() {
		logger.info("Intro the if of Client:\s");
		var id = Integer.parseInt(scanner.nextLine());

		Client client = iClientService.searchClient(id);

		if (client != null) {
			iClientService.delete(id);
			logger.info("Client deleted:\s" + client.toString());
		} else {
			logger.info("Client not founded");
		}

	}

}
