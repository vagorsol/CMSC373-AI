/* DATABASE

    isa(x, y): x is a y. For example, isa(x, mammal), or isa(x, cheetah), etc.
    has(x, y): x has y. For example, has(swifty, hair), has(swifty, forward_pointed_teeth), etc.
    flies(x): x flies
    swims(x): x swims
    good_flier(x): x is a good flier
    lays_eggs(x): x lays eggs
    chews_cud(x): x chews cud
    color(x, y): x is colored y
*/

:- discontiguous color/2, has/2.

% stretch
has(stretch, hair).
has(stretch, long_legs).
has(stretch, long_neck).
has(stretch, dark_spots).
chews_cud(stretch).
color(stretch, tawny).

% swifty
has(swifty, hair).
has(swifty, pointed_teeth).
has(swifty, claws).
has(swifty, forward_pointing_eyes).
has(swifty, dark_spots).
color(swifty, tawny).

isa(X, mammal):- has(X, hair).
isa(X, mammal):- has(X, milk).

isa(X, bird):- has(X, feathers).
isa(X, bird):- flies(X).
isa(X, bird):- flies(X), lays_eggs(X).

isa(X, carnivore):- isa(X, mammal), has(X, pointed_teeth), has(X, claws), has(X, forward_pointing_eyes).

isa(X, ungulate):- isa(X, mammal), has(X, hoofs).
isa(X, ungulate):- isa(X, mammal), chews_cud(X).

isa(X, cheetah):- color(X, tawny), has(X, dark_spots), isa(X, carnivore).

isa(X, tiger):- isa(X, carnivore), color(X, tawny), has(X, black_stripes).

isa(X, giraffe):- isa(X, ungulate), has(X, long_legs), has(X, long_neck), color(X, tawny), has(X, dark_spots).

isa(X, zebra):- isa(X, ungulate), color(X, white), has(X, black_stripes).

isa(X, ostrich):- isa(X, bird), not(flies(X)), has(x, long_legs), color(X, black), color(X, white).

isa(X, penguin):- isa(X, bird), not(flies(X)), swims(X), color(X, black), color(X, white).

isa(X, albatross):- isa(X, bird), good_flier(X).