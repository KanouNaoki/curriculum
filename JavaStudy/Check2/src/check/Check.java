package check;

import constants.Constants;

public class Check {

	private static String firstName = "加納";
	private static String lastName = "直樹";

	private static String printName(String firstName, String lastName) {
		return firstName + lastName;
	}

	public static void main(String[] args) {
		System.out.println("printNameメソッド→" + printName(firstName, lastName));
		System.out.println();

		Pet pet = new Pet(Constants.CHECK_CLASS_JAVA, Constants.CHECK_CLASS_HOGE);
		RobotPet robotPet = new RobotPet(Constants.CHECK_CLASS_R2D2, Constants.CHECK_CLASS_LUKE);
		pet.introduce();
		robotPet.introduce();
	}
}
