package org.howard.edu.lsp.midterm.question2;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // Correct output as required
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

        // Trigger an exception
        try {
            AreaCalculator.area(-5); // invalid input
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

	}

}

/*
Overloading allows us to use the same method name (area) for different shapes,
which improves readability and keeps related functionality grouped together.
Using separate names like circleArea() or rectangleArea() would work, but method
overloading is cleaner and easier to understand for users of the class.
*/