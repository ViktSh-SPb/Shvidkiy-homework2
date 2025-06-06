package org.viktsh;


/**
 * @author Viktor Shvidkiy
 */
public class App 
{
    public static void main( String[] args )
    {
        CustomLinkedList<String> testList = new CustomLinkedList<>();
        testList.add("hello");
        testList.add("World");
        System.out.println(testList);

    }
}
