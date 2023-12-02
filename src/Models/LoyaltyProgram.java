package Models;

public class LoyaltyProgram {
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LoyaltyProgram(int points) {
        this.points = points;
    }

    public void redeemPoints(int points, String email){
        this.points -= points;
        updateDB(email); 
    }

    private void updateDB(String email){
        dbhandler db = new dbhandler();
        System.out.println(points);

        db.updatePoints(email, points);
    }

    public void addPoints(int points, String email) {
        this.points += points;
        
        updateDB(email); 
    }

    public Boolean eligibleForDiscount(){
        return (points < 30 ? false : true);
    }


}
