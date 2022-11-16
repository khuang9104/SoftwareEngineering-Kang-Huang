package Backgammon_Sprint12;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DiceTest {

	@Test
	public void testDice() {
		Dice dice = new Dice();
		assertTrue(dice instanceof Dice);
	}

	@Test
	public void testRollDice() {
		Dice dice = new Dice();
		//Map to store the distribution of different numbers.
		Map<Integer, Integer> dist = new HashMap<>();
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < 1000; i++) {
			int number = dice.rollDice();
			dist.put(number, dist.getOrDefault(number, 0)+1);
			
			if(max < number) {
				max = number;
			}
			
			if(min > number) {
				min = number;
			}
		}
		
		double distributionFor_2 = ((double) dist.get(2) / 1000)*100;
		assertEquals(1, min);
		assertTrue(distributionFor_2 > 10 && distributionFor_2 < 20);
	}

}
