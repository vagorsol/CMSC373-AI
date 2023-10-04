% A first PROLOG database of the Simpsons's family tree.
% Uses the following relations/predicates:
% maritals
% married(x, y) : x is married to y
%
married(abraham, mona).
married(mona, abraham).
married(clancy, jackie).
married(jackie, clancy).
married(homer, marge).
married(marge, homer).
% males and females
% female(x) : x is female
% male(x)   : x is male
male(abraham).
male(clancy).
male(herb).
male(homer).
male(bart).
female(mona).
female(jackie).
female(marge).
female(patty).
female(selma).
female(ling).
% parents
% parent(x, y) : x is a parent of y
parent(abraham, herb).
parent(mona, herb).
parent(abraham, homer).
parent(mona, homer).
parent(clancy, marge).
parent(jackie, marge).
parent(clancy, patty).
parent(jackie, patty).
parent(clancy, selma).
parent(jackie, selma).
parent(homer, bart).
parent(marge, bart).
parent(homer, lisa).
parent(marge, lisa).
parent(homer, maggie).
parent(marge, maggie).
parent(selma, ling).
% relationships
% mother(x, y) : x is a mother of y
% father(x, y) : x is a father of y
% sibling(x, y): x is a sibling of y
% etc.
mother(X, Y) :- parent(X, Y), female(X).
father(X, Y) :- parent(X, Y), male(X).
grandparent(X, Z) :- parent(X, Y), parent(Y, Z).
sibling(X, Y) :- parent(Z, X), parent(Z, Y), not(X = Y).
auntoruncle(X, W) :- sibling(X, Y), parent(Y, W).
auntoruncle(X, Z) :- married(X, Y), sibling(Y, W), parent(W, Z).
aunt(X, W) :- female(X), auntoruncle(X, W).
uncle(X, W) :- male(X), auntoruncle(X, W).
ancester(X, Y) :- parent(X, Y).
ancester(X, Y) :- parent(X, Z), ancester(Z, Y).

