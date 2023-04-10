package custom.people;

public class People {
    private int age;
    private String race;
    private String name;

    public People(int age, String race, String name) {
        this.age = age;
        this.race = race;
        this.name = name;
    }

    public void yearLater() {
        age++;
        System.out.println(this.name + " age ++");
    }
    public void setRace(String race) {
        this.race = race;
        System.out.println(this.name + " became a " + race);
    }

    public void setName(String name) {
        this.name = name;
    }

    public People soitie(People b) {
        return new People(0, this.race + "-" + b.race, "untitled");
    }
}
