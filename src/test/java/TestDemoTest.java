import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestDemoTest {
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if (!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}

	static Stream<Arguments> argumentsForAddPositive() {
		// @formatter:off
//		This works as well. Had a hard time figuring out the arguments import, and found this way to work.
//		Then I wanted to get it to work the way the instructions say, and got it working below.
		
//		return Stream.of(
//				Arguments.of(2, 4, 6, false), 
//				Arguments.of(1, 3, 4, false),
//				Arguments.of(-1, 2, 5, true),
//				Arguments.of(0, 1, 1, true),
//				Arguments.of()
//		);

		return Stream.of(
				arguments(1, 2, 3, false),
				arguments(2, 4, 6, false),
				arguments(1, 3, 4, false),
				arguments(-1, 2, 5, true),
				arguments(2, -2, 0, true),
				arguments(0, 1, 1, true),
				arguments(1, 0, 1, true)
		);
				
		// @formatter:on
	}

}
