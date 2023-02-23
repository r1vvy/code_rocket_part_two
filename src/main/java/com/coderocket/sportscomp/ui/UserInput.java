package com.coderocket.sportscomp.ui;

import com.coderocket.sportscomp.domain.holder.ChosenCompetitionHolder;
import com.coderocket.sportscomp.dto.CreateCompetitionInRequest;
import com.coderocket.sportscomp.dto.CreatePlayerInRequest;
import com.coderocket.sportscomp.dto.DeleteCompetitionInActionRequest;
import com.coderocket.sportscomp.dto.ReadCompetitionByIdInRequest;
import com.coderocket.sportscomp.utils.DateTimeUtils;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInput {

    public int getActionChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public CreatePlayerInRequest getPlayerCreationRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Surname: ");
        String surname = scanner.nextLine();

        return new CreatePlayerInRequest(name, surname);
    }

    public CreateCompetitionInRequest getCompetitionCreationRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Title: ");
        String title = scanner.nextLine();

        System.out.println("Max capacity: ");
        Integer capacity = Integer.valueOf(scanner.nextLine());

        System.out.println("Registration Opening date: ");
        String registrationOpen = scanner.nextLine();

        System.out.println("Registration Closing date: ");
        String registrationClose = scanner.nextLine();

        System.out.println("Starting date: ");
        String startDate = scanner.nextLine();

        System.out.println("Ending date: ");
        String endDate = scanner.nextLine();

        return new CreateCompetitionInRequest(
                title,
                capacity,
                DateTimeUtils.formatStringAsLocalDate(registrationOpen),
                DateTimeUtils.formatStringAsLocalDate(registrationClose),
                DateTimeUtils.formatStringAsLocalDate(startDate),
                DateTimeUtils.formatStringAsLocalDate(endDate)
        );
    }

    public ReadCompetitionByIdInRequest getReadCompetitionByIdRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID: ");
        Integer id = Integer.valueOf(scanner.nextInt());

        return new ReadCompetitionByIdInRequest(id);
    }

    public DeleteCompetitionInActionRequest getDeleteCompetitionRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure you wish to continue? [Y/N]");
        String userChoice = scanner.nextLine();
        if(userChoice.equalsIgnoreCase("Y")) {
            return new DeleteCompetitionInActionRequest(ChosenCompetitionHolder.getChosenCompetition());
        } else if(userChoice.equalsIgnoreCase("N")){
            return null;
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}
