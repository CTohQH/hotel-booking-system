package my.edu.utar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
@RunWith(JUnitParamsRunner.class)
public class BookingTest {
    
    private Room room;
    private WaitingList waitingList;
    private Printer printer;
    private Booking booking;    
    @Before
    public void setUp() {
        room = mock(Room.class);
        waitingList = mock(WaitingList.class);
        printer = mock(Printer.class);
        booking = new Booking(room, waitingList, printer);
        
        booking.setVipRoomsBooked(0);
        booking.setNormalRoomsBooked(0);
        booking.setNonRoomsBooked(0);
    }
    
    @Test
    @Parameters(method = "bookingTestData")
    public void testSetBooking(
        User user, boolean roomAvailable, boolean addToWaitingList, String expectedRoomType) {
        when(room.checkRoom(anyString())).thenReturn(roomAvailable);

        booking.setBooking(user);

        if (user.getReward()==true && user.getMemberType().equals("normal")) {
            expectedRoomType = "VIP";
        if (!roomAvailable) {
        	
            } else if (user.getMemberType().equals("VIP") && expectedRoomType.equals("VIP")) {
                expectedRoomType = "Deluxe";
            }
        }

        
        assertEquals(expectedRoomType, booking.getRoomType(user));
    }

    private Object[] bookingTestData() {
        return new Object[] {
            new Object[] { new User("John Doe", "VIP", false), true, true, "VIP" },
            new Object[] { new User("Jane Doe", "normal", false), true, true, "Deluxe" },
            new Object[] { new User("Alice", "non", false), true, true, "Standard" },
            new Object[] { new User("Bob", "normal", true), true, true, "Deluxe" },
            
        };
    }


    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "invalidUserTestData")
    public void testSetBooking_WithInvalidUser(User user) {
        // Mock room behavior
        Room mockRoom = mock(Room.class);
        Printer mockPrinter = mock(Printer.class);
        Booking booking = new Booking(mockRoom, null, mockPrinter);

        // Call the method that is expected to throw IllegalArgumentException
        booking.setBooking(user);
    }


    private Object[] invalidUserTestData() {
        return new Object[] {
            new Object[] { new User("", "normal", false) },
            new Object[] { new User("John Doe", null, false) },
            new Object[] { new User("John Doe", "invalid", false) },
            new Object[] { null }
        };
    }
       
    // Test updating room counters
    @Test
    public void testUpdateRoomCounter() {
        booking.updateRoomCounter("VIP");
        assertEquals(1, booking.getVipRoomsBooked());
    }
    
    // Test getting room type based on membership type
    @Test
    public void testGetRoomType() {
        User vipUser = new User("Alice", "VIP", false);
        User normalUser = new User("Bob", "normal", false);
        User nonMemberUser = new User("Charlie", "non", false);
        
        when(room.checkRoom("VIP")).thenReturn(true);
        when(room.checkRoom("Deluxe")).thenReturn(true);
        when(room.checkRoom("Standard")).thenReturn(true);
        
        assertEquals("VIP", booking.getRoomType(vipUser));
        assertEquals("Deluxe", booking.getRoomType(normalUser));
        assertEquals("Standard", booking.getRoomType(nonMemberUser));
    }
    
    // Test handling maximum room bookings for each membership type
    @Test(expected = IllegalStateException.class)
    public void testMaxRoomBookingForVIP() {
        User vipUser = new User("Eva", "VIP", false);
        booking.setVipRoomsBooked(3); // Maximum rooms booked
        
        booking.getRoomType(vipUser);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testMaxRoomBookingForNormal() {
        User normalUser = new User("Frank", "normal", false);
        booking.setNormalRoomsBooked(2); // Maximum rooms booked
        
        booking.getRoomType(normalUser);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testMaxRoomBookingForNonMember() {
        User nonMemberUser = new User("George", "non", false);
        booking.setNonRoomsBooked(1); // Maximum rooms booked
        
        booking.getRoomType(nonMemberUser);
    }
  //TEST CASE FOR ADDTOWAITINGLIST
    @Test
    public void testAddToWaitingList() 
    {
        // Create a mock user list
        ArrayList<User> userList = new ArrayList<>();
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        
        when(user1.getName()).thenReturn("Adam");
        when(user2.getName()).thenReturn("Eve");
        
        when(user1.getMemberType()).thenReturn("VIP");
        when(user2.getMemberType()).thenReturn("member");
      


        userList.add(user1);
        userList.add(user2);

        // Call the method to add users to waiting list
        booking.addToWaitingList(userList);

        // Verify that addWaitingList is called for each user in the list
        verify(waitingList, times(1)).addWaitingList(user1);
        verify(waitingList, times(1)).addWaitingList(user2);
    }

    @Test
    public void testCancelBooking_UserNotFound() {
        // Arrange
        User user = new User("John", "VIP", false);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);

        // Stubbing behavior
        when(waitingList.getWaitingList("VIP")).thenReturn(userList);
        when(room.checkRoom(anyString())).thenReturn(true);

        // Act
        booking.cancelBooking(new User("Alice", "VIP", false));

        // Assert
        verify(room, never()).cancelBooking(anyString());
        verify(waitingList, never()).removeWaitingList(any(User.class));
    }

    @Test
    public void testCancelBooking_UserFound() {
        // Arrange
        User user = new User("John", "VIP", false);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);

        // Stubbing behavior
        when(waitingList.getWaitingList("VIP")).thenReturn(userList);
        when(room.checkRoom(anyString())).thenReturn(true);

        // Act
        booking.cancelBooking(user);

        // Assert
        assertEquals(0, booking.getVipRoomsBooked());
        assertEquals(0, booking.getNormalRoomsBooked());
        assertEquals(0, booking.getNonRoomsBooked());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchUser_NullUserList() {
        // Act
        booking.searchUser(null, "Alice");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchUser_NullName() {
        // Act
        booking.searchUser(new ArrayList<>(), null);
    }

    @Test
    public void testSearchUser_UserNotFound() {
        // Arrange
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("John", "VIP", false));
        userList.add(new User("Alice", "normal", false));

        // Act
        int index = booking.searchUser(userList, "Bob");

        // Assert
        assertEquals(-1, index);
    }

    @Test
    public void testSearchUser_UserFound() {
        // Arrange
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("John", "VIP", false));
        userList.add(new User("Alice", "normal", false));

        // Act
        int index = booking.searchUser(userList, "John");

        // Assert
        assertEquals(0, index);
    }
    // Test adding users to the waiting list with null user list
    @Test
    public void testAddToWaitingListWithNullList() {
        booking.addToWaitingList(null);
        
        verify(waitingList, never()).addWaitingList(any(User.class));
    }
    
    // Test adding users to the waiting list with null user
    @Test(expected = IllegalArgumentException.class)
    public void testAddToWaitingListWithNullUser() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(null);
        
        booking.addToWaitingList(userList);
    }
    
    // Test adding users to the waiting list with null user name
    @Test(expected = IllegalArgumentException.class)
    public void testAddToWaitingListWithNullUserName() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(null, "VIP", false));
        
        booking.addToWaitingList(userList);
    }
    
    // Test adding users to the waiting list with empty user name
    @Test(expected = IllegalArgumentException.class)
    public void testAddToWaitingListWithEmptyUserName() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("", "VIP", false));
        
        booking.addToWaitingList(userList);
    }
    
    // Test adding users to the waiting list with null member type
    @Test(expected = IllegalArgumentException.class)
    public void testAddToWaitingListWithNullMemberType() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("John", null, false));
        
        booking.addToWaitingList(userList);
    }
    
    // Test adding users to the waiting list with empty member type
    @Test(expected = IllegalArgumentException.class)
    public void testAddToWaitingListWithEmptyMemberType() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("John", "", false));
        
        booking.addToWaitingList(userList);
    }
}
