package tikape;

import java.util.HashMap;
import spark.Spark;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.rakenteet.Kysymys;
import tikape.rakenteet.Vastaus;
import tikape.dao.Database;
import tikape.dao.KysymysDao;
import tikape.dao.VastausDao;

public class Main {

public static void main (String[] args) throws Exception {
    if (System.getenv("PORT") != null) {
    Spark.port(Integer.valueOf(System.getenv("PORT")));
    }
    Database database = new Database("jdbc:sqlite:pankki.db");
    KysymysDao kysymykset = new KysymysDao(database);
    VastausDao vastaukset = new VastausDao(database, kysymykset);
    Spark.staticFileLocation("/public");
    Spark.get("/", (req, res) -> {
        HashMap map = new HashMap<>();
        map.put("kysymykset", kysymykset.findAll());
        map.put("maara", kysymykset.findAll().size());
        new ModelAndView(map, "index");
        return new ModelAndView(map, "index");
    }, new ThymeleafTemplateEngine());
    
    Spark.get("/vastauksia/:id", (req,res) -> {
        HashMap map = new HashMap<>();
        Integer kysymysId = Integer.parseInt(req.params(":id"));
        map.put("kysymys", kysymykset.findOne(kysymysId));
        map.put("maara", vastaukset.kaikkiKyssarille(kysymysId).size());
        map.put("vastaukset", vastaukset.kaikkiKyssarille(kysymysId));
        map.put("oikea", vastaukset.eiOikeaaKyssarille(kysymysId));
        map.put("kaikkioikein", vastaukset.kaikkiOikeinKyssarille(kysymysId));
        return new ModelAndView(map, "kysymys");
    }, new ThymeleafTemplateEngine());
    
    Spark.post("/lisaakysymys", (req, res) -> {
        Kysymys kysymys = new Kysymys(-1, req.queryParams("kurssi"), req.queryParams("aihe"), req.queryParams("kysymysteksti"));
        kysymykset.saveOrUpdate(kysymys);
        res.redirect("/#lisaa");
    return "";
    });
    
    Spark.post("/lisaavastaus/:id", (req, res) -> {
        Integer kysymysId = Integer.parseInt(req.params(":id"));
        Boolean oikein = true;
        if (req.queryParams("oikein") == null) {
            oikein = false;
        }
        Vastaus vastaus = new Vastaus(-1, kysymykset.findOne(kysymysId), req.queryParams("vastausteksti"), oikein);
        vastaukset.saveOrUpdate(vastaus);
        res.redirect("/vastauksia/" + kysymysId);
    return "";
    });
    
    Spark.post("/poistakysymys/:id", (req, res) -> {
        Integer taskId = Integer.parseInt(req.params(":id"));
        kysymykset.delete(taskId);
        res.redirect("/#tarkastele");
    return "";
    });
    
    Spark.post("/poistavastaus/:id", (req, res) -> {
        Integer taskId = Integer.parseInt(req.params(":id"));
        Integer kysymysId = Integer.parseInt(req.queryParams("kysymysid"));
        vastaukset.delete(taskId);
        res.redirect("/vastauksia/" + kysymysId);
        return "";
    });
    
    
}
    
}
