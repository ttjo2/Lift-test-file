import greenfoot.World;
import greenfoot.Actor;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Floor extends Actor
{
    private static final Random random = Building.getRandomizer();

    private int floorNumber;
    private Button button;
    
    private List people;  // the people currently waiting on this floor
    
    public Floor()
    {
        this(0);
    }

    public Floor(int floorNumber)
    {
        this.floorNumber = floorNumber;
        people = new ArrayList();
    }
    
    public void addedToWorld(World world)
    {
        button = new Button();
        world.addObject(button, getX()+78, getY());
    }
    
    /**
     * Do the regular simulation action. For a floor, that is: produce a new
     * person every now and then.
     */
    public void act()
    {

    }
    
    public void addPerson(Person p)
    {
        p.setFloor(this);
        getWorld().addObject(p,58 + getX() - (people.size() * 15),getY() + 8);
        people.add(p);
    }
    
    /**
     * Return this floor's number.
     */
    public int getFloorNumber()
    {
        return floorNumber;
    }
    
    public void liftArrived(int direction, Lift lift)
    {
        // should move people in the lift to the floor
    }
    
    public void liftLeaving(int direction, Lift lift)
    {
        clearButton(direction);
        // we just wipe out all the people. Instead, they
        // should move into the list and go on from there.
        getWorld().removeObjects(people);
        people.clear();
    }

    /**
     * Press a button to call a lift to this floor.
     */
    public void pressButton(int direction)
    {
        button.press(direction);
    }

    /**
     * Press a button to call a lift to this floor.
     */
    public void clearButton(int direction)
    {
        button.clear(direction);
    }

}