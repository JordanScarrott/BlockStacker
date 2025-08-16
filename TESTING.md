# Unit Testing Guide

This guide provides instructions on how to set up and run unit tests for this project.

## Setup

To write and run unit tests, you will need the JUnit 5 testing framework. Since this project does not use a dependency management tool like Maven or Gradle, you will need to download the JUnit JAR file manually.

1.  **Create a `lib` directory:**
    In the root of the project, create a new directory named `lib`. This directory will hold the testing libraries.

    ```bash
    mkdir lib
    ```

2.  **Download JUnit 5:**
    Download the JUnit Platform Console Standalone JAR file and place it in the `lib` directory. This single JAR contains everything you need to run JUnit 5 tests from the command line.

    You can download it from the following URL:
    [junit-platform-console-standalone-1.8.2.jar](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.2/junit-platform-console-standalone-1.8.2.jar)

    You can use `curl` or `wget` to download it from your terminal:

    ```bash
    curl -o lib/junit-platform-console-standalone-1.8.2.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.2/junit-platform-console-standalone-1.8.2.jar
    ```

## Directory Structure

To keep the tests organized and separate from the application code, we will place all test files in a `tests` directory. You should create this directory in the root of the project.

```bash
mkdir tests
```

The project structure will look like this:

```
.
├── colorThings
├── core
├── lib
│   └── junit-platform-console-standalone-1.8.2.jar
├── out
├── shape
├── tests
│   └── MyVectorTest.java
├── Display.java
├── Main.java
├── README.md
└── Scene.java
```

## Writing Tests

To write a unit test, you create a test class in the `tests` directory. This class should contain methods annotated with `@Test`, where each method tests a specific piece of functionality.

Here is an example of a test class, `tests/MyVectorTest.java`, which tests the `core.MyVector` class:

```java
import core.MyVector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyVectorTest {

    @Test
    @DisplayName("Test the add method")
    void testAdd() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        MyVector result = MyVector.add(v1, v2);
        assertEquals(4, result.x, "The x component should be 4");
        assertEquals(6, result.y, "The y component should be 6");
    }

    @Test
    @DisplayName("Test the sub method")
    void testSub() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        MyVector result = MyVector.sub(v1, v2);
        assertEquals(-2, result.x, "The x component should be -2");
        assertEquals(-2, result.y, "The y component should be -2");
    }

    @Test
    @DisplayName("Test the dotProduct method")
    void testDotProduct() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        float result = MyVector.dotProduct(v1, v2);
        assertEquals(11, result, "The dot product should be 11");
    }

    @Test
    @DisplayName("Test the rotate90 method")
    void testRotate90() {
        MyVector v1 = new MyVector(1, 2);
        MyVector result = MyVector.rotate90(v1);
        assertEquals(-2, result.x, "The x component should be -2");
        assertEquals(1, result.y, "The y component should be 1");
    }

    @Test
    @DisplayName("Test the magnitude method")
    void testMagnitude() {
        MyVector v1 = new MyVector(3, 4);
        float result = v1.magnitude();
        assertEquals(5, result, "The magnitude should be 5");
    }
}
```

Key things to note:

-   **`import static org.junit.jupiter.api.Assertions.assertEquals;`**: This imports the static `assertEquals` method, which is used to check if an actual value matches an expected value.
-   **`@Test`**: This annotation marks a method as a test method.
-   **`@DisplayName(...)`**: This annotation provides a custom display name for the test method, which makes the test output more readable.
-   **`assertEquals(expected, actual, message)`**: This is an assertion that checks if the `actual` value is equal to the `expected` value. If they are not equal, the test fails, and the `message` is displayed.

## Compiling and Running Tests

Once you have written your tests, you need to compile them along with the application code and then run them using the JUnit 5 console launcher.

1.  **Compile the code and tests:**
    Open a terminal in the root of the project and run the following command. This will compile all the application source files and your test files, placing the compiled `.class` files into the `out` directory.

    *Note: The command below uses a colon (`:`) as a path separator. If you are on Windows, you should replace it with a semicolon (`;`).*

    ```bash
    javac -d out -cp lib/junit-platform-console-standalone-1.8.2.jar:. *.java colorThings/*.java core/*.java shape/*.java tests/*.java
    ```

2.  **Run the tests:**
    After compilation, you can run the tests using the JUnit Platform Console Standalone JAR. The following command will scan the `out` directory for test classes and run them.

    ```bash
    java -jar lib/junit-platform-console-standalone-1.8.2.jar --class-path out --scan-classpath
    ```

    You should see output indicating that the tests have run and whether they passed or failed.
