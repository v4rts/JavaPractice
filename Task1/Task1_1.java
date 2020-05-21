public class Task1_1{
	public static void main(String[] args) {
		int arg1 = Integer.parseInt(args[0]);
		int arg2 = Integer.parseInt(args[1]);
		System.out.println("Остаток от деления "+arg1+" на "+arg2+" = "+ remainder(arg1,arg2));
	}
	
	public static int remainder(int arg1, int arg2) {
		return arg1 % arg2;
	}
}