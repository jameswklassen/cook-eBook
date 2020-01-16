# Iteration 2

## This Iteration
This iteration we did quite a few things. The big thing we did was started implementing the database. We still have not fully implemented it for everything we do (for example new user tags are not added to the database, and no tags are deleted), but we are hoping to get to that next iteration. You can now click on a recipes image and enlarge it. If you want to sort or filter your recipes you can now do that by clicking the sort or filter option in the overflow menu of the main activity. You can also view your favourites by pressing the heart in the top bar of the main activity. Additionally we added an Error hierarchy and proper messages for invalid recipes.

### Features
Finished:
- Edit Recipes
- Recipe Organization (filter recipe)


Started but not Finished:
- Sorting Recipes
    - need to implement search
- View Recipes
    - need to store images of recipes in database and then show them in the UI


### User Stories
Finished:
- Edit Recipe Information
- View Recipes by Tag
- View Favourite Recipes
- Enlarge Images
- Sort recipe by Criteria

Started but not Finished:
- Mark Recipe as Favourite
    - need to fix something small for updating favourite


Note for the Marker:
- We were having issues updating the favourite button down into the persistence layer. Besides that the favourite button is functional, but this is something we need to fix in the next iteration.
- Our database is also not fully implemented properly for the tags. We are hoping to get to that in the next iteration.

### Architecture Document
We updated our [Architecture](https://code.cs.umanitoba.ca/comp3350-summer2019/cook-eBook/blob/master/docs/Architecture.md) Document

## Next Iteration
In the third Iteration we would like to :
- add Searching (would look for recipes with a particular tag)
- add Duplicate Recipe
- add images to the database and display them in the UI (would a need a way to upload the images from local gallery)
- add the date to the view

If we have time we would also like to add:
- add Share Recipe
- add Read Recipe (text to speech)

