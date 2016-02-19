package at.mschwaer.chucknorrisBild;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mschwaer on 15.02.2016.
 */
public class ChuckNoris {
    private List<String> chuckList;

    public ChuckNoris() {
        chuckList = new ArrayList<String>();
        addChuckSpruch("Chuck Norris ist so männlich, dass seine Brusthaare Haare haben.");
        addChuckSpruch("Wenn Chuck Norris von einer Kugel getroffen wird, blutet nicht er, sondern die Kugel.");
        addChuckSpruch("Sie wollten Chuck Norris auf Mount Rushmore abbilden, aber der Granitfels war nicht hart genug für seinen Bart.");
        addChuckSpruch("Wenn Chuck Norris dividiert, dann bleibt kein Rest.");
        addChuckSpruch("Chuck Norris sucht nicht - er findet.");
        addChuckSpruch("Wer Angst vor dem Tod hat, kennt Chuck Norris nicht.");
        addChuckSpruch("Es gibt keine globale Erwärmung. Chuck Norris war kalt, also hat er die Sonne höher gedreht.");
        addChuckSpruch("Chuck Norris kennt die letzte Ziffer von Pi.");
        addChuckSpruch("Chuck Norris schläft nicht, er erhält seine Kampfkraft.");
        addChuckSpruch("Chuck Norris hat unter seinem Bart kein Kinn, sondern eine dritte Faust.");
        addChuckSpruch("Chuck Norris schaut nicht in den Spiegel, denn selbst Chuck Norris hat Angst vor Chuck Norris.");
        addChuckSpruch("Chuck Norris trinkt immer mit Strohhalm - er kann das Glas nicht greifen, weil er dazu seine Faust öffnen müsste.");
        addChuckSpruch("Chuck Norris duscht nicht. Niemals. Denn nicht einmal Schmutz wagt sich an ihn heran.");
        addChuckSpruch("Wenn Chuck Norris Liegestütze macht, stützt er sich nicht ab, er drückt die Erde nach unten.");
        addChuckSpruch("Chuck Norris ist so schnell, dass er einmal um die Welt rennen kann, um sich selbst gegen den Hinterkopf zu hauen.");
        addChuckSpruch("Chuck Norris hat einmal russisches Roulette mit einem komplett geladenen Colt gespielt - und gewonnen!");
        addChuckSpruch("Chuck Norris hat zwei Geschwindigkeiten: Laufen und Töten.");
        addChuckSpruch("Chuck Norris braucht weder Maus noch Tastatur. Er steckt einfach seinen rechten Zeigefinger in einen USB-Anschluss.");
        addChuckSpruch("Chuck Norris ist nicht Gott, denn Gott kennt Gnade.");
    }

    public String getChuckSpruch(int nr) {
        if (nr > getChuckSpruchAnz())
            return "";
        else
            return chuckList.get(nr);
    }

    public int getChuckSpruchAnz() {
        if (chuckList != null)
            return chuckList.size();
        else
            return 0;
    }
    public void addChuckSpruch(String theSpruch) {

        chuckList.add(theSpruch);
    }

    public int getChuckSpruchSize(int nr) {
        if (nr > getChuckSpruchAnz())
            return 0;
        else
            return getChuckSpruch(nr).length();
    }
}
