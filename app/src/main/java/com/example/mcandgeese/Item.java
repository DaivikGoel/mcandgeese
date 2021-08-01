package com.example.mcandgeese;

/*
    Class for defining attributes of item object that we will be storing in inventory
 */
public class Item {
    private String name;

    // should be at least one when an instance of the class is created
    private int quantity;
    private int imageId;
    private String description;

    public Item(String name, int quantity, int imageId, String description){
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.imageId = imageId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setImageId(int imageId){
        this.imageId = imageId;
    }

    public int getImageId(){
        return imageId;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    // predefined values for set bosses item drops
    public static Item getGooseFeather(){
        String name = "Goose Feather";
        int quantity = 1;
        int imageId = R.drawable.goose_feather;
        String description = "A legendary item obtained by defeating the almighty goose " +
                "roaming the campus - it is said that the feather has magic properties " +
                "that help students with their exams (say thank Mr.Goose to trigger this" +
                "effect";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getMetalPiece(){
        String name = "Metal Piece";
        int quantity = 1;
        int imageId = R.drawable.metal_piece;
        String description = "A piece of the almighty sixty-inch triple chrome-plated " +
                "adjustable pipe wrench known as the Tool. The tool is the only of its" +
                "kind in the world and the piece is probably worth a solid $3 - can " +
                "only be wielded by an owner of an iron ring";
        return new Item(name, quantity, imageId, description);
    }
}
