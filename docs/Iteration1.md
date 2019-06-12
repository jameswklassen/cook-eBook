# Iteration 1

## This Iteration

### Features
Finished:
- Add Recipes
- Delete Recipes

Started but not Finished:
- View Recipes
    - need to add specific image for recipe
- Recipe Organization
    - need to be able to sort recipes by tag or favourites
   
### User Stories
Finished:
- Adding Ingredients
- Adding Directions
- Add Cooking Time
- View a List of Recipes
- View a single Recipe
- Delete a Recipe
- Mark Recipe with tags

Started but Not Finished:
- Mark Recipe as favourite
    - need to make it so when someone favourites it changes the value in the persistence layer

Note for the Marker:
- We started adding the image of the recipe, currently right now we don't have the uploading of a photo for a recipe working, but you will see an add photo button on the add UI since we were working on that
- We were able to assoicate an image with a recipe and display it in the single recipe view

### Architecture Document
We updated our [Architecture](https://code.cs.umanitoba.ca/comp3350-summer2019/cook-eBook/blob/master/docs/Architecture.md) Document

## Next Iteration
In the second Iteration we would like to :
- add proper error catching so we will build an error hierarchy
- add a function to check for valid recipes and show an error to the user if its invalid
- properly implement the favourite button
- add the edit recipe functions
- add the sort/filter feature
- add a proper database and adjust tests for it
