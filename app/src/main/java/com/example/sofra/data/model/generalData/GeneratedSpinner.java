package com.example.sofra.data.model.generalData;


public class GeneratedSpinner {

    private String Name;
    private int id;

      public GeneratedSpinner(int id, String name ) {
        this.id = id;
        this.Name = name;

    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
