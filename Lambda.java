import java.util.Arrays;
import java.util.List;

public class Lambda {

	static Runnable r = new Runnable() {
		@Override
		public void run() {
			System.out.println("Thread com classe interna!");
		}
	};

	static Runnable r2 = () -> System.out.println("\nThread com função lambda");

	public static void main(String[] args) {
		new Thread(r).start();
		
		new Thread(r2).start();

		new Thread(() -> System.out.println("\nhello world!")).start();

		System.out.println("\nImprime todos os elementos da lista");

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		for (Integer n : list) {
			System.out.println(n);
		}

		System.out.println("\nImprime todos os elementos da lista usando lambda");
		List<Integer> listL = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		listL.forEach(n ->  System.out.println(n));
		listL.forEach(n -> {if(n%2==0) {System.out.println(n);}});
		listL.forEach(n -> System.out.println(n*n));
		

	}

}
