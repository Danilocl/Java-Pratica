
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class SamplePreprocessorTest {

	@Test
	public void testPreprocessWithValidData() {
		SampleNormalizer normalizer = new SampleNormalizer();
		SamplePreprocessor preprocessor = new SamplePreprocessor(normalizer);

		Stream<BigDecimal> input = Stream.of(BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(30),
				BigDecimal.valueOf(40), BigDecimal.valueOf(50), BigDecimal.valueOf(60), BigDecimal.valueOf(70),
				BigDecimal.valueOf(80), BigDecimal.valueOf(90));

		List<BigDecimal> result = preprocessor.preprocess(input).collect(Collectors.toList());

		assertEquals(3, result.size());
		assertTrue(result.contains(BigDecimal.valueOf(10)));
		assertTrue(result.contains(BigDecimal.valueOf(20)));
		assertTrue(result.contains(BigDecimal.valueOf(30)));		
	}

	@Test
	public void testPreprocessWithNullAndNegativeValues() {
		SampleNormalizer normalizer = new SampleNormalizer();
		SamplePreprocessor preprocessor = new SamplePreprocessor(normalizer);

		Stream<BigDecimal> input = Stream.of(BigDecimal.valueOf(-10), BigDecimal.valueOf(20), null,
				BigDecimal.valueOf(30), null, BigDecimal.valueOf(40));

		List<BigDecimal> result = preprocessor.preprocess(input).collect(Collectors.toList());

		assertEquals(3, result.size());
		assertTrue(result.contains(BigDecimal.valueOf(20))); 
		assertTrue(result.contains(BigDecimal.valueOf(30))); 
		assertTrue(result.contains(BigDecimal.valueOf(40))); 
	}

	@Test
	public void testPreprocessWithInvalidAverage() {
		SampleNormalizer normalizer = new SampleNormalizer();
		SamplePreprocessor preprocessor = new SamplePreprocessor(normalizer);

		Stream<BigDecimal> input = Stream.of(BigDecimal.valueOf(10), BigDecimal.valueOf(40), BigDecimal.valueOf(50),
				BigDecimal.valueOf(60), BigDecimal.valueOf(70), BigDecimal.valueOf(80), BigDecimal.valueOf(90),
				BigDecimal.valueOf(100), BigDecimal.valueOf(110));

		List<BigDecimal> result = preprocessor.preprocess(input).collect(Collectors.toList());

		assertTrue(result.isEmpty());
	}
}
