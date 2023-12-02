package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Models.AllTripViewTable;
import Models.Booking;
import Models.Traveller;
import Models.Trip;
import Models.User;
import Models.dbhandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BookTripController implements Initializable {

    private Traveller traveller = TouristDashBoardController.traveller;
    private Trip trip;
    private double discount = 0;

    @FXML
    private Label tripPriceLabel;

    @FXML
    private TextField amountTextField;

    @FXML
    private Label loyaltyPointsLabel;

    @FXML
    private Label discountEligibilityLabel;

    @FXML
    private Button discountButton;

    // You can add more fields and methods as needed

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
                
        traveller.fetchLoyaltyProgram();
        loyaltyPointsLabel.setText("" + traveller.getLoyaltyPoints());
        if(!traveller.discountEligbility()){
            discountButton.setVisible(false);
            discountEligibilityLabel.setText("Discount not Applicable");
        }
    
    }
        // Initialize the controller, if needed

       

    public void setTripData(Trip trip){
        this.trip = trip;
        tripPriceLabel.setText("Amount to be Paid " + trip.getPrice());


    }

    @FXML
    private void handlePay(ActionEvent event){
        if (amountTextField.getText().isEmpty()){
            AlertController.showAlert("Error", "Enter Amount");
            return;
        }

        double amountToPay = trip.getPrice() - discount;

        String amountText = amountTextField.getText();
        double amount = Double.parseDouble(amountText);

        if (amount > amountToPay){
            AlertController.showAlert("Error", "Amount is greater than the amount to be Paid. ARE YOU REALLY THAT RICH?");
            return;
        } else if (amount < (amountToPay*0.5)){
             AlertController.showAlert("Error", "Pay Ateast 50% of the trip amount to confirm the booking spot");
            return;
        }

        Booking newBooking = new Booking(traveller.getEmail(), LocalDate.now(), trip.getTrip_ID(), discount);
        newBooking.makePayment(amount);
        newBooking.addToDB();

        traveller.addPoints(5);

        AlertController.showConfirmation("Succesful", "Trip as been booked Successfully");
        NavBarController NBC = new NavBarController();

        NBC.Navigate(event, NavigationLink.touristDashboard);
        
    }

    @FXML
    private void handleDiscount(ActionEvent event){
        discountButton.setVisible(false);

        discount = trip.getPrice()*(0.2);
        discountEligibilityLabel.setText("Discount Applied " + discount);
        tripPriceLabel.setText("Amount to be Paid " + (trip.getPrice()-discount));

        traveller.redeemPoints(30);
        traveller.fetchLoyaltyProgram();
        loyaltyPointsLabel.setText("" + traveller.getLoyaltyPoints());
    }

    NavBarController NBC = new NavBarController();

     @FXML
    private void handleBack(ActionEvent event) {
        // Handle the back button action here
        System.out.println("Back button clicked");
        NBC.Navigate(event, NavigationLink.touristViewTrips);
        // You can navigate to the previous page or perform any other action
    }

   

    // You can add more methods to handle actions or perform specific logic
}
