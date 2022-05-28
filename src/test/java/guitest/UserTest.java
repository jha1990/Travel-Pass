package guitest;

import fileio.FileOperations;
import gui.Journey;
import gui.Station;
import gui.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class UserTest {

    @Test
    public void createUser(){
        User usr = new User("sa","Adult","sam","sam@email.com");

        Assertions.assertEquals("sa",usr.getId());
        Assertions.assertEquals("sam@email.com",usr.getEmail());
        Assertions.assertEquals("Adult", usr.getType());
        Assertions.assertEquals("sam",usr.getName());
        Assertions.assertEquals(0.0,usr.getCredit());
    }

    @Test
    public void fileTest(){
        Map<String, User> map= FileOperations.readOperation();
        if(map!=null){
            Assertions.assertNotNull(map);
            User usr = map.get("lc");
            Assertions.assertEquals("lc",usr.getId());
            Assertions.assertEquals("Senior", usr.getType());
        }else{
            Assertions.assertNull(map);
        }
    }
    @Test
    public void journeyTest(){
        Journey journey = new Journey();
        journey.setCost(2);
        journey.setDay("Monday");
        journey.setStartStation(new Station("Richmond",1));

        Assertions.assertNull(journey.getEndStation());
        Assertions.assertEquals("Richmond", journey.getStartStation().getName());


    }



}
