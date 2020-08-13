# My Personal Project

## Phase 1

#### What will the application do? Who will use it?

I want to create an application where a user will be able to obtain a random amount of "coins" which they can use to 
purchase certain characters of a game. 
The coins can also be used to power up different traits of the characters the user purchases.

The user can also view the characters with their different traits and how much it costs to power up to the next level,
what level they're currently at, and how many coins they have left.

#### Why is this project of interest to you?

I wanted to create this project because I play a mobile version of *Sonic the Hedgehog* a lot, and it's quite fun to 
always have a goal to work towards in a game (e.g. accumulating coins to power up a character).
I also don't really play games at all, so I wanted to try to code one and see how it is like.

#### User Stories

- **As a user, I want to be able to generate a lucky toss to obtain a random amount of coins.**
- **As a user, I want to be able to see how many coins I currently have.**
- **As a user, I want to be able to view all the characters available, and all the characters I own.**
- **As a user, I want to be able to add a character to the list of characters I own.**
- **As a user, I want to be able to select a character and view how much it costs to power up/purchase it.**
- **As a user, I want to be able to select a character and power up/purchase it.**
- **As a user, I want to be able to recover my characters from where I last left of when I quit the program.**
- **As a user, I want to be able to save the state of my application.**


#### Instructions for Grader
- Click the button "Generate lucky coin toss" to generate a lucky toss.
- You can view your total amount of coins at all times in the top left corner.
- Click "All characters available" to view. Choose character to purchase and add to "Your characters owned".
- Click "Your characters owned" to view characters you have purchased. Click to view skill levels and power up costs.
- Select a character and click a skill button to power up.
- AUDIOVISUALS: all character buttons have images of the character; coin toss generates gif
- SAVE: click "Save progress" to save character purchase progress to file.
- LOAD: click "Load past progress" to load last saved progress. If no saved progress present, start from scratch.

#### Phase 4: Task 2
I have implemented a type hierarchy. I used an abstract CharList class as the superclass/template. Since the user needs 
to keep track of a list of characters already owned and a list of characters not yet owned, there are two subclasses: 
UserCharList and AllCharList. UserCharList only has one additional method than CharList, and it is the ability to add
new characters. AllCharList doesn't have this function as it only needs to remove characters from its list, not add.
AllCharList, on the other hand, loads all the available characters and creates a nonempty character list upon
 initiation (so it builds upon the superclass' constructor). UserCharList overrides the getChar(String charName) method
 to return the given character in its list. AllCharList overrides the same method but removes the character upon getting
 the character. UserCharList also overrides the getListName() method by returning the actual field listName, while 
 AllCharList always returns the constant variable ALL_CHAR_AVAIL = "all characters available".

#### Phase 4: Task 3
Cohesion: My main GUI class was handling the displays for the graphical UI as well as managing the saving/loading of 
the character lists. Since that seems like it is taking an additional role, I refactored a new class called ListManager
 that focused on saving and loading the character lists. The original methods in CardLayout GUI still exist, but they 
 now call the ListManager's methods (from the new ListManager field).

Coupling: 
