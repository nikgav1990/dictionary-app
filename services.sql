CREATE DATABASE IF NOT EXISTS services DEFAULT CHARACTER SET Utf8 COLLATE Utf8_general_ci;
USE services;

CREATE TABLE IF NOT EXISTS users (id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR( 50 ) NOT NULL,
surname VARCHAR( 50 ) NOT NULL,
username VARCHAR( 50 ) NOT NULL, 
password VARCHAR(50) NOT NULL,
successful_logins INT(10) NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS words (id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
letter CHAR( 5 ) NOT NULL,
word VARCHAR( 50 ) NOT NULL,
definition VARCHAR( 200 ) NOT NULL
);

ALTER TABLE users AUTO_INCREMENT = 1;

INSERT INTO users(name,surname,username,password) VALUES('Nikos','Panagopoulos','nickp','1234');
INSERT INTO users(name,surname,username,password) VALUES('Nikos','Gavalas','nickg','1258');
INSERT INTO users(name,surname,username,password) VALUES('Giannis','Papadopoulos','johnpap','5254');
INSERT INTO users(name,surname,username,password) VALUES('Marios','Kwnstantinou','marioskon','1990');
INSERT INTO users(name,surname,username,password) VALUES('Maria','Papakwnstantinou','mariap','1994');

INSERT INTO words(letter,word,definition) VALUES('A','abase','cause to feel shame');
INSERT INTO words(letter,word,definition) VALUES('A','attribute','a quality belonging to or characteristic of an entity');
INSERT INTO words(letter,word,definition) VALUES('A','augury','a quality belonging to or characteristic of an entity');

INSERT INTO words(letter,word,definition) VALUES('B','bandy','discuss lightly');
INSERT INTO words(letter,word,definition) VALUES('B','beatific','resembling or befitting an angel or saint');
INSERT INTO words(letter,word,definition) VALUES('B','bullion','gold or silver in bars or ingots');

INSERT INTO words(letter,word,definition) VALUES('C','cameo','engraving or carving in low relief on a stone');
INSERT INTO words(letter,word,definition) VALUES('C','campaign','a race between candidates for elective office');
INSERT INTO words(letter,word,definition) VALUES('C','cleave','separate or cut with a tool, such as a sharp instrument');

INSERT INTO words(letter,word,definition) VALUES('D','debacle','a sound defeat');
INSERT INTO words(letter,word,definition) VALUES('D','decorum','propriety in manners and conduct');
INSERT INTO words(letter,word,definition) VALUES('D','deduce','reason from the general to the particular');

INSERT INTO words(letter,word,definition) VALUES('E','exploit','draw from; make good use of');
INSERT INTO words(letter,word,definition) VALUES('E','establish','set up or found');
INSERT INTO words(letter,word,definition) VALUES('E','exigency','a pressing or urgent situation');

INSERT INTO words(letter,word,definition) VALUES('F','foster','foster');
INSERT INTO words(letter,word,definition) VALUES('F','fructify','become productive or fruitful');
INSERT INTO words(letter,word,definition) VALUES('F','functionary','a worker who holds or is invested with an office');

INSERT INTO words(letter,word,definition) VALUES('G','garble','make false by mutilation or addition');
INSERT INTO words(letter,word,definition) VALUES('G','gentry','the most powerful members of a society');
INSERT INTO words(letter,word,definition) VALUES('G','grove','a small growth of trees without underbrush');

INSERT INTO words(letter,word,definition) VALUES('H','halting','proceeding in a fragmentary, hesitant, or ineffective way');
INSERT INTO words(letter,word,definition) VALUES('H','headlong','in a hasty and foolhardy manner');
INSERT INTO words(letter,word,definition) VALUES('H','humiliate','cause to feel shame');

INSERT INTO words(letter,word,definition) VALUES('I','idiosyncrasy','a behavioral attribute peculiar to an individual');
INSERT INTO words(letter,word,definition) VALUES('I','ignominy','a state of dishonor');
INSERT INTO words(letter,word,definition) VALUES('I','impart','transmit, as knowledge or a skill');

INSERT INTO words(letter,word,definition) VALUES('J','justify','show to be reasonable or provide adequate ground for');
INSERT INTO words(letter,word,definition) VALUES('J','jet','an airplane powered by gas turbines');
INSERT INTO words(letter,word,definition) VALUES('J','jocund','full of or showing high-spirited merriment');

INSERT INTO words(letter,word,definition) VALUES('K','knight','a person of noble birth trained to arms and chivalry');
INSERT INTO words(letter,word,definition) VALUES('K','keen','demonstrating ability to recognize or draw fine distinctions');
INSERT INTO words(letter,word,definition) VALUES('K','knoll','a small natural hill');

INSERT INTO words(letter,word,definition) VALUES('L','larder','a small storeroom for storing foods or wines');
INSERT INTO words(letter,word,definition) VALUES('L','largesse','liberality in bestowing gifts');
INSERT INTO words(letter,word,definition) VALUES('L','luxuriant','produced or growing in extreme abundance');

INSERT INTO words(letter,word,definition) VALUES('M','majority','more than half of the votes in an election');
INSERT INTO words(letter,word,definition) VALUES('M','munificent','very generous');
INSERT INTO words(letter,word,definition) VALUES('M','morbid','suggesting the horror of death and decay');

INSERT INTO words(letter,word,definition) VALUES('N','nonentity','a person of no influence');
INSERT INTO words(letter,word,definition) VALUES('N','nonplus','be a mystery or bewildering to');
INSERT INTO words(letter,word,definition) VALUES('N','nuance','a subtle difference in meaning or opinion or attitude');

INSERT INTO words(letter,word,definition) VALUES('O','obloquy','abusive, malicious, and condemnatory language');
INSERT INTO words(letter,word,definition) VALUES('O','obtain','come into possession of');
INSERT INTO words(letter,word,definition) VALUES('O','ordain','invest with ministerial or priestly authority');

INSERT INTO words(letter,word,definition) VALUES('P','pallid','lacking in vitality or interest or effectiveness');
INSERT INTO words(letter,word,definition) VALUES('P','parity','functional equality');
INSERT INTO words(letter,word,definition) VALUES('P','parsimonious','excessively unwilling to spend');

INSERT INTO words(letter,word,definition) VALUES('Q','quaff','swallow hurriedly or greedily or in one draught');
INSERT INTO words(letter,word,definition) VALUES('Q','quail','draw back, as with fear or pain');
INSERT INTO words(letter,word,definition) VALUES('Q','quip','make jokes or witty remarks');

INSERT INTO words(letter,word,definition) VALUES('R','rampart','an embankment built around a space for defensive purposes');
INSERT INTO words(letter,word,definition) VALUES('R','ranging','wandering freely');
INSERT INTO words(letter,word,definition) VALUES('R','recovering','returning to health after illness or debility');

INSERT INTO words(letter,word,definition) VALUES('S','surreptitious','marked by quiet and caution and secrecy');
INSERT INTO words(letter,word,definition) VALUES('S','sybarite','a person addicted to luxury and pleasures of the senses');
INSERT INTO words(letter,word,definition) VALUES('S','syllogism','reasoning in which a conclusion is derived from two premises');

INSERT INTO words(letter,word,definition) VALUES('T','tenacious','stubbornly unyielding');
INSERT INTO words(letter,word,definition) VALUES('T','theory','a well-substantiated explanation of some aspect of the world');
INSERT INTO words(letter,word,definition) VALUES('T','turbid','clouded as with sediment');

INSERT INTO words(letter,word,definition) VALUES('U','unconscionable','greatly exceeding bounds of reason or moderation');
INSERT INTO words(letter,word,definition) VALUES('U','undertake','enter upon an activity or enterprise');
INSERT INTO words(letter,word,definition) VALUES('U','aupbraid','express criticism towards');

INSERT INTO words(letter,word,definition) VALUES('V','vassal','a person holding a fief');
INSERT INTO words(letter,word,definition) VALUES('V','venerate','regard with feelings of respect and reverence');
INSERT INTO words(letter,word,definition) VALUES('V','veracious','precisely accurate');

INSERT INTO words(letter,word,definition) VALUES('W','warble','sing or play with trills');
INSERT INTO words(letter,word,definition) VALUES('W','warrant','show to be reasonable or provide adequate ground for');
INSERT INTO words(letter,word,definition) VALUES('W','wizened','lean and wrinkled by shrinkage as from age or illness');

INSERT INTO words(letter,word,definition) VALUES('X','xenolith','A rock fragment foreign to the igneous mass in which it occurs.');
INSERT INTO words(letter,word,definition) VALUES('X','xenophobic','Fear, hatred, or mistrust of that which is foreign, especially strangers or people from different countries or cultures.');
INSERT INTO words(letter,word,definition) VALUES('X','xerophilia','the ability of some plants to survive in dosert or salt marsh areas by storing fresh water internally');

INSERT INTO words(letter,word,definition) VALUES('Y','yard','an outdoor area that is next to a house and is usually covered by grass');
INSERT INTO words(letter,word,definition) VALUES('Y','young',' in an early stage of life, growth, or development : not yet old');
INSERT INTO words(letter,word,definition) VALUES('Y','yawn','to open your mouth wide while taking in breath usually because you are tired or bored');

INSERT INTO words(letter,word,definition) VALUES('Z','zealot','a fervent and even militant proponent of something');
INSERT INTO words(letter,word,definition) VALUES('Z','zenith','the point above the observer directly opposite the nadir');
INSERT INTO words(letter,word,definition) VALUES('Z','zephyr','a slight wind');