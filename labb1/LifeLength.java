public class LifeLength{
	public static void main(String[] args){
		try{
			int n = Integer.parseInt(args[0]);
			switch(n){
				case 1:
					task1(Integer.parseInt(args[1]));
					break;
				case 2:
					task2(Integer.parseInt(args[1]));
					break;
				case 3:
					task3(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
					break;
				case 4:
					task4(Integer.parseInt(args[1]));
					break;
				case 6:
					task6(Integer.parseInt(args[1]));
					break;

				default:
					System.out.print("This function doesnt exist");
			}
		}
		catch(Exception err){
			System.out.print("This function doesnt exist");
		}

		}

	public static void task1(int num){
		int f1Num = f1(num);
		System.out.println(f1Num);
	}

	public static void task2(int num){
		int f1Num = f1(num);
		int f2Num = f2(num);
		int f4Num = f4(num);
		int f8Num = f8(num);
		int f16Num = f16(num);
		int f32Num = f32(num);
		System.out.println("f1="+f1Num+"\t f2="+f2Num+"\t f4="+f4Num+"\t f8="+f8Num+"\t f16="+f16Num+"\t f32="+f32Num);
	}

	public static void task3(int num, int steps){
		iterateF(num, steps);
	}

	public static void task4(int num){
		int life = iterLifeLength(num);
		System.out.print(intsToString(num, life));
	}

	public static void task6(int num){
		System.out.println("Life length: "+rek(num, 0));
	}

	public static int f1(int a0){
		if(a0 == 1){
			return a0;
		}
		else if(a0 % 2 == 0){
			return a0/2;
		}
		else{
			return a0*3 + 1;
		}
	}

	public static int f2(int a0){
		int newNumber = f1(f1(a0));
		return newNumber;
	}

	public static int f4(int a0){
		int newNumber = f2(f2(a0));
		return newNumber;
	}

	public static int f8(int a0){
		int newNumber = f4(f4(a0));
		return newNumber;
	}

	public static int f16(int a0){
		int newNumber = f8(f8(a0));
		return newNumber;
	}

	public static int f32(int a0){
		int newNumber = f16(f16(a0));
		return newNumber;
	}

	public static void iterateF(int a0, int n){
		
		for(int i = 0; i < n; i++){
			a0=f1(a0);
			int step = i + 1;
			System.out.println("Step "+step+"= "+a0);
		}
	}

	public static int iterLifeLength(int a0){
		int life = 0;
		while(a0 > 1){
			a0=f1(a0);
			life++;
		}
		return life;
	}

	public static String intsToString(int a0, int life){
		return "The life length of "+ a0 + " is " + life + ".";
	}

	public static int rek(int a0, int k){
		if(a0 == 1){
			return k;
		}
		a0 = f1(a0);
		return rek(a0, k+1);
	}
	//Ivan License

}