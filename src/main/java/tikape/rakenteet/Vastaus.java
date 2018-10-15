package tikape.rakenteet;

public class Vastaus {
    private Integer id;
    private Kysymys kysymys;
    private String vastausteksti;
    private Boolean oikein;

    public Vastaus(Integer id, Kysymys kysymys, String vastausteksti, Boolean oikein) {
        this.id = id;
        this.kysymys = kysymys;
        this.vastausteksti = vastausteksti;
        this.oikein = oikein;
    }
    
    public Kysymys getKysymys() {
        return this.kysymys;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getVastausteksti() {
        return this.vastausteksti;
    }
    
    public Boolean getOikein() {
        return this.oikein;
    }

}
