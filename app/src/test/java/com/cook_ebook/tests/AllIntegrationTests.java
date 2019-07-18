package com.cook_ebook.tests;

import com.cook_ebook.tests.business.RecipeHandlerIT;
import com.cook_ebook.tests.business.RecipeTagHandlerIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        RecipeHandlerIT.class,
        RecipeTagHandlerIT.class
})

public class AllIntegrationTests {
}
