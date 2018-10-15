## Harjoitustyö 2: Kysymyspankki

#### Sovelluksen kuvaus

Sovellus on osana Tietokantojen perusteet -kurssia suoritettu toinen harjoitustehtävä. Sovellus on kysymyspankki, joka sisältää mahdollisuuden lisätä ja poistaa kysymyksiä, lisätä ja poistaa vastausvaihtoehtoja, tarkastella kysymysten listausta ja yksittäisiä kysymyksiä.

Keskeiset toiminnallisuudet on jaettu kahteen näkymään. Etusivu sisältää ensinnäkin tehtävien listauksen. Tehtävien listaamisen yhteydessä on "poista"-nappi, jolla yksittäisen kysymyksen voi poistaa. Tehtävän nimeä klikkaamalla siirrytään erilliseen tehtäväkohtaiseen näkymään. Ensimmäisen näkymän lopussa on lisäksi lomake, jolla uuden kysymyksen voi lisätä. Tehtävän tietojen syöttämisen yhteydessä tarkistetaan, että kaikki kentät ovat täytettyjä. Ensimmäisen sivun sisällä navigoimiseksi on lisätty sisäisiä linkkejä.

Toinen näkymä sisältää yksityiskohtaisen kysymysnäkymän. Tässä on listattuna sekä kysymystä koskevat tiedot, että siihen liityvät vastausvaihtoehdot. Vastausvaihtoehdot sisältävät tiedon siitä, onko kyseessä oikea vai väärä vastaus ja mahdollisuuden vastausvaihtoehdon poistamiseen. Toisen näkymän alalaidassa on lomake kysymysvaihtoehdon lisäämiseen. Vastaustekstikenttä on pakolinen ja vaihtoehdon antamisen yhteydessä käyttäjä antaa tiedon siitä, onko vastausvaihtoehto oikea vai väärä vastaus laittamalla rastin checkboxiin.

Kysymyspankki tarkkailee seuraavia kahta tapausta ja varoittaa niistä: 1) kaikki vastausvaihtoehdot ovat oikein ja 2) yksikään vastausvaihtoehto ei ole oikein. Erilliset ilmoitukset ovat myös tilanteille, joissa ei ole lainkaan kysymyksiä pankissa (ensimmäinen näkymä) ja kun kysymykselle ei ole lainkaan vastausvaihtoehtoja (toinen näkymä).

Pankkiin on annettu valmiiksi muutamia kysymyksiä.

Kehityskohtia/ongelmia: Sovellukseen ei ole toteutettu laajempia listausvaihtoehtoja. Tehtävänannossa edellytettiin vain kysymysten listausta. Kysymysmäärän lisääntymisen seurauksena olisi kuitenkin tarpeellista lisätä mahdollisuudet tarkastella esim. vain tiettyyn kurssiin liittyviä kysymyksiä tai tiettyyn aiheeseen liittyviä kysymyksiä.

Kysymyspankin Herokuun siirretty versio löytyy osoitteesta: https://harjoitustyo-kyspankki.herokuapp.com/
