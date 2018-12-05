
public class Test {
	int acc_no;
	String name;
	float amount;

	void insert(int a, String n, float amt) {
		acc_no = a;
		name = n;
		amount = amt;
	}

	void deposit(float amt) {
		amount = amount + amt;
		System.out.println(amt + " deposited");
	}

	void withdraw(float amt) {
		if (amount < amt) {
			System.out.println("Insufficient Balance");
		} else {
			amount = amount - amt;
			System.out.println(amt + " withdrawn");
		}
	}

	float checkBalance() {
		System.out.println("Balance is: " + amount);
		return amount;
	}

	void display() {
		System.out.println(acc_no + " " + name + " " + amount);
	}
	
	
	public static void main(String[] args) {
		Test a1 = new Test();
		a1.insert(832345, "Ankit", 1000);
		a1.display();
		a1.checkBalance();
		a1.deposit(40000);
		a1.checkBalance();
		a1.withdraw(15000);
		float bal=a1.checkBalance();
		System.out.println(" final balance " +bal);
	}
}
