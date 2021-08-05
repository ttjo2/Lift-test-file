import greenfoot.World;
import greenfoot.Actor;

import java.util.Random;

public class Person extends Actor
{
    private static final Random randomizer = Building.getRandomizer();
    
    private static final int ST_ON_FLOOR = 0;
    private static final int ST_IN_LIFT = 1;
    private static final int WAITING = 0;

    private Building building;
    private Floor floor;
    private int targetFloor;
    private int status;
    
    public Person()
    {
        this(null, null);
    }

    public Person(Floor floor, Building building)
    {
        setImage("person.gif");
        this.floor = floor;
        this.building = building;
        status = ST_ON_FLOOR;
    }

    public void act()
    {
        if(status == ST_ON_FLOOR && randomizer.nextInt(100) > 95) {
            targetFloor = pickRandomFloor(floor.getFloorNumber(), building);
            if(goingUp()) {
                floor.pressButton(Button.UP);
            }
            else {
                floor.pressButton(Button.DOWN);
            }
            
            status = WAITING;
        }
    }
    
    public int getTargetFloor()
    {
        return targetFloor;
    }

    /**
     * Return whether or not we want to go up (otherwiese we want to go down).
     */
    private boolean goingUp()
    {
        return targetFloor > floor.getFloorNumber();
    }

    /**
     * Choose a random floor number (but not the one we are currently on).
     */
    private int pickRandomFloor(int currentFloor, Building building)
    {
        int floor;
        do {
            floor = building.getRandomFloor();
        } while (floor == currentFloor);
        return floor;
    }
    
    public void setFloor(Floor floor)
    {
        this.floor = floor;
    }
}