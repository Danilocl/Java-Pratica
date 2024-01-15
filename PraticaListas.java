import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PraticaListas {

	public static void main(String[] args) throws IOException {
		List<String> list = new ArrayList<String>();

		list.add("um");
		list.add("dois");
		list.add("três");

		Stream<String> stream = list.stream();
		System.out.println(stream.toList().toString());

		Stream<Integer> numbersFromValues = Stream.of(1, 2, 3, 4, 5);

		System.out.println(numbersFromValues.toList());

	}
}
