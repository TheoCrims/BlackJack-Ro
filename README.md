# BlackJack-Ro
Casino games depend largely on chance and luck, but this is not 100% true for Blackjack. The Blackjack card game is part of this small group of games of chance in which the result can be influenced by the player's strategy and skills. Let's take the book counting strategy as an example. This is the most popular and often used to beat the House. Exactly because they work, casinos are working hard to detect the players they will use. It is not an illegal strategy, but the House will use every possible way to persuade you to give up the game. Blackjack is a game that obeys strict rules. If you apply them correctly and disciplined, you will reduce the House's advantage to less than 1%. The most advantageous combinations are a hand of 21 or 20. The third best hand is worth 11 because you can afford to draw or card with minimal risk. Many novice players lose the game because their goal is to get a hand worth 21, but this is not true. The goal of the bettor is to beat the House, ie to make a hand of value as close as possible to 21, but without exceeding it and this hand to be bigger than that of the dealer.
Blackjack in Romana
Card.Java
Pentru a reprezenta un pachet de cărți.
Putem folosi constructorul pentru a inițializa pachetul de cărți pentru a conține cărțile
potrivite.(Public class Card).
Pentru cartile cu valoarea(n):2,3,4,5,6,7,8,9 li se atribuie valoarea exprimata pe carte.
Iar pentru cartile cu valoarea(n)>=10, li se atribuie valoarea 10:10, Jack(J)=11,Queen(Q)=
12,King(K)=13.
Vaoarea 1 este reprezentata de Ace(A).
Main.Java
Metoda main implementeaza rata de refresh(60Hz)a aplicatiei in timp ce ruleaza in window
1
mode.
Aceasta avand rolul de a reimprospata GUI(Graphical User Interface)-JFrame si de a-l aduce la
stadiul intial odata cu accesarea butonului JButton.Urmand ca acesta sa aiba rol si in evidenta
scrolui.
Message.java
Aceasta clasa este implementata pentru interfata grafica si mesajele pe care urmeaza sa le
primesti in momentul derularii aplicatiei.
Primeste mesajul , intreaba , il atribuie.
2
GUI(Graphical User Interface).java
Incepem prin a importa librariile:
Implementam functia pentru randomizarea cartilor din pachet:(alegere aleatorie din deck-ul de
carti):
Implementam un status:numar intreg folosit pentru statusul de folosit:
Implementam boolean care indică dacă dealerul gândește sau nu:
3
Implementam lista de carti(booleana):
Implementam lista de mesaje:
Fonturile folosite pentru:Carte,Intrebare,Buton,Log
Culorile folosite pentru Player si Dealer(Log):
Culoarea playerului fiind una RGB:
Stringuri folosite pentru intrebarile din dreapta sus , respectiv jos a panelului:
Culorile folosite pentru buton si fundal
Implementarea butoanelor : Stai , Trage , Da , Nu:
Rezolutia ferestrei:
4
Pozitia cartilor si grilei:
Spatierea si dimensiunea cartilor
Introducerea FAZELOR JOCULUI:
Array-ul playerului si dealerului:
Totalul punctelor playerului si dealerului:
Denumirea jocului uramand initializarea boardului: Functia Close a tabului-->Exit
5
Implementarea butoanelor si potrivirea acestora pe screen resolution:
-Trag
-Stau
-Da
6
-Nu
Crearea tuturor cartilor -52 carti
Selectarea aleatorie a cartilor pentru dealer si player:
7
Crearea clasei totalsChecker pentru a calcula scorul total al playerului:
8
Declararea castigatorului rundei implementand clasa SetCastigator:
9
atribuind dealerului d++ runda si playerului p++ daca unul din acestia respecta regula si castiga.
Urmand apoi sa stabilim o noua clasa , dealerTrageStai acesta incepand cu 0 urmarind
incercarea de a atinge 21 de puncte sau ca acesta sa fie cat mai aproape de a atinge acel scor.
Refresherul are rolul ca in momentul in care apare mesajul "Stop Automat" || "Dealer Stop
Automat" si apare pe ecran Total acesta sa dea repaint aplicatiei.
PaintComponent este clasa creata pentru a evidentia culoarea , fontul , grila , dimensiunea :
intrebarilor: Tragi , Stai?, Totalului, Scorului , Playerului ,Dealerului, fundaluiui,backgroundului
Cartile Playerului:
10
Cartile Dealerului:
actionPerformed(actiunile performate in timpul derularii aplicatiei:
Actiunea:Ai tras o carte(Trag)
Actiunea:Ai ales sa stai(Stai)
11
LoginVerificare.java
Clasa LoginVerificare are rolul de a verifica daca datele introduse in program sunt
corespunzatoare cu cele din fisierul login.txt .
Functia writer.write(user+"\n"+pass); trimite catre reader (Scanner reader) string-urile
declarate in clasa main.
12
Scanner-ul si Delimiter-ul citesc liniile din .txt si le atribuie in cLine.
In while (reader.hasNext()) se preiau liniile din .txt si sunt atribuite in metoda contulExista().
Apoi prin if (Exista == true) verificam daca ce avem in .txt corespunde cu string-urile din clasa
main, si afisam daca este booleanul exista ia valoarea true sau false, prin liniile "Esti logat!" sau
"Nu esti logat!"
13
14
