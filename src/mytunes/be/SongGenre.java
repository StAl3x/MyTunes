package mytunes.be;

public enum SongGenre {
    Classical("Classical"),
    Pop("Pop"),
    Jazz("Jazz"),
    Country("Country"),
    CountryPop("Country Pop"),
    Rock("Rock"),
    Soul("Soul"),
    HeavyMetal("Heavy Metal"),
    Rap("Rap"),
    HipHop("Hip Hop");

    String name;
    SongGenre(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
