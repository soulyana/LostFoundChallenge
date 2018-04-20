package me.soulyana.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 3)
    private String type;

    @NotNull
    @Size(min=2)
    private String name;

    @NotNull
    @Size(min = 5)
    private String description;

    //Pet Status: lost == true; found == false
    private boolean found;

    private String image;

    public Pet() {
        //each Pet is automatically lost in database
        found = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getImage() {
        if(image==null) {
            image = "/Images/lost.jpg";
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
