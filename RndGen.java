public class RndGen {
    public static String randomEnemyName() {//Name generator
        int d40 = Util.diceRoller(0, 39);
        String[] enemyName = {"Moransab","Mavorgezu","Thargha","Zergta","Vresan",
        "Thild'ula","Ha","Fenu","Imilphu","Bucu","Ronba","Nexla","Doomimgash",
        "Ball","Xyal","Grorn","Raruk","Mali","Thanxus","Irath","Rend","Hevorg",
        "Kil'grorn","Thusra","Bachom","Rornushang","Rothme","Dresh",
        "Rothshandze","Reshra","Naush","Motar","Baalshu","Lavi","Phekahud",
        "Zargver","Rath","Varorsharg","Kukruul","Becain"};
        return enemyName[d40]; //Returns a random enemy name
    } //End randomEnemyName

    public static String randomAdjective() {// Adjective generator
        int d50 = Util.diceRoller(0, 49);
        String[] adjectives = {"scintillating","acceptable","barbarous","flashy",
        "noxious","laughable","normal","hurried","puzzled","oval","overrated",
        "scandalous","dark","bloody","adventurous","lethal","groovy","whole",
        "adaptable","alive","smoltering","different","lopsided","malicious",
        "troubled","knowledgeable","simplistic","brash","encouraging","dusty",
        "inexpensive","smart","incandescent","weary","workable","grandiose",
        "splendid","precious","tragic","narrow","joyous","sharp","careless",
        "flat","resonant","bumpy","solid","strong","faulty","accidental"};
        return adjectives[d50]; //Returns a random adjective inside the array
    } //End randomAdjective

    public static String randomNoun() {
        int d50 = Util.diceRoller(0, 49);
        String[] nouns = {"rise","campaign","secret","past","safety","pipe",
        "credit","rule","shelter","connection","farm","food","wealth","ratio",
        "failure","power","raw","system","square","money","sky","bear","black",
        "disease","pressure","final","weakness","data","beacon","equal",
        "penalty","keep","associate","control","elevator","company","concept",
        "disk","perception","professional","field","bone","natural","peak",
        "deep","future","extreme","night","knife","procedure"};
        return nouns[d50]; //Returns a random noun inside the array
    } //End randomNoun
    
    public static String randomTrap() {
        int d4 = Util.diceRoller(0, 3);
        String[] traps = {
        "Negative energy pillars and ethereal scythes hit you.",
        "Icy metal disks and precise ballistae shoot towards you.",
        "Forceful spears and energy-draining javelins get thrown towards you.",
        "Sonic swinging glaives and necrotic floortiles damage you." };
        return traps[d4]; //Same as other random Methods
    }//End randomTrap
}
