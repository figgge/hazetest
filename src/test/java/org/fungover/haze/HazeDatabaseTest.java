package org.fungover.haze;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HazeDatabaseTest {

	HazeDatabase testDatabase = new HazeDatabase();

	@Test
	void testSetNxReturnZeroWhenExistingKeyAreUsedWithDifferentValue() {
		testDatabase.setNX("1", "Hej");
		assertThat(testDatabase.setNX("1", "Då")).isEqualTo(":0\r\n");
	}

	@Test
	void testSetNxReturnOneWhenKeyDontExist() {
		assertThat(testDatabase.setNX("2", "Då")).isEqualTo(":1\r\n");
	}

    @Test
    void testSettingOneKeyInDatabaseMakesExistsFunctionReturnOneInstanceOfKeyExistingInTheDatabase() {
        testDatabase.setNX("name", "saher");

        assertThat(testDatabase.exists("name")).isEqualTo(":1\r\n");
    }

    @Test
    void testSettingTwoKeysInDatabaseMakesExistsFunctionReturnOneInstanceOfKeyExistingInTheDatabase() {
        testDatabase.setNX("name", "saher");
        testDatabase.setNX("1", "Hej");

        assertThat(testDatabase.exists("name")).isEqualTo(":1\r\n");
    }

    @Test
    void testAskingExistsFunctionForNumerousKeysWhereOneKeyHasTwoOccurrencesAndTheOtherKeyHasOneOccurrenceShouldReturnThree() {
        testDatabase.setNX("name", "saher");
        testDatabase.setNX("1", "Hej");

        assertThat(testDatabase.exists("name", "1", "name", "2")).isEqualTo(":3\r\n");
    }
}
