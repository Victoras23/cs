import javax.tools.DocumentationTool.Location;

public class Playfair implements Cipher {

    String key;
    String[] graph = new String[5];
    char letter = ' ';

    Playfair(String key) {
        this.key = key.toUpperCase();
        this.graph = makeGraph();
    }

    String[] makeGraph() {
        String[] toBeReturned = new String[5];
        for (int i = 0; i < 5; i++) {
            toBeReturned[i] = "";
        }
        int line = 0;
        for (int i = 0; i < this.key.length(); i++) {
            if (i >= 5) {
                line = i / 5;
            }
            toBeReturned[line] += this.key.charAt(i);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            if (this.key.indexOf((char) i) == -1 && i != 'J') {
                toBeReturned[line] += (char) i;
            }
            if (toBeReturned[line].length() == 5) {
                line += 1;
            }
        }
        return toBeReturned;
    }

    String makeEncryption(char letter1, char letter2) {
        String toBeReturned = "";
        if (letter1 == 'J')
            letter1 = 'I';
        if (letter2 == 'J')
            letter2 = 'I';
        int LocationL1 = 0, place1 = 0;
        int LocationL2 = 0, place2 = 0;
        for (int i = 0; i < 5; i++) {
            if (this.graph[i].indexOf(letter1) != -1) {
                LocationL1 = i;
                place1 = this.graph[i].indexOf(letter1);
            }
            if (this.graph[i].indexOf(letter2) != -1) {
                LocationL2 = i;
                place2 = this.graph[i].indexOf(letter2);
            }
        }
        if (LocationL1 == LocationL2) {
            if (place1 + 1 == place2) {
                toBeReturned += this.graph[LocationL1].charAt(place2);
                place2++;
                if (place2 == 5)
                    place2 = 0;
                toBeReturned += this.graph[LocationL1].charAt(place2);
            } else if (place1 == place2 + 1) {
                toBeReturned += this.graph[LocationL1].charAt(place1);
                place1++;
                if (place1 == 5)
                    place2 = 0;
                toBeReturned += this.graph[LocationL1].charAt(place1);
            } else {
                if (LocationL1 == 4)
                    LocationL1 = 0;
                else
                    LocationL1++;
                if (LocationL2 == 4)
                    LocationL2 = 0;
                else
                    LocationL2++;
                toBeReturned += this.graph[LocationL1].charAt(place1);
                toBeReturned += this.graph[LocationL2].charAt(place2);
            }
        } else {
            if (LocationL1 == 4)
                LocationL1 = 0;
            else
                LocationL1++;
            if (LocationL2 == 4)
                LocationL2 = 0;
            else
                LocationL2++;
            toBeReturned += this.graph[LocationL1].charAt(place1);
            toBeReturned += this.graph[LocationL2].charAt(place2);
        }
        return toBeReturned;
    }

    String makeDevisionE(String message) {
        String messageEncrypted = "";
        for (int i = 0; i < message.length(); i++) {
            if (i != message.length() - 1) {
                if (message.charAt(i) != message.charAt(i + 1)) {
                    messageEncrypted += makeEncryption(message.charAt(i), message.charAt(i + 1));
                    i++;
                } else {
                    messageEncrypted += makeEncryption(message.charAt(i), this.letter);
                }
            } else {
                messageEncrypted += makeEncryption(message.charAt(i), this.letter);
            }
        }
        return messageEncrypted;
    }

    String makeDecryption(char letter1, char letter2) {
        String toBeReturned = "";
        int LocationL1 = 0, place1 = 0;
        int LocationL2 = 0, place2 = 0;
        for (int i = 0; i < 5; i++) {
            if (this.graph[i].indexOf(letter1) != -1) {
                LocationL1 = i;
                place1 = this.graph[i].indexOf(letter1);
            }
            if (this.graph[i].indexOf(letter2) != -1) {
                LocationL2 = i;
                place2 = this.graph[i].indexOf(letter2);
            }
        }
        if (LocationL1 == LocationL2) {
            if ((place1 + 1) % 5 == place2) {
                toBeReturned += this.graph[LocationL1].charAt(place1 - 1);
                toBeReturned += this.graph[LocationL1].charAt(place1);
            } else if (place1 == (place2 + 1) % 5) {
                toBeReturned += this.graph[LocationL1].charAt(place2 - 1);
                toBeReturned += this.graph[LocationL1].charAt(place2);
            } else {
                if (LocationL1 == 0)
                    LocationL1 = 4;
                else
                    LocationL1--;
                if (LocationL2 == 0)
                    LocationL2 = 4;
                else
                    LocationL2--;
                toBeReturned += this.graph[LocationL1].charAt(place1);
                toBeReturned += this.graph[LocationL2].charAt(place2);
            }
        } else {
            if (LocationL1 == 0)
                LocationL1 = 4;
            else
                LocationL1--;
            if (LocationL2 == 0)
                LocationL2 = 4;
            else
                LocationL2--;
            toBeReturned += this.graph[LocationL1].charAt(place1);
            toBeReturned += this.graph[LocationL2].charAt(place2);
        }
        return toBeReturned;
    }

    String makeDevisionD(String message) {
        String messageDecrypted = "";
        for (int i = 0; i < message.length(); i += 2) {
            messageDecrypted += makeDecryption(message.charAt(i), message.charAt(i + 1));
        }
        String replace = "";
        replace += this.letter;
        messageDecrypted = messageDecrypted.replaceAll(replace, "");
        return messageDecrypted;
    }

    @Override
    public String encrypt(String message) {
        message = message.toUpperCase();
        for (int i = 'A'; i <= 'Z'; i++) {
            if (message.indexOf(i) == -1) {
                this.letter = (char) i;
                break;
            }
        }
        if (this.letter == ' ') {
            this.letter = 'X';
        }

        String replace = "";
        replace += this.letter;

        return this.makeDevisionE(message.replaceAll(" ", replace));
    }

    @Override
    public String Decrypt(String message) {
        return this.makeDevisionD(message);
    }

}
