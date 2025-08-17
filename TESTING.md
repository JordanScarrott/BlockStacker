# Testing Guide

This document provides instructions on how to run the tests for this project.

## Prerequisites

* Java Development Kit (JDK) 8 or higher
* Gradle

## Running Tests

To run the tests, execute the following command from the root directory of the project:

```bash
./gradlew test
```

This command will compile the test source code and run the tests. The test results will be displayed in the console.

## Sample Test

Here is an example of a simple test for the `MyVector` class. This test verifies that the `add` method of the `MyVector` class works correctly.

`src/test/java/com/jordan/blockstacker/core/MyVectorTest.java`

```java
package com.jordan.blockstacker.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyVectorTest {

    @Test
    public void testAdd() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        MyVector result = MyVector.add(v1, v2);
        assertEquals(4, result.x);
        assertEquals(6, result.y);
    }
}
```
