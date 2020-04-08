-----------------------------------------------------------------------------------------------

The game stores a grid of Cells, which is an abstract class. This means that any type of Cell
can be created and be placed into the world. As an example, the only class right now that
extends Cell is Land. Land-based lifeforms will be able to walk on these Cells.

This allows flexibility for the types of Cells that lifeforms can inhabit. For example, there
can be a Water class that extends Cell. Land-based lifeforms won't be able to walk on Water,
but Fish can.

New Lifeforms can just check if their neighboring cells are a certain instance of Land or Water
so they can move to that Cell.

-----------------------------------------------------------------------------------------------

Lifeforms are also an abstract class with an abstract update method. New Lifeforms can just
extend Lifeform and override the update method to uniquely determine each Lifeforms behavior.

These Lifeforms can then be updated by the game without casting as update is a method from Lifeform.

There are also interfaces to determine if a Lifeform is edible for Herbivores. The only interface
determining edibility right now is EdibleForHerbivore which allows Herbivores to eat those Lifeforms
that implement that. In the future, we can add interfaces like EdibleForCarnivore in case I need
to add Carnivores to the game.

By using interfaces, there is a potential to use them as properties for Lifeforms that influence how
other Lifeforms react to it. As an example, we can have a Plant that implements EdibleForHerbivore but
also implements Poisonous. If a Herbivore eats a poisonous plant, it could lower their life faster
or something like that.

-----------------------------------------------------------------------------------------------