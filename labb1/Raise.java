public class Raise{
	public static void main(String[] args){
		double productHalf = recRaiseHalf(Double.parseDouble(args[0]), Integer.parseInt(args[1]), 0);
		System.out.println(productHalf);
		double productOne = recRaiseOne(Double.parseDouble(args[0]), Integer.parseInt(args[1]), 0);
		System.out.println(productOne);
	}

	public static double recRaiseOne(double x, int k, int count){
		count++;
		if(k == 0){
			System.out.println("Ammount of recursions: "+count);
			return 1;
		}
		else{
			return x*recRaiseOne(x, k-1, count);
		}
	}

	public static double recRaiseHalf(double x, int k, int count){
		count++;
		if(k == 0){
			System.out.println("Ammount of recursions: "+count*2);
			return 1;
		}
		double powerOfHalfK = recRaiseHalf( x, k/2, count );
		if(k % 2 == 0){
			
			return powerOfHalfK * powerOfHalfK;
		}
		else{
			return x * powerOfHalfK * powerOfHalfK;
		}
	}

}