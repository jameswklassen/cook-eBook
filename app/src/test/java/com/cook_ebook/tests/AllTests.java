package com.cook_ebook.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Import the individual test files
import com.cook_ebook.tests.business.InitBusinessTest;
import com.cook_ebook.tests.objects.InitObjectTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InitBusinessTest.class,
        InitObjectTest.class,
})

public class AllTests
{

}
