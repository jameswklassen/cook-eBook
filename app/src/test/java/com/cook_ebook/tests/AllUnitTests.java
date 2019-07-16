package com.cook_ebook.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Import the individual test files
import com.cook_ebook.logic.RecipeTagValidator;
import com.cook_ebook.tests.business.ExceptionsTest;
import com.cook_ebook.tests.business.InitBusinessTest;
import com.cook_ebook.tests.business.RecipeHandlerTest;
import com.cook_ebook.tests.business.RecipeTagHandlerTest;
import com.cook_ebook.tests.business.RecipeValidatorTest;
import com.cook_ebook.tests.objects.InitObjectTest;
import com.cook_ebook.tests.objects.RecipeTagTest;
import com.cook_ebook.tests.objects.RecipeTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InitBusinessTest.class,
        InitObjectTest.class,
        RecipeTest.class,
        RecipeHandlerTest.class,
        RecipeTagTest.class,
        RecipeTagHandlerTest.class,
        RecipeValidatorTest.class,
        ExceptionsTest.class
})

public class AllUnitTests
{

}
