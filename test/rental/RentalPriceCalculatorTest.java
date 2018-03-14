package rental;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RentalPriceCalculatorTest {
	private RentalPriceCalculator rentalPriceCalculator;

	@Before
	public void beforeEachTest() {
		this.rentalPriceCalculator = new RentalPriceCalculator();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotOldEnoughToDrive() {
		rentalPriceCalculator.price(2, 1, 1, true, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotOldEnoughToDriveEdgeCase() {
		rentalPriceCalculator.price(17, 1, 1, true, true);
	}

	@Test
	public void testOldEnoughToDriveRightCarClassGotLicence() {
		double price = rentalPriceCalculator.price(18, 1, 1, true, true);
		assertEquals(18 * 1.3 + 15, price, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOldEnoughToDriveRightCarClassNoLicence() {
		rentalPriceCalculator.price(18, 0, 1, true, true);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testOldEnoughToDriveWrongCarClassGotLicence() {
		rentalPriceCalculator.price(18, 1, 2, true, true);
	}

	@Test
	public void testAgeTwentyTwoGotLicenceCausedAccidentLicenceOneYear() {
		double price = rentalPriceCalculator.price(22, 1, 3, true, true);
		assertEquals(22 * 1.3 + 15, price, 1);
	}

	@Test
	public void testAgeTwentyTwoGotLicenceNotCausedAccidentLicenceOneYear() {
		double price = rentalPriceCalculator.price(22, 1, 3, false, true);
		assertEquals(22 * 1.3, price, 1);
	}


	@Test
	public void testAgeThirtyGotLicenceNotCausedAccidentLicenceThreeYears() {
		double price = rentalPriceCalculator.price(30, 3, 3, false, true);
		assertEquals(30, price, 1);
	}

	@Test
	public void testAgeThirtyGotLicenceCausedAccidentLicenceThreeYears() {
		double price = rentalPriceCalculator.price(30, 3, 3, true, true);
		assertEquals(30, price, 1);
	}

	@Test
	public void testAgeTwentyFiveGotLicenceNotCausedAccidentLicenceThreeYearsClassFourSeasonTrue() {
		double price = rentalPriceCalculator.price(25, 3, 4, false, true);
		assertEquals(25 * 2, price, 1);
	}

	@Test
	public void testAgeTwentyFiveGotLicenceCausedAccidentLicenceThreeYearsClassFourSeasonTrue() {
		double price = rentalPriceCalculator.price(25, 3, 4, true, true);
		assertEquals(25 * 2 + 15, price, 1);
	}

	@Test
	public void testAgeTwentyFiveGotLicenceCausedAccidentLicenceThreeYearsClassFourSeasonFalse() {
		double price = rentalPriceCalculator.price(25, 3, 4, true, false);
		assertEquals(25 + 15, price, 1);
	}


}
