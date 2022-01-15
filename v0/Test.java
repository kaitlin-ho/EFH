public class Test {

	public static void main(String[] args) {
		Player player = new Player();
		Sword sword = new Sword();
		InvisCloak invisCloak = new InvisCloak();
		KtS kts = new KtS();
		Shield shield = new Shield();
		BowArrow bowArrow = new BowArrow();
		Armor armor = new Armor();
                player.invent(sword);
                player.invent(invisCloak);
                player.invent(kts);
                player.invent(shield);
                player.invent(bowArrow);
                player.invent(armor);
		player.equip(sword);
		player.equip(invisCloak);
		player.equip(kts);
                player.equip(shield);
                player.equip(bowArrow);
                player.equip(armor);
		System.out.println(player.invToString());
		System.out.println(player.eqToString());
	}

}
