package activities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1
{
    static ArrayList<String> list;
    @BeforeAll
    public void tryAll(){
        System.out.println("this is Before All");
}
    @BeforeEach
    public void setUp(){
        list = new ArrayList<String>();
        list.add("alpha");
        list.add("beta");

    }
    @Test
    public void insertTest(){
    assertEquals(1,list.size(),"Wrong size");
    list.add(2,"Charlie");
    assertEquals(3,list.size(), "Wormg Size");

    //Asserting Individual elements
        assertEquals("beta",list.get(1), "incorrect Element");
    }
    @Test
    public void replaceTest(){
       // Relacing element at index 1 in list
        list.set(1,"Charlie");
        //Asserting the size of list
        assertEquals(2,list.size(), "Wrong Size");

        //Asserting Individual Elemnt
        assertEquals("alpha",list.get(0), "Wrong Elemnts");
    }
}
