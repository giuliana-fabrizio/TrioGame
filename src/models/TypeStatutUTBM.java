package models;

public enum TypeStatutUTBM {
    TYPE_1(1,"Entretien UTBM", "/assets/Type_1.png", "Bleu"),
    TYPE_2(2,"Stage TC1", "/assets/Type_2.png", "Vert"),
    TYPE_3(3,"Stage TC2", "/assets/Type_3.png", "Rouge"),
    TYPE_4(4,"Semestre césure", "/assets/Type_4.png", "Orange"),
    TYPE_5(5,"Filière", "/assets/Type_5.png", "Violet"),
    TYPE_6(6,"CrunchLab", "/assets/Type_6.png", "Jaune"),
    TYPE_7(7,"ST50 + CDD/CDI", "/assets/Type_7.png", "Cyan"),
    TYPE_8(8,"ST50", "/assets/Type_8.png", "Magenta"),
    TYPE_9(9,"ST40", "/assets/Type_9.png", "Rose"),
    TYPE_10(10,"Branche", "/assets/Type_10.png", "Turquoise"),
    TYPE_11(11,"TC2", "/assets/Type_11.png", "Marron"),
    TYPE_12(12,"TC1", "/assets/Type_12.png", "Gris");

    private final int number;
    private final String name;
    private final String imagePath;
    private final String color;

    TypeStatutUTBM(int numero, String name, String imagePath, String color) {
        this.number = numero;
        this.name = name;
        this.imagePath = imagePath;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getColor() {
        return color;
    }
}
