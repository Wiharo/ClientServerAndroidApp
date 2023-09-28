package it.mirea.kursovaya;

public class festToDataBase {
    public String user_id, fest_name, fest_description, fest_date, fest_location;

    public festToDataBase(){

    }

    public festToDataBase(String user_id, String fest_name, String fest_description, String fest_date,
                          String fest_location) {
        this.user_id = user_id;
        this.fest_name = fest_name;
        this.fest_description = fest_description;
        this.fest_date = fest_date;
        this.fest_location = fest_location;
    }
}
