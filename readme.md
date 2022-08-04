# Exposed many-to-one one-to-many Examples

Simple repo to showcase Exposed relationships as the wiki itself is a bit lacking for my tastes.
In this repo you'll find:
- Buildings table, which has no idea others (Rooms/Persons) exist
- Rooms table, which has FK reference on Building (and Many-to-One relation) and a One-to-Many relation with Persons
- Persons table, which has FK on Rooms (Many-to-One relation)