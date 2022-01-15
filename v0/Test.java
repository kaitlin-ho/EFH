public class Test {

	public static void main(String[] args) {
		Player player = new Player();
		Sword sword = new Sword();
		InvisCloak invisCloak = new InvisCloak();
		player.equip(sword);
		player.equip(invisCloak);
		System.out.println(player.eqToString());
	}

}
