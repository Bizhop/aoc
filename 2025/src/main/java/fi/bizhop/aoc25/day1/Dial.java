package fi.bizhop.aoc25.day1;

public class Dial {
    public static final int DIAL_VALUE_MIN = 0;
    public static final int DIAL_VALUE_MAX = 99;
    public static final int DIAL_VALUE_OVERTURN_ADJUSTMENT = DIAL_VALUE_MAX - DIAL_VALUE_MIN + 1;

    int value;

    Dial(int value) {
        this.value = value;
    }

    //returns the dial value
    int turnLeft(int steps) {
        if(steps < 0) throw new RuntimeException("Only positive values allowed in steps");
        this.value = this.value - steps;
        while (this.value < DIAL_VALUE_MIN) {
            this.value += DIAL_VALUE_OVERTURN_ADJUSTMENT;
        }
        return this.value;
    }

    //returns the times the dial hit min value
    int turnLeftPart2(int steps) {
        if(steps < 0) throw new RuntimeException("Only positive values allowed in steps");
        int numberOfHits = 0;
        int startValue = this.value;
        int targetValue = this.value - steps;
        while (targetValue < DIAL_VALUE_MIN) {
            targetValue += DIAL_VALUE_OVERTURN_ADJUSTMENT;
            numberOfHits++;
        }
        this.value = targetValue;
        if(startValue == DIAL_VALUE_MIN) {
            numberOfHits--;
        }
        if(this.value == DIAL_VALUE_MIN) {
            numberOfHits++;
        }
        return numberOfHits;
    }

    //returns the dial value
    int turnRight(int steps) {
        if(steps < 0) throw new RuntimeException("Only positive values allowed in steps");
        this.value = this.value + steps;
        while(this.value > DIAL_VALUE_MAX) {
            this.value -= DIAL_VALUE_OVERTURN_ADJUSTMENT;
        }
        return value;
    }

    //returns the times the dial hit min value
    int turnRightPart2(int steps) {
        if(steps < 0) throw new RuntimeException("Only positive values allowed in steps");
        int numberOfHits = 0;
        this.value = this.value + steps;
        while(this.value > DIAL_VALUE_MAX) {
            this.value -= DIAL_VALUE_OVERTURN_ADJUSTMENT;
            numberOfHits++;
        }
        return numberOfHits;
    }
}
