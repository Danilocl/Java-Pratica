import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SamplePreprocessor {

	private final SampleNormalizer normalizer;
	private static final BigDecimal THIRTY = BigDecimal.valueOf(30);

	/**
	 * You've received a Stream<BigDecimal> that represents a set of laboratory
	 * samples. Before the input data is passed on for further processing, you are
	 * required to perform some pre((-))processing on it. What you must do is:
	 * 
	 * • Eliminate all null and negative values (noises).
	 * 
	 * • Divide the data into triple((-))groups of three samples and retain only
	 * complete triples.
	 * 
	 * • Eliminate all triples whose average is higher than 30.
	 * 
	 * • Join all the triples again.
	 * 
	 * • Use SampleNormalizer to normalize the values.
	 * 
	 * Unfortunately, due to poor design it returns Optional<Optional<BigDecimal>>
	 * and is a bit buggy: it may return nulls (empty optionals), which you must
	 * eliminate. SampleNormalizer 's normalize method accepts a single BigDecimal.
	 * • Return a Stream<BigDecimal> . You mustn't return null.
	 * 
	 * @param normalizer
	 */

	SamplePreprocessor(SampleNormalizer normalizer) {
		this.normalizer = normalizer;
	}

	Stream<BigDecimal> preprocess(Stream<BigDecimal> input) {
		// Eliminate all null and negative values (noises).
		List<BigDecimal> inputList = input.filter(value -> value != null && value.compareTo(BigDecimal.ZERO) > 0)
				.collect(Collectors.toList());

		List<BigDecimal> result = new ArrayList<>();

		/**
		 * Divide the data into triple((-))groups of three samples and retain only
		 * complete triples.
		 */
		for (int i = 0; i < inputList.size(); i += 3) {
		    List<BigDecimal> triple = new ArrayList<>();
		    
		    for (int j = i; j < i + 3 && j < inputList.size(); j++) {
		        triple.add(inputList.get(j));
		    }

			if (triple.size() == 3) {
				BigDecimal average = calculateAverage(triple);
				//Eliminate all triples whose average is higher than 30.
				if (average.compareTo(THIRTY) <= 0) {
					for (BigDecimal tripleValue : triple) {
						//Join all the triples again
						//Use SampleNormalizer to normalize the values
						Optional<BigDecimal> normalized = normalizer.normalize(tripleValue).map(Optional::of)
								.orElse(Optional.empty());

						if (normalized.isPresent()) {
							result.add(normalized.get());
						}
					}
				}
			}
		}

		return result.stream();
	}

	@SuppressWarnings("deprecation")
	private BigDecimal calculateAverage(List<BigDecimal> list) {
		BigDecimal sum = BigDecimal.ZERO;
		for (BigDecimal value : list) {
			sum = sum.add(value);
		}
		return sum.divide(BigDecimal.valueOf(list.size()), 2, BigDecimal.ROUND_HALF_UP);
	}

	public static void main(String[] args) {
		SamplePreprocessor preprocessor = new SamplePreprocessor(new SampleNormalizer());

		Stream<BigDecimal> input = Stream.of(BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(30),
				BigDecimal.valueOf(40), BigDecimal.valueOf(50), BigDecimal.valueOf(60), BigDecimal.valueOf(70),
				BigDecimal.valueOf(80), BigDecimal.valueOf(90));

		Stream<BigDecimal> result = preprocessor.preprocess(input);

		result.forEach(System.out::println);
	}
}
