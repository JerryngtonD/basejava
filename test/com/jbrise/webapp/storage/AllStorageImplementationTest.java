package com.jbrise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapOnUuidStorageTest.class,
        MapOnResumeStorageTest.class
})
public class AllStorageImplementationTest {
}
