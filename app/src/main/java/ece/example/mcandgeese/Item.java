package ece.example.mcandgeese;

import ece.example.mcandgeese.R;

import java.io.Serializable;

/*
    Class for defining attributes of item object that we will be storing in inventory
 */
public class Item implements Serializable {
    private static final long serialVersionUID = 4750425879800330764L;

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
    public static Item getWatCard(){
        String name = "WatCard Balance";
        int quantity = 25;
        int imageId = R.drawable.watcard;
        String description = "A WatCard belonging to a University of Waterloo alumni - " +
                "Goose A. There is still watcard meal plan balance in here, perhaps you" +
                "can use it by visiting the plaza and exchanging points for energy. Other" +
                "monsters may drop more balance which will be added to this card";
        return new Item(name, quantity, imageId, description);
    }

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

    public static Item getAmoeba(){
        String name = "Amoeba";
        int quantity = 1;
        int imageId = R.drawable.amoeba;
        String description = "A small amoeba cell from the huge Arriba the Amoeba monster." +
                "This creature is the mascot for the science society and not gonna lie " +
                "lowkey scares me a little bit - its appearance reminds me of the scream mask";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getLionTooth(){
        String name = "Lion Tooth";
        int quantity = 1;
        int imageId = R.drawable.lion_tooth;
        String description = "A tooth from the legendary Athletics mascot King Warrior. " +
                "Rumor has it if you cheer loud enough for the waterloo team, King " +
                "Warrior will make sure our team wins - go out and try it!";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getWoodPiece(){
        String name = "Wood Piece";
        int quantity = 1;
        int imageId = R.drawable.wood_piece;
        String description = "A processed wooden piece from the Mathematics Society's " +
                "mascot, natural log. Ba-dum-ch, it is actually a pretty good pun I " +
                "will give them that - this item will give you good luck on math questions";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getPinkBracelet(){
        String name = "Bracelet";
        int quantity = 1;
        int imageId = R.drawable.pink_bracelet;
        String description = "A beautiful pink bracelet made from the threads of the " +
                "mascot of the Faculty of Mathematics, the huge pink tie that you might" +
                "see on the side of a building on campus. For some math exams you also " +
                "need to have your calculator verified by the pink tie to use it, who knew.";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getBronzePiece(){
        String name = "Bronze Piece";
        int quantity = 1;
        int imageId = R.drawable.bronze_piece;
        String description = "A bronze piece obtained by defeating the mascot for the Faculty " +
                "of Arts, the boar. I hope you rubbed the nose of the boar before the battle " +
                "since it is known to give good luck!";
        return new Item(name, quantity, imageId, description);
    }

    // Food items - three food items (5, 10, 25) for each restaurant
    public static Item getPaninoMapo(){
        String name = "Mapo Tofu";
        int quantity = 5;
        int imageId = R.drawable.panino_mapo;
        String description = "Mapo Tofu";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getPaninoDumpling(){
        String name = "Dumpling";
        int quantity = 10;
        int imageId = R.drawable.panino_dumpling;
        String description = "Dumpling";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getPaninoChicken(){
        String name = "Spicy Chicken";
        int quantity = 25;
        int imageId = R.drawable.panino_chicken;
        String description = "Spicy Chicken";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getCampusSingle(){
        String name = "Single Slice";
        int quantity = 5;
        int imageId = R.drawable.campus_singleslice;
        String description = "Single Slice";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getCampusBigKahuna(){
        String name = "Big Kahuna";
        int quantity = 10;
        int imageId = R.drawable.campus_bigkahuna;
        String description = "Big Kahuna";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getCampusBigKahunaSauce(){
        String name = "Kahuna Extra Sauce";
        int quantity = 25;
        int imageId = R.drawable.campus_sauce;
        String description = "Kahuna Extra Sauce";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getLazeezSingle(){
        String name = "Chicken & Beef Single Line";
        int quantity = 5;
        int imageId = R.drawable.lazeez_one;
        String description = "Chicken & Beef Single Line";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getLazeezTriple(){
        String name = "Chicken & Beef Triple Line";
        int quantity = 10;
        int imageId = R.drawable.lazeez_three;
        String description = "Chicken & Beef Triple Line";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getLazeezExtra(){
        String name = "Chicken & Beef Extra Spicy";
        int quantity = 25;
        int imageId = R.drawable.lazeez_extra;
        String description = "Chicken & Beef Extra Spicy";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getSweetMango(){
        String name = "Mango Bubble Tea";
        int quantity = 5;
        int imageId = R.drawable.sweet_mango;
        String description = "Mango Bubble Tea";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getSweetCaramelt(){
        String name = "Caramelt my <3";
        int quantity = 10;
        int imageId = R.drawable.sweet_caramelt;
        String description = "Caramelt my <3";
        return new Item(name, quantity, imageId, description);
    }

    public static Item getSweetGrilled(){
        String name = "Grilled Cheese";
        int quantity = 25;
        int imageId = R.drawable.sweet_grilled;
        String description = "Grilled Cheese";
        return new Item(name, quantity, imageId, description);
    }
}
