package rental;

public class RentalPriceCalculator {
    private double rentalPrice;
    private static final int MAX_RENTAL_PRICE = 1000;

    // driversAge - driversAge of driver
    // licenceOwnedInYears - number of full years person holds driving licenceOwnedInYears
    // carPriceClass - class of the car from 1 (smallest) to 5 (largest) that person wishes to rent
    // hasCausedAccidendInLastYear - has s/he caused any accidents within last year
    // hasParticipatedInAccidentLastYear - has s/he participated (but not caused) in any accidents within last year
    // isHighSeason - if it is high isHighSeason or not
    public double price(int driversAge,
                        int licenceOwnedInYears,
                        int carPriceClass,
                        boolean hasCausedAccidendInLastYear,
                        boolean isHighSeason) {
        rentalPrice = 0;

        checkAge(driversAge);
        checkIfCarClassIsInAccordanceToAge(driversAge, carPriceClass);
        checkIfLicenceIsOwnedForAtleastOneYear(licenceOwnedInYears);


        setRentalPriceAccordanceToAge(driversAge);
        isHighSeasonIsHighCarClassIsYoungDriver(driversAge, carPriceClass, isHighSeason);
        checkIfDriverHasHadLicenceUnder3Years(licenceOwnedInYears);
        checkIfHasCausedAccidentInLastYearAndDriverIsUnder30(hasCausedAccidendInLastYear, driversAge);
        checkMaxRentalPrice();


        return rentalPrice;

    }

    private void isHighSeasonIsHighCarClassIsYoungDriver(int driversAge, int carPriceClass, boolean isHighSeason) {
        if (carPriceClass >= 4 && driversAge <= 25 && isHighSeason) {
            rentalPrice = 2 * rentalPrice;
        }
    }

    private void checkAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Driver too young - cannot quote the price");
        }
    }

    private void checkIfCarClassIsInAccordanceToAge(int driversAge, int carPriceClass) {
        if (driversAge <= 21 && carPriceClass >= 2) {
            throw new UnsupportedOperationException("Drivers 21 y/o or less can only rent Class 1 vehicles");
        }
    }

    private void setRentalPriceAccordanceToAge(int driversAge) {
        rentalPrice = driversAge;
    }

    private void checkIfLicenceIsOwnedForAtleastOneYear(int licenceOwnedInYears) {
        if (licenceOwnedInYears < 1) {
            throw new IllegalArgumentException("Driver must hold driving licenceOwnedInYears at least for one year. Can not rent a car!");

        }
    }

    private void checkIfHasCausedAccidentInLastYearAndDriverIsUnder30(boolean hasCausedAccidendInLastYear, int driversAge) {
        if (hasCausedAccidendInLastYear && driversAge < 30) {
            rentalPrice += 15;
        }
    }

    private void checkMaxRentalPrice() {
        if (rentalPrice > MAX_RENTAL_PRICE) {
            rentalPrice = MAX_RENTAL_PRICE;
        }
    }

    private void checkIfDriverHasHadLicenceUnder3Years(int licenceOwnedInYears) {
        if (licenceOwnedInYears < 3) {
            rentalPrice = rentalPrice * 1.3;
        }

    }
}