# Gilded Rose Inventory System Refactor

## How did I start ?
- Analysing given details : 
```
- "GildedRose have a system in place that updates our inventory for us" - Inventory System
- "Task is to add a new feature to the system, enable selling a new category of items" - feature is introducing a new type/category of item
```

- Understanding the Restrictions :
```
- do not alter the Item class or Items property
- free to make any changes to the `updateQuality` method
```

- Goals :
```
- A polymorphic solution assuming as it's kind of an inventory system
- Add or Remove items as simple & safe
```

## Refactoring Steps ?

```
1 - Understanding the given code and add/update unit tests for each behaviour
2 - Updated unit tests for all types of items (normal items, aged items, legendary items, backstage passes)
3 - Iteratively find the smallest piece of code to change and run unit tests.
   3.1 - Items array is index based usage - noisy, less readable. -> Make it forEach
   3.2 - Extract the code out of for loop to a new method and deal with one item at a time
   3.3 - Attempt to find the steps of the algorithm (template pattern)
       3.3.1 - Update quality of item -> Update expiry of item -> process expired items
   3.4 - Remove/invert the negation based if conditions (better readability)
       3.4.1 - lots of if & nested ifs (Isolate, Improve and Inline)
4 - Idea is to update each type/category of item independently (wherever applicable)
5 - It led to creational design pattern
6 - Remove duplicate codes
```

![GildedRoseInventory](https://github.com/sumit-mishra/GildedRose-Refactoring-Kata/assets/14976827/6f11d44e-c9fb-41a5-b8a2-5eabc21548ae)

InventoryItem is a wrapper around Item with default behaviour.

### Possible Improvements

- Introducing category and linking each inventory item to a category would be more resilient to further change

